package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class ShowTurtle extends RootNode {
    public ShowTurtle(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        myTurtle.show();
        return 1;
    }
}
