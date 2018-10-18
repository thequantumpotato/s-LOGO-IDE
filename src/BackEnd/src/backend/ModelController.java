
package backend;

import backend.Nodes.ArgumentNode;
import backend.Nodes.BasicNode;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ModelController {
    private Interpreter interpreter;
    private Turtle turtle;
    private Command c;

    public ModelController(){
        interpreter = new Interpreter();
        turtle = new Turtle();
        c = new Command();
    }

    public void parse(String input){
        List<BasicNode> l = null;
        try {
            l = interpreter.parse(input);
        } catch (Exception e) {
            System.err.println("interpreter has thrown an exception");
            e.printStackTrace();
        }
        List<ArgumentNode> res = new ArrayList<>();
        for(BasicNode b: l){
            res.add(parseTree(b));
        }
    }

    private ArgumentNode parseTree(BasicNode root){
        if(!root.isCommand()){
            return (ArgumentNode) root;
        }
        List<BasicNode> children = root.getChildren();
        List<ArgumentNode> arguments = new ArrayList<>();
        for(BasicNode b: children){
            arguments.add(parseTree(b));
        }
        ArgumentNode res = null;
        try {
            res = (ArgumentNode) c.getClass().getDeclaredMethod(lowerFirstLetter(root.getCommandName())).invoke(c, arguments);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return res;
    }

    private String lowerFirstLetter(String input){
        if(input.length() <= 0){
            return null;
        }
        String firstLetter = input.substring(0, 1);
        return firstLetter.toLowerCase() + input.substring(1, input.length());
    }
}