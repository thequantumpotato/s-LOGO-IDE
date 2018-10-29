package backend;

import javafx.scene.paint.Color;

import java.util.Observer;

/**
 * @author Jose San Martin
 * An interface for our turtles
 * Implements the Composite design pattern
 */
public interface Turtle {

    void move(double distance);

    void turn(double angle);

    void penUp();

    void penDown();

    void show();

    void hide();

    void setPosition(double x, double y);

    void Changed();

    void clear();

    void setHeading(double angle);

    double getDirection();

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    boolean getIsPenDown();

    boolean getIsShowing();

    void setBgColor(Color color);
    void setPenColor(Color color);
    void setPenSize(double size);
    void setShape(int shape);



    void addAnObserver(Observer o);

    void notifyAllObservers(Object o);
    void notifyAllObservers();

    void setActive(double id);
    Turtle getTurtleLeaf(Integer id);

}
