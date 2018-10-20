package backend;

import backend.Nodes.BasicNode;
import backend.Nodes.argumentNode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose San Martin
 * This house handles the reflection part of our parsing. It is instantiated in ModelController and is given
 * a Node to parse, where the command class is then used to parse the arguments
 */

public class Reflector {

    private Command Commander;
    private Turtle myTurtle;

    public Reflector(Command command, Turtle turtle){
        Commander = command;
        myTurtle = turtle;
    }

    public void execute(BasicNode root) throws NoSuchMethodException {
        if(root.getCommandName().matches("If")){
            handleIf(root);
        }
        else{
            traverseTree(root);
        }
    }


    public BasicNode traverseTree(BasicNode root) throws NoSuchMethodException{
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
        Method command = Commander.getClass().getDeclaredMethod(root.getCommandName(), Turtle.class, List.class);
        //Invoke the command, and obtain the returned value, which is turned into a new argument node
        try{
            myTurtle.Changed();
            Object ret = command.invoke(Commander,myTurtle,children);
            myTurtle.clear();
            //System.out.println(String.valueOf(ret));
            return (argumentNode) ret;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        //Return the new argument node
        throw new NoSuchMethodException();
    }

    private BasicNode handleIf(BasicNode root) throws NoSuchMethodException {
        if (root == null){
            return null;
        }
        if(isNumeric(root.getCommandName())){
            return root;
        }
        //The first child is always the condition to be evaluated
        BasicNode condition = traverseTree(root.getChildren().get(0));
        BasicNode lastCommand = null;
        if(!condition.getCommandName().equals("0")){
            BasicNode list = root.getChildren().get(1);
            for(BasicNode commandToRun : list.getChildren()){
                lastCommand = traverseTree(commandToRun);

            }
        }
        return lastCommand;
    }

    private boolean isNumeric(String s){
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

}
