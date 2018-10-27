package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class YCoordinate extends RootNode {
    public YCoordinate(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        return myTurtle.getY();
    }
}
