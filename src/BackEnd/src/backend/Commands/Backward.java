package backend.Commands;
import backend.Turtle;

import java.util.List;

public class Backward extends TurtleNode {

    public Backward(Turtle t) {
        super(t);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        Double d = l.get(0);
        myTurtle.move(-1 * d);
        return d;
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }
}
