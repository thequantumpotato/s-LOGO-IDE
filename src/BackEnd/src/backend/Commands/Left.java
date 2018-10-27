package backend.Commands;

import backend.Turtle;
import backend.Storage.Storage;

import java.util.List;

public class Left extends RootNode{

    public Left(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        myTurtle.turn(l.get(0));
        return l.get(0);
    }
}
