package backend;


import backend.Nodes.BasicNode;
import backend.Nodes.argumentNode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ModelController {
    private Interpreter interpreter;
    private List<BasicNode> myCommands;
    private Command commander;
    private Turtle myTurtle;

    public ModelController(Turtle turtle){
        interpreter = new Interpreter();
        commander = new Command();
        myTurtle = turtle;

    }

    /**
     * Parses the
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
            Object ret = command.invoke(commander,myTurtle,children);
            //System.out.println(String.valueOf(ret));
            return new argumentNode(String.valueOf(ret));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //Return the new argument node
        throw new NoSuchMethodException();
    }


    private boolean isNumeric(String s){
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public void updateVar(String varName, String varVal){

    }

}
