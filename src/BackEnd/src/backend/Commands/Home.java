package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Home extends RootNode {

    public Home(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        double dist = calcDist(myTurtle.getX(), myTurtle.getY(), 0, 0);
        myTurtle.Changed();
        myTurtle.setPosition(0, 0);
        myTurtle.clear();
        return dist;
    }

    private double calcDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}
