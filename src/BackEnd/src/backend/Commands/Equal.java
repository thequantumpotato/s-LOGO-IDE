package backend.Commands;

import backend.Turtle;

import java.util.List;

public class Equal extends MathNode{

    public Equal(Turtle t, List<Node> children) {
        super(t, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        return l.get(0) == l.get(1);
    }
}
