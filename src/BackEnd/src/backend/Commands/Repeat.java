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
        System.out.println("test1");
        Double reps;
        if(l.get(0) instanceof Integer){
            reps = ((Integer) l.get(0)).doubleValue();
        }else{
            reps = (Double) l.get(0);
        }
        System.out.println("test2");
        Integer times = reps.intValue();
        List<Node> cList = (List<Node>)l.get(1);
        for(int i = 0; i < times; i++){
            for(Node c: cList){
                c.run();
            }
        }
        return 1;
    }
}
