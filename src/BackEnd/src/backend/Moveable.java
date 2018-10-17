package backend;

public interface Moveable {
    public void move(double distance);

    public void turn(double angle);

    public void drawCircle(double centerX, double centerY, double radius);
}
