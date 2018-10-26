package backend.Commands;

import backend.Turtle;

import java.util.List;

public class Cosine extends MathNode{

    public Cosine(Turtle t, List<Node> children) {
        super(t, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        return Math.cos(l.get(0));
    }
}
