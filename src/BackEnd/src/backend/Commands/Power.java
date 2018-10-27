package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Power extends RootNode {
    public Power(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        return Math.pow(l.get(0), l.get(1));
    }
}
