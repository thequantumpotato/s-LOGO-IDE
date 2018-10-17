package backend;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * represents a turtle on the screen
 */
public class Turtle implements Moveable{

    private double direction;
    private double size;
    private Color color;
    private ImageView imageView;
    private boolean penDown;
    private boolean showing;

    public Turtle(){

    }

    public void move(double distance){
        double x = imageView.getX();
        double y = imageView.getY();
        imageView.setX(x + distance * Math.cos(direction));
        imageView.setY(y + distance * Math.sin(direction));
    }

    public void setPosition(double x, double y){
        imageView.setX(x);
        imageView.setY(y);
    }

    public void turn(double angle){
        direction += angle / 360 * 2 * Math.PI;
    }

    public void setHeading(double angle){
        direction = angle;
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
