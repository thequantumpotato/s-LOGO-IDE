package frontend;

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

    public int getY() {
        return y;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

