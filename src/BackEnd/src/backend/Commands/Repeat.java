package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Repeat extends RootNode {
    public Repeat(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        Integer times = (Integer)l.get(0);
        List<Node> cList = (List<Node>)l.get(1);
        for(int i = 0; i < times; i++){
            for(Node c: cList){
                c.run();
            }
        }
        return 1;
    }
}
