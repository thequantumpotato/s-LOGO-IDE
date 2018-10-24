package frontend.Display;

import frontend.Coordinate;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static frontend.Display.DisplayView.TURTLE_DEFAULT_X;
import static frontend.Display.DisplayView.TURTLE_DEFAULT_Y;
import static frontend.Display.DisplayView.TURTLE_IMAGE;

/** Class that encapsulates all the behavior needed to manipulate the turtle sprite on the GUI
 * @author bpx
 */
public class TurtleView {
    public static final int TURTLE_SIZE = 25;
    public static final int TURTLE_SPEED = 2;

    private double myX;
    private double myY;
    private double myAngle;
    private double myWidth;
    private double myHeight;
    private double myDuration;
    private boolean penDown;

    private Pen myPen;
    private ImageView myView;
    private Group myRoot;
    private SequentialTransition myAnimQ;

    /** Default constructor
     *  xpos = TURTLE_DEFAULT_X
     *  ypos = TURTLE_DEFAULT_Y
     *  image = TURTLE_IMAGE
     */
    public TurtleView(Pen pen, Group root){
        this(TURTLE_DEFAULT_X,TURTLE_DEFAULT_Y,TURTLE_IMAGE, pen, root);
    }

    /** Full constructor
     * @param xpos The initial X position of the {@code TurtleView}
     * @param ypos The initial Y position of the {@code TurtleView}
     * @param image The path to image file representing the turtle sprite
     * @param pen The reference to the Pen object that will be used to trace the turtle's path
     * @param root The reference to the Group to which {@code TurtleView} will render its turtle sprite to
     */
    public TurtleView(double xpos, double ypos, String image, Pen pen, Group root){
        myX = xpos;
        myY = ypos;
        myAngle = 0;
        myWidth = TURTLE_SIZE;
        myHeight = TURTLE_SIZE;
        myDuration = TURTLE_SPEED;
        penDown = true;
        myPen = pen;
        myView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(image)));
        myView.setFitWidth(myWidth);
        myView.setFitHeight(myHeight);
        myRoot = root;
        myRoot.getChildren().add(myView);
        myAnimQ = new SequentialTransition();
    }

    /** Stop tracing turtle path with {@code myPen} */
    public void penUp(){
        penDown = false;
    }

    /** Start tracing turtle path with {@code myPen} */
    public void penDown(){
        penDown = true;
    }

    /** Render the turtle to the root, if it is not already */
    public void show(){
        if(!myRoot.getChildren().contains(myView)){
            myRoot.getChildren().add(myView);
        }
    }

    /** Remove the turtle from the root */
    public void hide(){
        myRoot.getChildren().remove(myView);
    }

    /** Forcibly set the turtle's position without any animation
     * @param coordinate The coordinate to which the turtle sprite will translate to
     */
    public void setPosition(Coordinate coordinate){
        myX = coordinate.getX();
        myY = coordinate.getY();
        myAngle = coordinate.getAngle();
        myView.setTranslateX(myX);
        myView.setTranslateY(myY);
        myView.setRotate(myAngle);
    }

    /** Change the size of the turtle sprite, locked at 1:1 aspect ratio
     * @param size must be greater than 0, represents pixel width and height to set the sprite to
     */
    public void setSize(double size){
        if(size>0){
            myWidth = size;
            myHeight = size;
            myView.setFitHeight(myHeight);
            myView.setFitWidth(myWidth);
        }
    }

    /** Change the speed at which the turtle animation and line drawing animation runs
     * @param speed must be greater than 0, represents duration in seconds that animation per movement will take
     */
    public void setSpeed(double speed){
        if(speed>0){
            myDuration = speed;
        }
    }

    /** Change the sprite representation of the turtle if the image created is valid (not null)
     * @param path the string representing the path to the image file to be used as the new turtle sprite
     */
    public void setSprite(String path){
        Image newsprite = new Image(this.getClass().getClassLoader().getResourceAsStream(path));
        if(newsprite!=null){
            myView.setImage(newsprite);
        }
    }

    /** Add an animation for the movement to the specified coordinate to the animation queue to be played later
     *  See {@code playAnimation()} for more information
     *  @param coordinate the coordinate serving as the target destination of the transition animations
     */
    public void moveTo(Coordinate coordinate){
        Duration duration = Duration.seconds(myDuration);
        TranslateTransition xt = new TranslateTransition(duration, myView);
        xt.setFromX(myX);
        xt.setFromY(myY);
        xt.setToX(coordinate.getX());
        xt.setToY(coordinate.getY());
        RotateTransition rt = new RotateTransition(Duration.millis(0.1), myView);
        rt.setFromAngle(myAngle);
        rt.setToAngle(coordinate.getAngle());
        myPen.setDrawSpeed(duration);
        ParallelTransition pl;
        if (penDown) {
            pl = myPen.drawPath(new Coordinate(coordinate.getX() + myWidth / 2, coordinate.getY() + myHeight / 2, 0));
        } else {
            myPen.movePen(new Coordinate(coordinate.getX() + myWidth / 2, coordinate.getY() + myHeight / 2, 0));
            pl = new ParallelTransition();
        }
        pl.getChildren().add(xt);
        pl.getChildren().add(rt);
        myAnimQ.getChildren().add(pl);
        myX = coordinate.getX();
        myY = coordinate.getY();
    }

    /** Plays the animations in {@code myAnimQ} sequentially.
     *  <p>
     *  Animations added to {@code myAnimQ} while it is currently running will not be shown.
     *  {@code playAnimation()} must be called again after new animations are added.
     * */
    public void playAnimation(){
        myView.toFront();
        myAnimQ.play();
        myAnimQ.getChildren().clear();
    }
}
