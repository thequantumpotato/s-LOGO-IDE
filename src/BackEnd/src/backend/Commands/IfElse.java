package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;
import java.util.List;

public class IfElse extends RootNode {

    public IfElse(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        System.out.println("llllllllllllll: " + l);
        Boolean b = (Boolean) l.get(0);
        List<Node> l1 = (List<Node>)l.get(1);
        List<Node> l2 = (List<Node>)l.get(2);
        if(b){
            Object res = runNodeList(l1);
            return res;
        }
        else{
            Object res = runNodeList(l2);
            return res;
        }
    }
}
