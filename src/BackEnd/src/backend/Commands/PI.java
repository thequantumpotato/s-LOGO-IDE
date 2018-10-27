package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class PI extends RootNode {
    public PI(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        return Math.PI;
    }
}
