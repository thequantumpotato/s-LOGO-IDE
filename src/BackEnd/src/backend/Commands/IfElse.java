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
        Boolean b = (Boolean) l.get(0);
        List<Node> l1 = (List<Node>)l.get(1);
        List<Node> l2 = (List<Node>)l.get(2);
        if(b){
            for(Node n: l1){
                n.run();
            }
        }
        else{
            for(Node n: l2){
                n.run();
            }
        }
        return 1;
    }
}
