package backend;

public interface Moveable {
    public void move(double distance);

    public void turn(double angle);

    public void penUp();

    public void penDown();

    public void show();

    public void hide();

    public void setPosition(double x, double y);



}
