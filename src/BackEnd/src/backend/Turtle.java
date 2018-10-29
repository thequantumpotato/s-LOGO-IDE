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

    Color getBgColor();

    void setBgColor(Color color);

    Color getPenColor();

    void setPenColor(Color color);

    double getPenSize();

    void setPenSize(double size);

    void addAnObserver(Observer o);

    void notifyAllObservers(Object o);

    void notifyAllObservers();

    void setActive(double id);

    void setInactive(double id);

    Turtle getTurtleLeaf(Integer id);

    void clearScreen();


}
