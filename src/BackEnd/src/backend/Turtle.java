package backend;

import javafx.scene.paint.Color;

import java.util.Observer;

/**
 * @author Jose San Martin, Harry Xie
 * An interface for our turtles
 * Implements the Composite design pattern
 */
public interface Turtle {

    /**
     * Allows the turtle to move forward or backward
     */

    void move(double distance);

    /**
     * Allows the turtle to turn direction by the parameter angle
     */

    void turn(double angle);

    /**
     * Sets the turtles pen to up
     */

    void penUp();

    /**
     * Sets the turtles pen to down
     */

    void penDown();

    /**
     * Shows the turtle
     */
    void show();

    /**
     * Hides the turtle
     */

    void hide();

    /**
     * Sets a turtle at a given x and a given y
     */

    void setPosition(double x, double y);

    /**
     * Notifies the listeners that the turtle observable is about to change
     */

    void Changed();

    /**
     * Notifies the listeners that the turtle observable is done changing
     */

    void clear();

    /**
     * Sets the turtle to look towards a certain degree
     */

    void setHeading(double angle);

    /**
     *  Returns the direction of the turtle
     */

    double getDirection();

    /**
     * Returns the current X position of the turtle
     */
    double getX();

    /**
     * Sets the current X position of the turtle
     */

    void setX(double x);

    /**
     * gets the current Y position of the turtle
     */
    double getY();

    /**
     * Sets the current Y position of the turtle
     */
    void setY(double y);

    /**
     * Returns a boolean indication if the turtles pen is down
     */

    boolean getIsPenDown();

    /**
     * Returns a boolean indicating if the turtle is showing
     */

    boolean getIsShowing();

    /**
     * Returns the current background color
     */
    Color getBgColor();

    /**
     * Sets the background color
     */

    void setBgColor(Color color);

    /**
     * Gets the current pen color
     */

    Color getPenColor();

    /**
     * Sets the current pen color
     */

    void setPenColor(Color color);

    /**
     * Gets the current pen size
     */

    double getPenSize();

    /**
     * Sets the pen size
     */

    void setPenSize(double size);

    /**
     * Adds an observer to each observable turtle
     */

    void addAnObserver(Observer o);

    /**
     * Notifies all observers of the change and sends an object
     */

    void notifyAllObservers(Object o);

    /**
     * Notifies all observers of the change
     */

    void notifyAllObservers();

    /**
     * Sets the turtle to be active
     */

    void setActive(double id);

    /**
     * Sets the turtle to be inactive
     */

    void setInactive(double id);

    /**
     * Returns a turtle with the given id
     */

    Turtle getTurtleLeaf(Integer id);

    /**
     * Clears the screen
     */

    void clearScreen();

    void stamp();

    void clearStamp();


}
