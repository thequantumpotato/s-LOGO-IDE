package backend.Commands;

import backend.Turtle;

import java.util.List;

public class Forward extends TurtleNode{

    public Forward(Turtle t, List<Node> children) {
        super(t, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        myTurtle.move(l.get(0));
        return l.get(0);
    }
}
