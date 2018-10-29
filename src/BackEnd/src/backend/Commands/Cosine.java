package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Cosine extends RootNode {

    public Cosine(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        return Math.cos(l.get(0));
    }
}
