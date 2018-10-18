package frontend;

/**
 * A customized class to keep track of the position and angle of the turtle.
 * It uses integer instead of double because usually in the command the numbers are all integers.
 *
 * @author Vincent Liu
 */

public class Coordinate {
    private int x;
    private int y;
    private int angle;


    public Coordinate(int x_, int y_, int angle_) {
        x = x_;
        y = y_;
        angle = angle_;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}

