package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class NotEqual extends RootNode {

    public NotEqual(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        double e = 0.000000001;
        return Math.abs(l.get(0) - l.get(1)) <= e;
    }
}
