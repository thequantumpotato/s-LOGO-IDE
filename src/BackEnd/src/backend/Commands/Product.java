package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Product extends RootNode {
    public Product(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        return l.get(0) * l.get(1);
    }
}
