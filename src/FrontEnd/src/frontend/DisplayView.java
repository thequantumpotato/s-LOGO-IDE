package frontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

/**
 * DisplayView contains the display of the turtle as well as the panel for the user to change the
 * settings (eg. Pen color, background color, and turtle image). It is the playground for the turtle
 * to move around and draw different shapes.
 *
 * @author Vincent Liu
 * @author Benjamin Xu
 */

public class DisplayView implements SubView {

    public static final int TURTLE_SIZE = 25;
    public static final int LINE_ANIMATION_FPS = 15;
    public static final float LINE_ANIMATION_DELAY = 1f / LINE_ANIMATION_FPS;
    private View myView;
    private ScrollPane scrollPane;
    private Group root;
    private Rectangle bg;
    private ImageView turtleView;
    private Path path;

    private boolean penDown;
    private int turtleX;
    private int turtleY;
    private int turtleAngle;

    public DisplayView(View myView_, Image image) {
        myView = myView_;
        scrollPane = new ScrollPane();
        root = new Group();
        //create bg
        bg = new Rectangle(800, 800, Color.WHITE);
        path = new Path();

        //create turtle
        /** TO DO: Set the default turtle location to the center of the displayView */
        turtleView = new ImageView(image);
        turtleView.setFitWidth(TURTLE_SIZE);
        turtleView.setFitHeight(TURTLE_SIZE);
        turtleView.setX(100);
        turtleView.setY(100);
        //create path
        path.getElements().add(new MoveTo(getTurtleCenter()[0],getTurtleCenter()[1]));

        //add turtle to scroll pane
        root.getChildren().add(bg);
        root.getChildren().add(path);
        root.getChildren().add(turtleView);
        scrollPane.setContent(root);
        /*turtleView.setX(400);
        turtleView.setY(400);*/
        penDown = true;
//        System.out.println(turtleView.getBoundsInLocal());
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
        bg.setFill(bgColor);
    }

    public void changePenColor(Color penColor) {

    }

    public void changeTurtleImg(Image newTurtleImg) {
        turtleView.setImage(newTurtleImg);
    }

    public void updateTurtle(Coordinate newpos, int duration) {
        if (newpos.getX() > bg.getWidth() || newpos.getY() > bg.getHeight()) {
            bg.setHeight(Math.max(newpos.getX(), newpos.getY()));
            bg.setWidth(Math.max(newpos.getX(), newpos.getY()));
        }
        TranslateTransition xt = new TranslateTransition(Duration.seconds(duration), turtleView);
        xt.setByX(newpos.getX() - turtleView.getX());
        xt.setByY(newpos.getY() - turtleView.getY());
        turtleView.setRotate(newpos.getAngle());
        Timeline line = createDrawPath(newpos,duration);
        xt.play();
        line.play();

    }

    private double[] getTurtleCenter(){
        double x = turtleView.getBoundsInParent().getMaxX()-(turtleView.getBoundsInParent().getWidth())/2;
        double y = turtleView.getBoundsInParent().getMaxY()-(turtleView.getBoundsInParent().getHeight())/2;
        return new double[]{x,y};
    }

    private Timeline createDrawPath(Coordinate newpos,int duration) {
        // temporary method, fill in implementation later
        /*Line line = new Line();
        line.setStartX(turtleView.getX());
        line.setStartY(turtleView.getY());*/

        int totalframes = LINE_ANIMATION_FPS*duration;
        double xnudge = (newpos.getX()-turtleView.getX())/totalframes;
        double ynudge = (newpos.getY()-turtleView.getY())/totalframes;
        Timeline timeline = new Timeline();
        timeline.setCycleCount(LINE_ANIMATION_FPS *duration);
        KeyFrame frame = new KeyFrame(Duration.seconds(LINE_ANIMATION_DELAY), event -> {
//            System.out.println("nudge to "+turtleView.getBoundsInParent());
            path.getElements().add(new LineTo(getTurtleCenter()[0],getTurtleCenter()[1]));
        });
        timeline.getKeyFrames().add(frame);
        timeline.play();
        return timeline;
    }

    @Override
    public Node getView() {
        return scrollPane;
    }
}
