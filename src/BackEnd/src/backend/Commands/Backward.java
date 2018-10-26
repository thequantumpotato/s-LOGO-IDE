package backend.Commands;
import backend.Turtle;

import java.util.List;

public class Backward extends TurtleNode {

    public Backward(Turtle t, List<Node> children) {
        super(t, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        Double d = l.get(0);
        myTurtle.move(-1 * d);
        return d;
    }
}
