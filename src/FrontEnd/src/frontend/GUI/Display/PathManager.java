package frontend.GUI.Display;

import frontend.Util.AnimationContainer;
import frontend.Util.Coordinate;
import frontend.Util.LinePath;
import frontend.Util.Pen;
import javafx.animation.SequentialTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;

import static frontend.GUI.Display.TurtleManager.ORIGIN_X;
import static frontend.GUI.Display.TurtleManager.ORIGIN_Y;


/**
 * Class for the management of line drawing on the display
 *
 * @author bpx
 */
public class PathManager extends Manager {

    private boolean penDown;
    private Group myRenderTarget;

    private Pen myPen;

    private ArrayList<Path> myPaths;
    private AnimationContainer myAnimationContainer;

    /**
     * Default constructor
     */
    public PathManager(Group root) {
        super();
        penDown = true;
        myRenderTarget = root;
        myPen = new Pen(new Coordinate(ORIGIN_X, ORIGIN_Y, 0));
        myPen.setRoot(myRenderTarget);
        myPaths = new ArrayList<>();
        myAnimationContainer = new AnimationContainer();
    }

    /**
     * Adds any number of {@code Line} objects to the {@code Path} and generates the appropriate animations
     *
     * @param id    The identifier for the turtle for which this path applies to
     * @param lines The {@code Line} objects that will be added to the {@code Path}
     */
    public void addPath(String id, Line... lines) {
        if (!myAnimationContainer.contains(id)) {
            myAnimationContainer.addAnimation(id, new SequentialTransition());
        }
        if (penDown) {
            System.out.println("Creating path for " + id);
            LinePath newpath = new LinePath();
            for (Line l : lines) {
                newpath.addLine(l);
            }
            if (penDown) {
                ((SequentialTransition) myAnimationContainer.get(id)).getChildren().add(myPen.drawLinePath(newpath));
                //myAnimationContainer.addAnimation(id,myPen.drawLinePath(newpath));
            } else {
                myPen.movePen(new Coordinate(lines[lines.length - 1].getEndX(), lines[lines.length - 1].getEndY(), 0));
            }
        }

    }

    /**
     * Plays the {@code PathTracer} animation for the specified turtle
     *
     * @param id The turtle to play the animation for
     */
    public void playPathDrawAnimation(String id) {
        myAnimationContainer.play(id);
    }


    /**
     * Sets the duration of the pen drawing animation
     *
     * @param duration The duration in seconds for the animation to take
     */
    public void setDuration(double duration) {
        myPen.setDrawSpeed(Duration.seconds(duration));
    }

    /**
     * Stop drawing lines
     */
    public void penUp() {
        penDown = false;
    }

    /**
     * Start drawing lines
     */
    public void penDown() {
        penDown = true;
    }

    /** Sets the pen drawing stroke width
     *  @param size The new width in pixels for the pen's stroke*/
    public void setPenSize(double size) {
        myPen.setPenSize(size);
    }

    /** Sets the pen's line color
     *  @param color The new {@code Color} for the pen to use*/
    public void setPenColor(Color color) {
        myPen.setPenColor(color);
    }

    /**
     * Resets {@code PathManager} fields to default state
     */
    @Override
    public void reset() {
        myPaths.clear();
        myPen.eraseAll();
        myAnimationContainer.killAllAnimations();
    }

    /** Returns the {@code Pen} object stored in the {@code PathManager}*/
    public Pen getMyPen() {
        return myPen;
    }

    /** Returns whether the pen is currently down */
    public boolean isPenDown() {
        return penDown;
    }
}
