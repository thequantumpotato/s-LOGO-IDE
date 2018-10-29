package backend;

import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;


/**
 * @author Jose San Martin
 */
public class TurtleLeaf extends Observable implements Turtle {

    private double direction;
    private double size;
    private double x;
    private double y;
    private boolean penDown;
    private boolean showing;
    private Integer id;
    private Color bgColor;
    private Color penColor;
    private double penSize;
    private boolean isActive;
    private boolean clearScreen;

    public Integer getId() {
        return id;
    }

    public TurtleLeaf(Integer id) {
        this.id = id;
        this.isActive = true;
        penDown = true;
        showing = true;
        bgColor = Color.BLACK;
        penColor = Color.WHITE;
        penSize = 1;
        isActive = true;
    }

    @Override
    public String toString() {
        return "TurtleLeaf{" +
                "direction=" + direction +
                ", size=" + size +
                ", x=" + x +
                ", y=" + y +
                ", penDown=" + penDown +
                ", showing=" + showing +
                ", id=" + id +
                '}';
    }

    public void move(double distance) {
        if(!isActive)
            return;
        this.setX(x + distance * Math.cos(direction));
        this.setY(y + distance * Math.sin(direction));
    }

    public void setPosition(double x, double y) {
        if(!isActive)
            return;
        this.setX(x);
        this.setY(y);
    }

    public void turn(double angle) {
        if(!isActive)
            return;
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
        if(!isActive)
            return;
        this.penDown = false;
    }

    @Override
    public void penDown() {
        if(!isActive)
            return;
        this.penDown = true;
    }

    public void Changed() {
        setChanged();
    }

    public void clear() {
        clearChanged();
    }

    public void setHeading(double angle) {
        if(!isActive)
            return;
        direction = angle;
    }

    public double getDirection() {
        return direction;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        if(!isActive)
            return;
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        if(!isActive)
            return;
        this.y = y;
    }

    public boolean getIsPenDown() {
        return this.penDown;
    }

    public boolean getIsShowing() {
        return this.showing;
    }


    public void show() {
        if(!isActive)
            return;
        showing = true;
    }

    public void setDirection(double Direction) {
        direction = Direction;
    }

    public void setBgColor(Color color){
        this.bgColor = color;
    }
    public void setPenColor(Color color){
        this.penColor = color;
    }
    public void setPenSize(double size){
        this.penSize = size;
    }

    public Color getPenColor(){
        return penColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public double getSize() {
        return size;
    }

    public double getPenSize() {
        return penSize;
    }

    public void hide() {
        if(!isActive)
            return;
        showing = false;
    }

    public void setActive(double idd){
        this.id = 1;
    }

    public void setInactive(double idd) {
        this.id = 0;
    }

    public Turtle getTurtleLeaf(Integer id){
        return this;
    }

    public void notifyAllObservers(Object o){
        return;
    }

    public void notifyAllObservers(){
        return;
    }

    public void addAnObserver(Observer o){
        this.addObserver(o);
    }

    public void clearScreen(){
        this.clearScreen = true;
        this.notifyObservers();
        this.clearScreen = false;
    }

    public boolean getClearScreen(){
        return this.clearScreen;
    }
}
