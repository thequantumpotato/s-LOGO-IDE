package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class ClearStamp extends RootNode {

    public ClearStamp(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        myTurtle.Changed();
        myTurtle.clearStamp();
        return 1;
    }
}
