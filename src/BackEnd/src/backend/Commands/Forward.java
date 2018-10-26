package backend.Commands;

import backend.Turtle;

import java.util.List;

public class Forward extends TurtleNode{

    public Forward(Turtle t) {
        super(t);
    }

    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        myTurtle.move(l.get(0));
        return l.get(0);
    }

    @Override
    public int getNumChildren() {
        return 0;
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }
}
