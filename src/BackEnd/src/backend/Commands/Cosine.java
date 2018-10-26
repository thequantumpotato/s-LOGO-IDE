package backend.Commands;

import backend.Turtle;

import java.util.List;

public class Cosine extends MathNode{

    public Cosine(Turtle t, List<Node> children) {
        super(t);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        return Math.cos(l.get(0));
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }
}
