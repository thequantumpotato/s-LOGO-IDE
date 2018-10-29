package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Heading extends RootNode {

    public Heading(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        return myTurtle.getDirection();
    }
}
