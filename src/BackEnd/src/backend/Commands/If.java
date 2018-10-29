package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class If extends RootNode {

    public If(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        Boolean b = (Boolean) l.get(0);
        List<Node> ln = (List<Node>)l.get(1);
        if(b){
            Object res = runNodeList(ln);
        }
        return null;
    }
}
