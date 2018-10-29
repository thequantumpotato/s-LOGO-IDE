package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;
import backend.TurtleGroup;

import java.util.List;

public class Turtles extends RootNode {
    public Turtles(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        return ((TurtleGroup) myTurtle).numTurtles();
    }
}
