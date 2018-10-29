package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.ArrayList;
import java.util.List;

public class SetTowards extends RootNode {

    public SetTowards(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Double> l = parseDoubles(runChildren());
        double x = l.get(0);
        double y = l.get(1);
        double newDir = angleWithXAxis(x, y);
        double oldDir = myTurtle.getDirection();
        myTurtle.Changed();
        myTurtle.setHeading(newDir);
        myTurtle.clear();
        return Math.abs(newDir - oldDir);
    }

    /**
     * return angle with x axis given x and y
     *
     * @param x
     * @param y
     * @return positive number between 0 and 2PI
     */
    private double angleWithXAxis(double x, double y) {
        double res = 0;
        if (x == 0) {
            if (y > 0) {
                res = Math.PI / 2;
            }
            if (y < 0) {
                res = 1.5 * Math.PI;
            }
            return res;
        }
        double tmp = Math.atan(y / x);
        if (x > 0) {
            res = tmp;
        }
        if (x < 0) {
            res = Math.PI + tmp;
        }
        if (res < 0) {
            res += 2 * Math.PI;
        }
        return res;
    }

//    public static void main(String[] args){
//        SetTowards s = new SetTowards(new Storage(), new Turtle(), new ArrayList<Node>());
//        System.out.println(s.angleWithXAxis(1,1));
//    }
}
