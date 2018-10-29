package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class PenUp extends RootNode {
    public PenUp(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        myTurtle.Changed();
        myTurtle.penUp();
        myTurtle.clear();
        return 1;
    }
}
