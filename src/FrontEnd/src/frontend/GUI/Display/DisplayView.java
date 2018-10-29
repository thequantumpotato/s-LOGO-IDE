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

    private TurtleManager myTurtleManager;
    /*private ArrayList<Turtle> myTurtleInfo;
    private ArrayList<TurtleView> myTurtles;*/

    private View myView;
    private ScrollPane scrollPane;
    private Group root;
    private Rectangle bg;
    private Pen myPen;

    public DisplayView(View myView_,Turtle turtle) {
        myView = myView_;
        scrollPane = new ScrollPane();
        root = new Group();
        bg = new Rectangle(DEFAULT_BG_WIDTH, DEFAULT_BG_HEIGHT, DEFAULT_BG_COLOR);
        myPen = new Pen(new Coordinate(TURTLE_DEFAULT_X + TURTLE_SIZE / 2, TURTLE_DEFAULT_Y + TURTLE_SIZE / 2, 0));
        myPen.setRoot(root);
        root.getChildren().add(bg);
        scrollPane.setContent(root);
        myTurtleManager = new TurtleManager(myView,root);
        myTurtleManager.createTurtle("0");
    }

    public void clearBg() {
        myPen.eraseAll();
    }

    public void changeBgColor(Color bgColor) {
        bg.setFill(bgColor);
    }

    public void setPenDown(boolean state) {
        if(state){
            myTurtleManager.penDown();
        }
        else{
            myTurtleManager.penUp();
        }
    }

    public void changePenColor(Color penColor) {
        myTurtleManager.setPenColor(penColor);
    }

    public void changePenSize(double size) {
        myTurtleManager.setPenSize(size);
    }

    /*public void hideTurtle() {
        for(TurtleView turtle : myTurtles){
            turtle.hide();
        }
    }*/

    public void hideTurtle(String id) {
        myTurtleManager.hide(id);
    }

    /*public void showTurtle() {
        for(TurtleView turtle : myTurtles){
            turtle.show();
        }
    }*/

    public void showTurtle(String id) {
        myTurtleManager.show(id);
    }

    public void changeTurtleSize(double size) {
        myTurtleManager.setTurtleSize(size);
    }

    public void changeTurtleSize(String id, double size) {
        myTurtleManager.setTurtleSize(id,size);
    }

    public void changeTurtleImg(Image image) {
        myTurtleManager.setTurtleImage(image);
    }

    public void changeAnimationSpeed(Double time) {
        myTurtleManager.setDuration(time);
    }

    /*//TODO: REIMPLEMENT MOVEMENT SYSTEM TO BE RELATIVE RATHER THAN ABSOLUTE
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
    }*/

    /*public void updateTurtle(int index, Coordinate newpos) {
        //increase background size if needed
        if (newpos.getX() > bg.getX() + bg.getWidth() || newpos.getX() < bg.getX() ||
                newpos.getY() > bg.getY() + bg.getHeight() || newpos.getY() < bg.getY()) {
            expandBackground(Math.max(Math.abs(newpos.getX() - bg.getX()), Math.abs(newpos.getY() - bg.getY())));
        }
        //move turtle
        if(index>0 && index<myTurtles.size()){
            myTurtles.get(index).moveTo(newpos);
        }
    }*/

    /*public void playAnims() {
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
    }*/

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
        TurtleLeaf updatedTurtle = (TurtleLeaf) o;
        if (arg != null) {
            System.out.println("Updating animations");
            //Change displayview settings based on info stored in turtle
            //getSettings((TurtleLeaf) o);
            getSettings((TurtleLeaf) o);
            myTurtleManager.updateTurtles();
        } else {
            System.out.println("Received update");
            /*myTurtleInfo.set(0,(Turtle)o);
            System.out.println(myTurtleInfo.get(0).getX() + " " + myTurtleInfo.get(0).getY() + " " + myTurtleInfo.get(0).getDirection());
            updateTurtle(adjustPosition(myTurtleInfo.get(0).getX(), myTurtleInfo.get(0).getY(), myTurtleInfo.get(0).getDirection()));*/
            Coordinate newPosition = adjustPosition(((TurtleLeaf) o).getX(),((TurtleLeaf) o).getY(),((TurtleLeaf) o).getDirection());
            if (newPosition.getX() > bg.getX() + bg.getWidth() || newPosition.getX() < bg.getX() ||
                    newPosition.getY() > bg.getY() + bg.getHeight() || newPosition.getY() < bg.getY()) {
                expandBackground(Math.max(Math.abs(newPosition.getX() - bg.getX()), Math.abs(newPosition.getY() - bg.getY())));
            }
            myTurtleManager.moveTurtle(String.valueOf(((TurtleLeaf) o).getId()),newPosition);
        }
    }

    private void getSettings(TurtleLeaf o) {
        myView.changePenDown(o.getIsPenDown());
        if(o.getIsShowing()){
            showTurtle(String.valueOf(o.getId()));
        }
        else{
            hideTurtle(String.valueOf(o.getId()));
        }
        myView.changeBgColor(o.getBgColor());
        myView.changePenColor(o.getPenColor());
        myView.changePenSize(o.getPenSize());
    }

}
