package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;
import backend.TurtleGroup;

import java.util.List;

public class ClearScreen extends RootNode {
    public ClearScreen(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        myTurtle.Changed();
        myTurtle.clearScreen();
        ((TurtleGroup) myTurtle).reset();
        myTurtle.clear();
        return 1;
    }
}
