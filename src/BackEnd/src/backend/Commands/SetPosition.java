package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class SetPosition extends RootNode {

    public SetPosition(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        double oldx = myTurtle.getX();
        double oldy = myTurtle.getY();
        myTurtle.setPosition(l.get(0), l.get(1));
        return Math.pow(oldx - l.get(0), 2) + Math.pow(oldy - l.get(1), 2);
    }
}
