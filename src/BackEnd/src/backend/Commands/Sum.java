package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Sum extends RootNode {
    public Sum(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        System.out.println(l.get(0) + l.get(1));
        return l.get(0) + l.get(1);
    }
}
