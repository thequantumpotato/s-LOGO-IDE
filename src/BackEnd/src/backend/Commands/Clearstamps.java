package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Clearstamps extends RootNode {
    public Clearstamps(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        myTurtle.Changed();
        myTurtle.clear();
        return 1;
    }
}
