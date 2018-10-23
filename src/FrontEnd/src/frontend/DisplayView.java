package frontend;

import backend.Turtle;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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

    public static final int TURTLE_SIZE = 25;
    public static final int LINE_ANIMATION_FPS = 15;
    public static final float LINE_ANIMATION_DELAY = 1f / LINE_ANIMATION_FPS;
    public static final int TURTLE_DEFAULT_X = 275;
    public static final int TURTLE_DEFAULT_Y = 250;
    public static final int DEFAULT_BG_WIDTH = 800;
    public static final int DEFAULT_BG_HEIGHT = 800;
    private Turtle myTurtle;
    private View myView;
    private ScrollPane scrollPane;
    private Group root;
    private Rectangle bg;
    private ImageView turtleView;
    private Pen myPen;
    private SequentialTransition myAnimQ;
    private boolean penDown;
    private double turtleX;
    private double turtleY;
    private double turtleW;
    private double turtleH;
    private double turtleAngle;

    public DisplayView(View myView_, Image image, Turtle turtle) {
        myView = myView_;
        scrollPane = new ScrollPane();
        root = new Group();
        myAnimQ = new SequentialTransition();
        turtleX = 0;
        turtleY = 0;
        turtleW = TURTLE_SIZE;
        turtleH = TURTLE_SIZE;
        //create bg
        bg = new Rectangle(DEFAULT_BG_WIDTH, DEFAULT_BG_HEIGHT, myView.DEFAULT_BG_COLOR);

        //create turtle
        /** TO DO: Set the default turtle location to the center of the displayView */
        turtleView = new ImageView(image);
        turtleView.setFitWidth(TURTLE_SIZE);
        turtleView.setFitHeight(TURTLE_SIZE);
        setTurtlePos(new Coordinate(TURTLE_DEFAULT_X, TURTLE_DEFAULT_Y, 0));
        //create pen
        myPen = new Pen(new Coordinate(turtleX + turtleW / 2, turtleY + turtleH / 2, 0));
        myPen.setRoot(root);
        //add turtle to scroll pane
        root.getChildren().add(bg);
        root.getChildren().add(turtleView);
        scrollPane.setContent(root);
        penDown = true;
        System.out.println(turtleView.getBoundsInLocal());
    }

    public void setTurtlePos(Coordinate turtleCoordinate) {
        turtleX = turtleCoordinate.getX();
        turtleY = turtleCoordinate.getY();
        turtleAngle = turtleCoordinate.getAngle();
        turtleView.setTranslateX(turtleX);
        turtleView.setTranslateY(turtleY);
        turtleView.setRotate(turtleAngle);
    }

    public void clearBg() {
        myPen.eraseAll();
    }

    public void changeBgColor(Color bgColor) {
        bg.setFill(bgColor);
    }

    public void setPenDown(boolean state) {
        penDown = state;
    }

    public void changePenColor(Color penColor) {
        myPen.setPenColor(penColor);
    }

    public void changePenSize(double size) {
        myPen.setPenSize(size);
    }

    public void hideTurtle() {
        root.getChildren().remove(turtleView);
    }

    public void showTurtle() {
        root.getChildren().add(turtleView);
    }

    public void changeTurtleSize(double size) {
        if (size > 0) {
            turtleView.setFitHeight(size);
            turtleView.setFitWidth(size);
            turtleW = size;
            turtleH = size;
        }
    }

    public void changeTurtleImg(Image newTurtleImg) {
        turtleView.setImage(newTurtleImg);
    }

    public void changeAnimationSpeed(Double time) {
        myPen.setDrawSpeed(Duration.seconds(time));
    }

    public void updateTurtle(Coordinate newpos, Duration duration) {
        if (newpos.getX() > bg.getX() + bg.getWidth() || newpos.getX() < bg.getX() ||
                newpos.getY() > bg.getY() + bg.getHeight() || newpos.getY() < bg.getY()) {
            expandBackground(Math.max(Math.abs(newpos.getX() - bg.getX()), Math.abs(newpos.getY() - bg.getY())));
        }
        TranslateTransition xt = new TranslateTransition(duration, turtleView);
        /*System.out.println("From "+turtleX+" "+turtleY);
        System.out.println("To "+newpos.getX()+" "+newpos.getY());*/
        xt.setFromX(turtleX);
        xt.setFromY(turtleY);
        xt.setToX(newpos.getX());
        xt.setToY(newpos.getY());
        RotateTransition rt = new RotateTransition(Duration.millis(0.1), turtleView);
        rt.setFromAngle(turtleAngle);
        rt.setToAngle(newpos.getAngle());
        myPen.setDrawSpeed(duration);
        ParallelTransition pl;
        if (penDown) {
            pl = myPen.drawPath(new Coordinate(newpos.getX() + turtleW / 2, newpos.getY() + turtleH / 2, 0));
        } else {
            myPen.movePen(new Coordinate(newpos.getX() + turtleW / 2, newpos.getY() + turtleH / 2, 0));
            pl = new ParallelTransition();
        }
        pl.getChildren().add(xt);
        pl.getChildren().add(rt);
        myAnimQ.getChildren().add(pl);
        turtleX = newpos.getX();
        turtleY = newpos.getY();
    }

    public void playAnims() {
        System.out.println("Playing anims");
        turtleView.toFront();
        myAnimQ.setOnFinished(event -> myAnimQ.getChildren().clear());
        myAnimQ.play();
    }

    private void expandBackground(double amount) {
        System.out.println("Expanding bg by " + amount);
        bg.setX(bg.getX() - amount);
        bg.setY(bg.getY() - amount);
        bg.setWidth(bg.getWidth() + 2 * amount);
        bg.setHeight(bg.getHeight() + 2 * amount);

    }

    public void clearScreen() {
    }


    private double[] getTurtleCenter() {
        double x = turtleView.getBoundsInParent().getMaxX() - (turtleView.getBoundsInParent().getWidth()) / 2;
        double y = turtleView.getBoundsInParent().getMaxY() - (turtleView.getBoundsInParent().getHeight()) / 2;
        return new double[]{x, y};
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
        if (arg != null) {
            playAnims();
        } else {
            System.out.println("null");
            myTurtle = (Turtle) o;
            System.out.println(myTurtle.getX() + " " + myTurtle.getY() + " " + myTurtle.getDirection());
            updateTurtle(adjustPosition(myTurtle.getX(), myTurtle.getY(), myTurtle.getDirection()), myPen.getMyDrawSpeed());
        }
    }
}
