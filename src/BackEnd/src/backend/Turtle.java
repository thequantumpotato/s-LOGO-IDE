package backend;

import frontend.Coordinate;
import javafx.scene.paint.Color;

/**
 * represents a turtle on the screen
 */
public class Turtle implements Moveable {

    private double direction;
    private double size;
    private Color color;
    private double x;
    private double y;
    private boolean penDown;
    private boolean showing;

    public Turtle() {
        x = 0.0;
        y = 0.0;
        direction = 0.0;
    }

    public Coordinate getPos() {
        return new Coordinate(x, y, direction);
    }

    public void move(double distance) {
        this.setX(x + distance * Math.cos(direction));
        this.setY(y + distance * Math.sin(direction));
    }

    public void setPosition(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public void turn(double angle) {
        direction += angle / 360 * 2 * Math.PI;
        if (direction >= 2 * Math.PI) {
            direction -= 2 * Math.PI;
        }
        if (direction <= 0) {
            direction += 2 * Math.PI;
        }
    }

    @Override
    public void penUp() {

    }

    @Override
    public void penDown() {

    }

    public void setHeading(double angle) {
        direction = angle;
    }

    public double getDirection() {
        return direction;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean getIsPenDown() {
        return this.penDown;
    }

    public boolean getIsShowing() {
        return this.showing;
    }

    public void liftPenUp() {

    }

    public void putPenDown() {

    }

    public void show() {

    }

    public void hide() {

    }

}
