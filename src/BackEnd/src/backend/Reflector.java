package backend;

import backend.Nodes.BasicNode;
import backend.Nodes.CommandNode;
import backend.Nodes.LoopNode;
import backend.Nodes.ArgumentNode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jose San Martin
 * This house handles the reflection part of our parsing. It is instantiated in ModelController and is given
 * a Node to parse, where the command class is then used to parse the arguments
 */

public class Reflector {

    private Command Commander;
    private Turtle myTurtle;
    private Map<String, ArgumentNode> variableMap = new HashMap<>();
    private Map<String, CommandNode> instructionMap = new HashMap<>();

    public Reflector(Turtle turtle) {
        myTurtle = turtle;
        Commander = new Command(this, myTurtle);
    }

    public void execute(BasicNode root) throws NoSuchMethodException {
        if (root.getCommandName().matches("If")) {
            handleIf(root);
        } else {
            traverseTree(root);
        }
    }

    private BasicNode traverseTree(BasicNode root) throws NoSuchMethodException {
        if (root == null) {
            return null;
        }
        System.out.println(root instanceof CommandNode);
        if (isNotCommand(root)) { //Continue only if the node is part of the Command hierarchy
            return root;
        }
        List<BasicNode> children = new ArrayList<>();
        for (BasicNode child : root.getChildren()) {
            children.add(traverseTree(child));
        }
        String name = root.getCommandName().substring(0, 1).toLowerCase() + root.getCommandName().substring(1);
        Method command = Commander.getClass().getDeclaredMethod(name, List.class);

        //Invoke the command, and obtain the returned value, which is turned into a new argument node
        try {
            myTurtle.Changed();
            BasicNode ret = (BasicNode) command.invoke(Commander, children);
            myTurtle.clear();
            if (ret instanceof LoopNode) { //If it isnt an arg node, it is a loop node!
                loop((LoopNode) ret);
            }
            return ret;

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Return the new argument node
        throw new NoSuchMethodException();
    }

    private BasicNode handleIf(BasicNode root) throws NoSuchMethodException {
        if (root == null) {
            return null;
        }
        if (isNumeric(root.getCommandName())) {
            return root;
        }
        //The first child is always the condition to be evaluated
        BasicNode condition = traverseTree(root.getChildren().get(0));
        BasicNode lastCommand = null;
        if (!condition.getCommandName().equals("0")) {
            BasicNode list = root.getChildren().get(1);
            lastCommand = loopList(list);
        }
        return lastCommand;
    }

    //TODO: Make this return the last value of the last executed command
    public void loop(LoopNode loopNode) throws NoSuchMethodException { //Have to accept LoopNode, not BasicNode
        //Run the commands in the list a specified number of times
        for (int i = 0; i < loopNode.getReps(); i++) {
            for (BasicNode subCommand : loopNode.getChildren()) {
                execute(subCommand);
            }
        }
        return;
    }

    public BasicNode loopList(BasicNode list) throws NoSuchMethodException {
        BasicNode lastCommand = null;
        for (BasicNode commandToRun : list.getChildren()) {
            lastCommand = traverseTree(commandToRun);
        }
        return lastCommand;
    }

    public void createSetVariable(String name, BasicNode value) {
        variableMap.put(name.substring(1), (ArgumentNode)value); //This both creates an entry AND replace an existing entry
    }


    public ArgumentNode getVariable(String name) throws IllegalCommandException {
        if (!variableMap.keySet().contains(name)) {
            throw new IllegalCommandException("Variable"+name+"does not exist");
        }
        return variableMap.get(name);
    }

    public boolean createInstruction(String name, CommandNode inst) {
        if (instructionMap.keySet().contains(name)) {
            return false;
        }
        instructionMap.put(name, inst);
        return true;
    }

    public BasicNode getInstruction(String name) {
        if (!instructionMap.keySet().contains(name)) {
            return null;
        }
        return instructionMap.get(name);

    }
        private boolean isNumeric (String s){
            return s.matches("[-+]?\\d*\\.?\\d+");
        }

        private boolean isNotCommand (BasicNode n){
            return !(n instanceof CommandNode);
        }
    public void clearScreen(){

    }



}
