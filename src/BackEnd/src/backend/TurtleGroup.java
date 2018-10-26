package backend;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Jose San Martin
 */
public class TurtleGroup extends Observable implements Turtle{
    private double direction;
    private double size;
    private Color color;
    private double x;
    private double y;
    private boolean penDown;
    private boolean showing;
    private List<TurtleLeaf> turtles;

    public TurtleGroup(){
        turtles = new ArrayList<>();
    }


    public void move(double distance) {
        for(TurtleLeaf leaf:turtles){
            leaf.setX(x + distance * Math.cos(direction));
            leaf.setY(y + distance * Math.sin(direction));
        }
    }

    public void addTurtle(){
        turtles.add(new TurtleLeaf());
    }
    public void setPosition(double x, double y) {
        for(TurtleLeaf leaf:turtles){
            leaf.setX(x);
            leaf.setY(y);
        }
    }

    public void turn(double angle) {
        for(Turtle leaf:turtles){
            leaf.turn(angle);
        }
    }

    @Override
    public void penUp() {

    }

    @Override
    public void penDown() {

    }

    public void Changed() {
        for(Turtle leaf:turtles){
            leaf.Changed();
        }
    }

    public void clear() {
        for(Turtle leaf:turtles){
            leaf.clear();
        }
    }

    public void setHeading(double angle) {
        for(TurtleLeaf leaf:turtles){
            leaf.setDirection(angle);
        }
    }

    //CHANGE THE SIGNATURE LATER
    public double getDirection() {
        List<Double> directions = new ArrayList<>();
        for(TurtleLeaf leaf:turtles){
            directions.add(leaf.getDirection());
        }
        return 1;
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

    @Override
    public void notifyObservers(){
        for(TurtleLeaf leaf:turtles){
            leaf.notifyObservers();
        }
    }

    public void addAnObserver(Observer o) {
        for(TurtleLeaf leaf:turtles){
            leaf.addObserver(o);
        }
    }


    public void hide() {

    }
}
