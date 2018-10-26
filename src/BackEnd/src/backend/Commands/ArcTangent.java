package backend.Commands;

import backend.Turtle;

import java.util.List;

public class ArcTangent extends MathNode {

    public ArcTangent(Turtle t) {
        super(t);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        Double angle = l.get(0);
        return Math.atan(angle);
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }
}
