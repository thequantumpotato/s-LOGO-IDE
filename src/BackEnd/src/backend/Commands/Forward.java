package backend.Commands;

import backend.Turtle;
import backend.Storage.Storage;

import java.util.List;

public class Forward extends RootNode{

    public Forward(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        myTurtle.Changed();
        myTurtle.move(l.get(0));
        myTurtle.clear();
        return l.get(0);
    }
}
