package backend.Commands;

import java.util.List;

public class ArcTangent extends MathNode {

    public ArcTangent(List<Node> children) {
        super(children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        Double angle = l.get(0);
        return Math.atan(angle);
    }
}
