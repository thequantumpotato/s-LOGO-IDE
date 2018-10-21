package backend;


import backend.Nodes.BasicNode;
import backend.Nodes.ArgumentNode;
import backend.Nodes.CommandNode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelController {
    private Interpreter interpreter;
    private List<BasicNode> myCommands;
    private Command commander;
    private Turtle myTurtle;
    private Map<String, ArgumentNode> variableMap = new HashMap<>();
    private Map<String, CommandNode> instructionMap = new HashMap<>();

    public ModelController(Turtle turtle){
        interpreter = new Interpreter();
        commander = new Command(this, turtle);
        myTurtle = turtle;
    }

    /**
     * Parses the Command
     */
    public void parseCommand(String input) throws Exception {
        myCommands = interpreter.parse(input); //returns a list of root nodes
       // System.out.println(myCommands);

        for(BasicNode node: myCommands){
            traverseTree(node);
        }
        myTurtle.Changed();
        myTurtle.notifyObservers(true);
    }

    //Postorder traversal of each command tree
    public BasicNode traverseTree(BasicNode root) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (root == null){
            return null;
        }
        if(isNumeric(root.getCommandName())){
            return root;
        }
        List<BasicNode> children = new ArrayList<>();
        for(BasicNode child: root.getChildren()){
            children.add(traverseTree(child));
        }
        //System.out.println(root.getCommandName());
        Method command = commander.getClass().getDeclaredMethod(root.getCommandName(), Turtle.class, List.class);
        //Invoke the command, and obtain the returned value, which is turned into a new argument node
        try{
            myTurtle.Changed();
            Object ret = command.invoke(children);
            myTurtle.clear();
            //System.out.println(String.valueOf(ret));
            return (ArgumentNode) ret;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        //Return the new argument node
        throw new NoSuchMethodException();
    }

    public boolean createVariable(String name){
        if(variableMap.keySet().contains(name)){
            return false;
        }
        variableMap.put(name, new ArgumentNode("0.0"));
        return true;
    }

    public boolean setVariable(String name, ArgumentNode value){
        if(!variableMap.keySet().contains(name)){
            return false;
        }
        variableMap.put(name, value);
        return true;
    }

    public ArgumentNode getVariable(String name){
        if(!variableMap.keySet().contains(name)){
            return null;
        }
        return variableMap.get(name);
    }

    public boolean createInstruction(String name, CommandNode inst){
        if(instructionMap.keySet().contains(name)){
            return false;
        }
        instructionMap.put(name, inst);
        return true;
    }

    public BasicNode getInstruction(String name){
        if(!instructionMap.keySet().contains(name)){
            return null;
        }
        return instructionMap.get(name);
    }

    private boolean isNumeric(String s){
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public void updateVar(String varName, String varVal){

    }

}
