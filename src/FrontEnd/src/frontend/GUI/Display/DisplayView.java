package frontend.GUI.Display;

import backend.Turtle;
import backend.TurtleLeaf;
import frontend.Util.Coordinate;
import frontend.Util.Pen;
import frontend.API.SubView;
import frontend.GUI.View;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static frontend.GUI.Display.TurtleView.TURTLE_SIZE;

/**
 * DisplayView contains the display of the turtle as well as the panel for the user to change the
 * settings (eg. Pen color, background color, and turtle image). It is the playground for the turtle
 * to move around and draw different shapes.
 *
 * @author Vincent Liu
 * @author Benjamin Xu
 */

public class DisplayView implements SubView, Observer {

    public static final String TURTLE_IMAGE = "turtle.png";
    public static final Color DEFAULT_BG_COLOR = Color.BLACK;
    public static final int TURTLE_DEFAULT_X = 275;
    public static final int TURTLE_DEFAULT_Y = 250;
    public static final int DEFAULT_BG_WIDTH = 800;
    public static final int DEFAULT_BG_HEIGHT = 800;

    private ArrayList<Turtle> myTurtleInfo;
    private ArrayList<TurtleView> myTurtles;

    private View myView;
    private ScrollPane scrollPane;
    private Group root;
    private Rectangle bg;
    private Pen myPen;

    public DisplayView(View myView_,Turtle turtle) {
        myView = myView_;
        scrollPane = new ScrollPane();
        root = new Group();
        //create bg
        bg = new Rectangle(DEFAULT_BG_WIDTH, DEFAULT_BG_HEIGHT, DEFAULT_BG_COLOR);
        //create pen
        myPen = new Pen(new Coordinate(TURTLE_DEFAULT_X + TURTLE_SIZE / 2, TURTLE_DEFAULT_Y + TURTLE_SIZE / 2, 0));
        myPen.setRoot(root);
        //add turtle to scroll pane
        root.getChildren().add(bg);
        //create turtle array and default turtle
        myTurtleInfo = new ArrayList<>();
        myTurtleInfo.add(turtle);
        //create turtleview array and default turtleview
        myTurtles = new ArrayList<>();
        myTurtles.add(new TurtleView(myPen,root));
        scrollPane.setContent(root);
        TurtleManager turtleManager = new TurtleManager(root);
        turtleManager.createTurtle("1");
        turtleManager.setDuration(10);
        turtleManager.moveTurtle("1",new Coordinate(500,300,0));
        turtleManager.updateTurtles();
    }

    public void clearBg() {
        myPen.eraseAll();
    }

    public void changeBgColor(Color bgColor) {
        bg.setFill(bgColor);
    }

    public void setPenDown(boolean state) {
        for(TurtleView turtle : myTurtles){
            if(state){
                turtle.penDown();
            }
            else{
                turtle.penUp();
            }
        }
    }

    public void changePenColor(Color penColor) {
        myPen.setPenColor(penColor);
    }

    public void changePenSize(double size) {
        myPen.setPenSize(size);
    }

    public void hideTurtle() {
        for(TurtleView turtle : myTurtles){
            turtle.hide();
        }
    }

    public void hideTurtle(int index) {
        if(index>0 && index<myTurtles.size()){
            myTurtles.get(index).hide();
        }
    }

    public void showTurtle() {
        for(TurtleView turtle : myTurtles){
            turtle.show();
        }
    }

    public void showTurtle(int index) {
        if(index>0 && index<myTurtles.size()){
            myTurtles.get(index).show();
        }
    }

    public void changeTurtleSize(double size) {
        for(TurtleView turtle : myTurtles){
            turtle.setSize(size);
        }
    }

    public void changeTurtleSize(int index, double size) {
        if(index>0 && index<myTurtles.size()){
            myTurtles.get(index).setSize(size);
        }
    }

    public void changeTurtleImg(Image image) {
        for(TurtleView turtle : myTurtles){
            turtle.setSprite(image);
        }
    }

    public void changeTurtleImg(int index, Image image) {
        if(index>0 && index<myTurtles.size()){
            myTurtles.get(index).setSprite(image);
        }
    }


    public void changeAnimationSpeed(Double time) {
        for(TurtleView turtle : myTurtles){
            turtle.setSpeed(time);
        }
    }

    //TODO: REIMPLEMENT MOVEMENT SYSTEM TO BE RELATIVE RATHER THAN ABSOLUTE
    public void updateTurtle(Coordinate newpos) {
        //increase background size if needed
        if (newpos.getX() > bg.getX() + bg.getWidth() || newpos.getX() < bg.getX() ||
                newpos.getY() > bg.getY() + bg.getHeight() || newpos.getY() < bg.getY()) {
            expandBackground(Math.max(Math.abs(newpos.getX() - bg.getX()), Math.abs(newpos.getY() - bg.getY())));
        }
        //move turtle
        for(TurtleView turtle: myTurtles){
            turtle.moveTo(newpos);
        }
    }

    public void updateTurtle(int index, Coordinate newpos) {
        //increase background size if needed
        if (newpos.getX() > bg.getX() + bg.getWidth() || newpos.getX() < bg.getX() ||
                newpos.getY() > bg.getY() + bg.getHeight() || newpos.getY() < bg.getY()) {
            expandBackground(Math.max(Math.abs(newpos.getX() - bg.getX()), Math.abs(newpos.getY() - bg.getY())));
        }
        //move turtle
        if(index>0 && index<myTurtles.size()){
            myTurtles.get(index).moveTo(newpos);
        }
    }

    public void playAnims() {
        System.out.println("Playing anims");
        for(TurtleView turtle : myTurtles){
            turtle.playAnimation();
        }
    }

    public void playAnims(int index) {
        System.out.println("Playing anims");
        if(index>0 && index<myTurtles.size()){
            myTurtles.get(index).playAnimation();
        }
    }

    private void expandBackground(double amount) {
        System.out.println("Expanding bg by " + amount);
        bg.setX(bg.getX() - amount);
        bg.setY(bg.getY() - amount);
        bg.setWidth(bg.getWidth() + 2 * amount);
        bg.setHeight(bg.getHeight() + 2 * amount);
    }

    private Coordinate adjustPosition(double x, double y, double theta) {
        double newtheta = Math.toDegrees((2 * Math.PI) - theta);
        return new Coordinate(x + TURTLE_DEFAULT_X, TURTLE_DEFAULT_Y - y, newtheta);
    }

    @Override
    public Node getView() {
        return scrollPane;
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO: update this method after turtle has new getID method
        System.out.println("Updated Turtle received by DisplayView:"+ arg);
        TurtleLeaf updatedTurtle = (TurtleLeaf) arg;
        if (arg != null) {
            Turtle oldTurtle = myTurtleInfo.get(updatedTurtle.getId());
            // change the corresponding turtle's position in the turtleInfo list
            oldTurtle.setX(updatedTurtle.getX());
            playAnims();
        } else {
            System.out.println("null");
            myTurtleInfo.set(0,(Turtle)o);
            System.out.println(myTurtleInfo.get(0).getX() + " " + myTurtleInfo.get(0).getY() + " " + myTurtleInfo.get(0).getDirection());
            updateTurtle(adjustPosition(myTurtleInfo.get(0).getX(), myTurtleInfo.get(0).getY(), myTurtleInfo.get(0).getDirection()));
        }
    }
}
