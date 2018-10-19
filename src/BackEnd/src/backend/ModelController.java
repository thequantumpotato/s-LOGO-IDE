package backend;


import backend.Nodes.BasicNode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ModelController {
    private Interpreter interpreter;
    private List<BasicNode> myCommands;
    private Command commander;
    private Turtle myTurtle;

    public ModelController(){
        interpreter = new Interpreter();
        commander = new Command();
        myTurtle = new Turtle();

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

        //Use reflection on myCommands

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
        System.out.println(root.getCommandName());
        Method command = commander.getClass().getDeclaredMethod(root.getCommandName(), Turtle.class, List.class);
        command.invoke(commander,myTurtle,children);
        return root;
    }


    private boolean isNumeric(String s){
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public void updateVar(String varName, String varVal){

    }

}
