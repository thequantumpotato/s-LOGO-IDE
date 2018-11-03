package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Equal extends RootNode {

    public Equal(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        System.out.println("equal children: " + myChildren.get(0));
        List<Double> l = parseDoubles(runChildren());
        System.out.println("equal test" + l);
        double e = 0.00000000001;
        return Math.abs(l.get(0)-l.get(1)) < e;
    }
}
