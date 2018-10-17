package frontend;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * DisplayView contains the display of the turtle as well as the panel for the user to change the
 * settings (eg. Pen color, background color, and turtle image). It is the playground for the turtle
 * to move around and draw different shapes.
 *
 * @author Vincent Liu
 */

public class DisplayView implements SubView {

    private View myView;
    private ScrollPane scrollPane;
    private Rectangle bg;
    private ImageView turtleView;
    private int turtleX;
    private int turtleY;
    private int turtleAngle;

    public DisplayView(View myView_, Image image) {
        myView = myView_;
        scrollPane  = new ScrollPane();
        Group root = new Group();
        //create bg
        bg = new Rectangle(800,800,Color.BLACK);

        //create turtle
        /** TO DO: Set the default turtle location to the center of the displayView */
        turtleView = new ImageView(image);
        turtleView.setFitWidth(100);
        turtleView.setFitHeight(100);

        //add turtle to scroll pane
        root.getChildren().add(bg);
        root.getChildren().add(turtleView);
        scrollPane.setContent(root);
    }

    public void setTurtlePos(Coordinate turtleCoordinate) {
        turtleX = turtleCoordinate.getX();
        turtleY = turtleCoordinate.getY();
        turtleAngle = turtleCoordinate.getAngle();
        turtleView.setX(turtleX);
        turtleView.setY(turtleY);
        turtleView.setRotate(turtleAngle);
    }

    public void changeBgColor(Color bgColor) {

    }

    public void changePenColor(Color penColor) {

    }

    public void changeTurtleImg(Image newTurtleImg) {

    }

    public void updateTurtle(Coordinate newpos){
        if(newpos.getX()>bg.getWidth()||newpos.getY()>bg.getHeight()){
            bg.setHeight(Math.max(newpos.getX(),newpos.getY()));
            bg.setWidth(Math.max(newpos.getX(),newpos.getY()));
        }
        TranslateTransition xt = new TranslateTransition(Duration.seconds(5),turtleView);
        xt.setByX(newpos.getX()-turtleView.getX());
        xt.setByY(newpos.getY()-turtleView.getY());
        turtleView.setRotate(newpos.getAngle());
        xt.play();

    }

    @Override
    public Node getView() {
        return scrollPane;
    }
}
