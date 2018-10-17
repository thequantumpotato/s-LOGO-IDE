package backend;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * represents a turtle on the screen
 */
public class Turtle {

    private double direction;
    private double size;
    private Color color;
    private double x;
    private double y;
    private boolean penDown;
    private boolean showing;

    public Turtle(){

    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void move(double distance){
        this.setX(x + distance * Math.cos(direction));
        this.setY(y + distance * Math.sin(direction));
    }

    public void setPosition(double x, double y){
        this.setX(x);
        this.setY(y);
    }

    public void turn(double angle){
        direction += angle / 360 * 2 * Math.PI;
        if(direction >= 2*Math.PI){
            direction -= 2*Math.PI;
        }
        if(direction <= 0){
            direction += 2*Math.PI;
        }
    }

    public void setHeading(double angle){
        direction = angle;
    }

    public double getDirection(){
        return direction;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void penUp(){

    }

    public void penDown(){

    }

    public void show(){

    }

    public void hide(){

    }

}
