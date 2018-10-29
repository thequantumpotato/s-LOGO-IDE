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
        Double reps;
        if (l.get(0) instanceof Integer) {
            reps = ((Integer) l.get(0)).doubleValue();
        } else {
            reps = (Double) l.get(0);
        }
        Integer times = reps.intValue();
        List<Node> cList = (List<Node>) l.get(1);
        System.out.println(cList.size());
        for (int i = 0; i < times; i++) {
            for (Node c : cList) {
                System.out.println(c);
                c.run();
            }
        }
        return 1;
    }
}
