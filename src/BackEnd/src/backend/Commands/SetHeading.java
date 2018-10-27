package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class SetHeading extends RootNode {
    public SetHeading(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        myTurtle.setHeading(l.get(0));
        return l.get(0);
    }
}
