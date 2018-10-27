package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Sine extends RootNode {
    public Sine(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        return Math.sin(l.get(0));
    }
}
