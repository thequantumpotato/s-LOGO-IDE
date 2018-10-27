package backend.Commands;

import backend.Turtle;

import java.util.List;

public class Forward extends TurtleNode{

    public Forward(Turtle t) {
        super(t);
    }

    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        myTurtle.move(l.get(0));
        System.out.println(myTurtle.toString());
        return l.get(0);
    }

    public int getNumChildren() {
        return children.size();
    }

    public List<Node> getChildren() {
        return children;
    }
}
