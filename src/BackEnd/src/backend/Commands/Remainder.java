package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Remainder extends RootNode {
    public Remainder(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Integer> l = parseIntegers(parseDoubles(runChildren()));
        return l.get(0) % l.get(1);
    }
}
