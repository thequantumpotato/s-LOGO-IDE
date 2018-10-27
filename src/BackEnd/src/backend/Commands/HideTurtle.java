package backend.Commands;

import backend.Turtle;
import backend.Storage.Storage;

import java.util.List;

public class HideTurtle extends RootNode {

    public HideTurtle(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        myTurtle.hide();
        return 1;
    }
}
