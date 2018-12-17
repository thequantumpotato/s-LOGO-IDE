package frontend.GUI.Display;

import backend.TurtleLeaf;
import frontend.API.SubView;
import frontend.GUI.View;
import frontend.Util.Coordinate;
import frontend.Util.Pen;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import java.util.Observer;


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
    public static final int TURTLE_SIZE = 25;
    public static final int TURTLE_DEFAULT_Y = 250;
    public static final int DEFAULT_BG_WIDTH = 800;
    public static final int DEFAULT_BG_HEIGHT = 800;

    private TurtleManager myTurtleManager;
    private View myView;
    private ScrollPane scrollPane;
    private Group root;
    private Rectangle bg;
    private Pen myPen;

    /** Constructor takes in the {@code View} that contains it
     *  @param myView_ The {@code View} that is holding the {@code DisplayView}*/
    public DisplayView(View myView_) {
        myView = myView_;
        scrollPane = new ScrollPane();
        root = new Group();
        bg = new Rectangle(DEFAULT_BG_WIDTH, DEFAULT_BG_HEIGHT, DEFAULT_BG_COLOR);
        myPen = new Pen(new Coordinate(TURTLE_DEFAULT_X + TURTLE_SIZE / 2, TURTLE_DEFAULT_Y + TURTLE_SIZE / 2, 0));
        myPen.setRoot(root);
        root.getChildren().add(bg);
        scrollPane.setContent(root);
        myTurtleManager = new TurtleManager(root, myView);
        myTurtleManager.createTurtle("0");
    }

    /** Clears the display of lines and all turtles except default, which will appear at origin*/
    public void clear() {
        System.out.println("Clear screen!");
        myPen.eraseAll();
        myTurtleManager.reset();
        myTurtleManager.createTurtle("0");
        myView.registerDisplay(myView.getTurtle());
    }

    /** Change the background color
     *  @param bgColor The {@code Color} representing the new background color*/
    public void changeBgColor(Color bgColor) {
        bg.setFill(bgColor);
    }

    /** Sets the pen to be up or down
     *  @param state True means pen is down, false means pen is up */
    public void setPenDown(boolean state) {
        if (state) {
            myTurtleManager.penDown();
        } else {
            myTurtleManager.penUp();
        }
    }

    /** Changes the color of the pen
     *  @param penColor The {@code Color} to change the pen to */
    public void changePenColor(Color penColor) {
        myTurtleManager.setPenColor(penColor);
    }

    /** Returns the current {@code Color} being used by the pen */
    public Color getPenColor() {
        return myTurtleManager.getPenColor();
    }

    /** Changes the size of the pen
     *  @param size The new stroke width of the pen*/
    public void changePenSize(double size) {
        myTurtleManager.setPenSize(size);
    }

    /** Hides the turtle with the given ID
     *  @param id THe identifier for the turtle */
    public void hideTurtle(String id) {
        myTurtleManager.hide(id);
    }

    /** Shows the turtle with the given ID
     *  @param id THe identifier for the turtle */
    public void showTurtle(String id) {
        myTurtleManager.show(id);
    }

    /** Changes the size of all turtles to the specified size
     *  @param size The new size of all turtles*/
    public void changeTurtleSize(double size) {
        myTurtleManager.setTurtleSize(size);
    }

    /** Changes the size of only a specified turtle by ID
     *  @param id The identifier for the turtle
     *  @param size The new size of the turtle */
    public void changeTurtleSize(String id, double size) {
        myTurtleManager.setTurtleSize(id, size);
    }

    public void changeOneTurtleImg(Image image, int id){
        myTurtleManager.setOneTurtleImage(image, id);
    }

    /** Change the sprite for all turtles
     *  @param image The new {@code Image} to use as the turtle sprite*/
    public void changeTurtleImg(Image image) {
        myTurtleManager.setTurtleImage(image);
    }

    /** Changes the speed of all turtle animations
     *  @param time The length of time in seconds for the turtle to carry out an action*/
    public void changeAnimationSpeed(Double time) {
        myTurtleManager.setDuration(time);
    }

    /** Expand the background uniformly by a specified amount in both the X and Y direction
     *  @param amount The amount in pixels to expand the background by*/
    private void expandBackground(double amount) {
        bg.setX(bg.getX() - amount);
        bg.setY(bg.getY() - amount);
        bg.setWidth(bg.getWidth() + 2 * amount);
        bg.setHeight(bg.getHeight() + 2 * amount);
    }

    /** Convert coordinates from Cartesian to JavaFX and returns a {@code Coordinate} object
     *  @param x The Cartesian X position
     *  @param y The Cartesian Y position
     *  @param theta The Cartesian angle */
    private Coordinate adjustPosition(double x, double y, double theta) {
        double newtheta = Math.toDegrees((2 * Math.PI) - theta);
        return new Coordinate(x + TURTLE_DEFAULT_X, TURTLE_DEFAULT_Y - y, newtheta);
    }

    /** Returns the {@code ScrollPane} holding all the graphics */
    @Override
    public Node getView() {
        return scrollPane;
    }

    /** Update method which responds upon being notified by the backend
     *  Updates settings and turtle positions with animations based on backend information*/
    @Override
    public void update(Observable o, Object arg) {
        //TODO: update this method after turtle has new getID method
        System.out.println("Updated Turtle received by DisplayView:" + arg);
        if (arg != null) {
            if (arg instanceof String) {
                if (((String) arg).equalsIgnoreCase("CHANGED!")) {
                    myView.registerDisplay(myView.getTurtle());
                    myTurtleManager.makeFake();
                } else if (((String) arg).equalsIgnoreCase("clear")) {
                    clear();
                }
            } else if (arg instanceof Boolean) {
                getSettings((TurtleLeaf) o);
                myTurtleManager.updateTurtles();
            }
        } else {
            myTurtleManager.hideFake();
            if (!myTurtleManager.contains(String.valueOf(((TurtleLeaf) o).getId()))) {
                System.out.println("creating new turtle " + String.valueOf(((TurtleLeaf) o).getId()));
                myTurtleManager.createTurtle(String.valueOf(((TurtleLeaf) o).getId()));
            }
            Coordinate newPosition = adjustPosition(((TurtleLeaf) o).getX(), ((TurtleLeaf) o).getY(), ((TurtleLeaf) o).getDirection());
            if (newPosition.getX() > bg.getX() + bg.getWidth() || newPosition.getX() < bg.getX() ||
                    newPosition.getY() > bg.getY() + bg.getHeight() || newPosition.getY() < bg.getY()) {
                expandBackground(Math.max(Math.abs(newPosition.getX() - bg.getX()), Math.abs(newPosition.getY() - bg.getY())));
            }
            myTurtleManager.moveTurtle(String.valueOf(((TurtleLeaf) o).getId()), newPosition);
        }
    }

    /** Helper method for processing and handling settings retrieved from a {@code TurtleLeaf}*/
    private void getSettings(TurtleLeaf o) {
        myView.changePenDown(o.getIsPenDown());
        if (o.getIsShowing()) {
            showTurtle(String.valueOf(o.getId()));
        } else {
            hideTurtle(String.valueOf(o.getId()));
        }
        myView.changeBgColor(o.getBgColor());
        myView.getMyGUIWrapper().changePenColor(o.getPenColor());
        myView.changePenSize(o.getPenSize());
    }

}
