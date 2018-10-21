package backend;

public interface Moveable {
    void move(double distance);

    void turn(double angle);

    void setPosition(double x, double y);

}
