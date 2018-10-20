package backend;

public interface Moveable {
    void move(double distance);

    void turn(double angle);

    void penUp();

    void penDown();

    void show();

    void hide();

    void setPosition(double x, double y);

}
