package frontend.Util;

/**
 * A customized class to keep track of the position and angle of the turtle.
 * It uses double because one of the operations is quotient.
 *
 * @author Vincent Liu
 */

public class Coordinate {
    private double x;
    private double y;
    private double angle;

    public Coordinate(double x_, double y_, double angle_) {
        x = x_;
        y = y_;
        angle = angle_;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}

