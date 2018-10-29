package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class ArcTangent extends RootNode {

    public ArcTangent(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        Double angle = l.get(0);
        return Math.atan(angle);
    }
}
