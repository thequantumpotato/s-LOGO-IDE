package backend.Commands;

import backend.Turtle;

import java.util.List;

public class Difference extends MathNode {

    public Difference(Turtle t) {
        super(t);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        return l.get(0) - l.get(1);
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }
}
