package backend;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Jose San Martin
 */
public class TurtleGroup extends Observable implements Turtle {
    private double direction;
    private double size;
    private Color color;
    private double x;
    private double y;
    private boolean penDown;
    private boolean showing;
    private List<TurtleLeaf> turtles;

    public TurtleGroup() {
        turtles = new ArrayList<>();
        addTurtle();
    }

    public void addTurtle() {
        turtles.add(new TurtleLeaf(turtles.size()));
    }

    public void removeTurtle(Integer id) {
        turtles.remove(id);
    }

    public Turtle getTurtleLeaf(Integer id){
        return turtles.get(id);
    }

    @Override
    public void move(double distance) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setX(x + distance * Math.cos(direction));
            leaf.setY(y + distance * Math.sin(direction));
        }
    }

    @Override
    public void setPosition(double x, double y) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setX(x);
            leaf.setY(y);
        }
    }

    @Override
    public void turn(double angle) {
        for (Turtle leaf : turtles) {
            leaf.turn(angle);
        }
    }

    @Override
    public void penUp() {

    }

    @Override
    public void penDown() {

    }

    @Override
    public void Changed() {
        for (Turtle leaf : turtles) {
            leaf.Changed();
        }
    }

    @Override
    public void clear() {
        for (Turtle leaf : turtles) {
            leaf.clear();
        }
    }

    @Override
    public void setHeading(double angle) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setDirection(angle);
        }
    }

    //CHANGE THE SIGNATURE LATER
    @Override
    public double getDirection() {
        List<Double> directions = new ArrayList<>();
        for (TurtleLeaf leaf : turtles) {
            directions.add(leaf.getDirection());
        }
        return 1;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean getIsPenDown() {
        return this.penDown;
    }

    @Override
    public boolean getIsShowing() {
        return this.showing;
    }

    @Override
    public void liftPenUp() {

    }

    @Override
    public void putPenDown() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void notifyObservers() {
        for (TurtleLeaf leaf : turtles) {
            System.out.println("My turtle after running command: " + leaf);
            System.out.println("Observer is going to be notified by turtle's change");
            // pass the TurtleLeaf to the DisplayView
            leaf.notifyObservers(leaf);
            System.out.println("Observer is already notified by turtle's change");
        }
    }

    @Override
    public void addAnObserver(Observer o) {
        for (TurtleLeaf leaf : turtles) {
            leaf.addObserver(o);
        }
    }
}
