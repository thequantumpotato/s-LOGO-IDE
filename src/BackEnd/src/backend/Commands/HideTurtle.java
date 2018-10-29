package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class HideTurtle extends RootNode {

    public HideTurtle(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        myTurtle.Changed();
        myTurtle.hide();
        myTurtle.clear();
        return 1;
    }
}
