package backend.Commands;
import backend.Turtle;
import backend.Storage.Storage;

import java.util.List;

public class Backward extends RootNode {

    public Backward(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        Double d = l.get(0);
        myTurtle.Changed();
        System.out.println(d);
        myTurtle.move(-1 * d);
        myTurtle.clear();
        return d;
    }
}
