package backend;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * @author Jose San Martin, Harry Xie
 */
public class TurtleGroup implements Turtle {

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

    public Turtle getTurtleLeaf(Integer id) {
        return turtles.get(id);
    }

    @Override
    public void move(double distance) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setX(leaf.getX() + distance * Math.cos(leaf.getDirection()));
            leaf.setY(leaf.getY() + distance * Math.sin(leaf.getDirection()));
            leaf.notifyObservers();
        }
    }

    @Override
    public void setPosition(double x, double y) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setX(x);
            leaf.setY(y);
            leaf.notifyObservers();
        }
    }

    @Override
    public void turn(double angle) {
        for (TurtleLeaf leaf : turtles) {
            leaf.turn(angle);
            leaf.notifyObservers();
        }
    }

    @Override
    public void penUp() {
        for (TurtleLeaf leaf : turtles) {
            leaf.penUp();
            leaf.notifyObservers();
        }

    }

    @Override
    public void penDown() {
        for (TurtleLeaf leaf : turtles) {
            leaf.penDown();
            leaf.notifyObservers();
        }
    }

    @Override
    public void Changed() {
        for (TurtleLeaf leaf : turtles) {
            leaf.Changed();
        }
    }

    @Override
    public void clear() {
        for (TurtleLeaf leaf : turtles) {
            leaf.clear();
        }
    }

    @Override
    public void setHeading(double angle) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setDirection(angle);
            leaf.notifyObservers();
        }
    }

    //CHANGE THE SIGNATURE LATER
    @Override
    public double getDirection() {
        List<Double> directions = new ArrayList<>();
        for (TurtleLeaf leaf : turtles) {
            directions.add(leaf.getDirection());
            leaf.notifyObservers();
        }
        return 1;
    }

    @Override
    public double getX() {
        for (TurtleLeaf leaf : turtles) {
            return leaf.getX();
        }
        return 0;
    }

    @Override
    public void setX(double x) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setX(x);
        }
    }

    @Override
    public double getY() {
        for (TurtleLeaf leaf : turtles) {
            return leaf.getY();
        }
        return 0;
    }

    @Override
    public void setY(double y) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setY(y);
        }
    }

    //TODO: FIX THIS
    @Override
    public boolean getIsPenDown() {
        for (TurtleLeaf leaf : turtles) {
            return leaf.getIsPenDown();
        }
        return false;
    }

    //TODO: FIX THIS
    @Override
    public boolean getIsShowing() {
        for (TurtleLeaf leaf : turtles) {
            return leaf.getIsShowing();
        }
        return false;
    }


    public void show() {
        for (TurtleLeaf leaf : turtles) {
            leaf.show();
            leaf.notifyObservers();
        }
    }

    public void hide() {
        for (TurtleLeaf leaf : turtles) {
            leaf.hide();
            leaf.notifyObservers();
        }
        ;
    }

    public void notifyAllObservers() {
        for (TurtleLeaf leaf : turtles) {
            System.out.println("My turtle after running command: " + leaf);
            System.out.println("Observer is going to be notified by turtle's change");
            // pass the TurtleLeaf to the DisplayView
            leaf.notifyObservers();
            System.out.println("Observer is already notified by turtle's change");
        }
    }

    public void notifyAllObservers(Object o) {
        for (TurtleLeaf leaf : turtles) {
            System.out.println(leaf.getX());
            leaf.notifyObservers(o);
        }
    }

    public void setActive(double id) {
        boolean changed = false;
        for (TurtleLeaf leaf : turtles) {
            if (leaf.getId() == id) {
                leaf.setActive(id);
                leaf.notifyObservers();
                changed = true;
            }
        }
        //Add new turtle
        if (!changed) {
            addTurtle();
            turtles.get(0).Changed();
            turtles.get(0).notifyObservers("CHANGED!");
            turtles.get(0).clear();
        }
    }

    public void addAnObserver(Observer o) {
        for (TurtleLeaf leaf : turtles) {
            leaf.addAnObserver(o);
        }
    }

    public Color getPenColor() {
        return turtles.get(0).getPenColor();
    }

    public void setPenColor(Color color) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setPenColor(color);
            leaf.notifyObservers();
        }
    }

    public Color getBgColor() {
        return turtles.get(0).getBgColor();
    }

    public void setBgColor(Color color) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setBgColor(color);
            leaf.notifyObservers();
        }
    }

    public double getPenSize() {
        return turtles.get(0).getPenSize();
    }

    public void setPenSize(double size) {
        for (TurtleLeaf leaf : turtles) {
            leaf.setPenSize(size);
            leaf.notifyObservers();
        }
    }

    @Override
    public void clearScreen() {
        turtles.get(0).clearScreen();
        turtles.get(0).notifyObservers("clear");
    }

    public double numTurtles() {
        return turtles.size();
    }

    public void setInactive(double id) {
        for (TurtleLeaf leaf : turtles) {
            if (leaf.getId() == id) {
                leaf.setInactive(id);
            }
        }
    }

    public void reset() {
        TurtleLeaf first = turtles.get(0); // remember first item
        turtles.clear(); // clear complete list
        turtles.add(first); // add first item
        setPosition(0, 0);
    }

    public void stamp(){
        for (TurtleLeaf leaf : turtles) {
            leaf.stamp();
            leaf.notifyObservers();
        }
    }


    public void removeStamp(){
        for (TurtleLeaf leaf : turtles) {
            leaf.removeStamp();
            leaf.notifyObservers();
        }
    }
    public boolean isStamped(){
        for (TurtleLeaf leaf : turtles) {
            leaf.isStamped();
            leaf.notifyObservers();
        }
        return true;
    }

}
