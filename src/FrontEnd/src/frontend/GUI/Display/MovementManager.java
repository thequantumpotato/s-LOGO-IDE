package frontend.GUI.Display;

import frontend.Util.AnimationManager;
import frontend.Util.Coordinate;
import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static frontend.GUI.Display.TurtleManager.DEFAULT_DURATION;

/** Class for the management of movement animations of the sprites
 *  @author bpx */
public class MovementManager {
    private AnimationManager myAnimationManager;
    private Duration myDuration;

    /** Constructor initializes object with default fields */
    public MovementManager(){
        myAnimationManager = new AnimationManager();
        myDuration = Duration.seconds(DEFAULT_DURATION);
    }

    /** Add a new turtle movement animation to the animation queue
     *  @param imageView The sprite to apply the animation to
     *  @param currentPosition The current position of the sprite in the coordinate system
     *  @param newPosition The target position of the sprite in the coordinate system */
    public void addTurtleAnimation(String id, ImageView imageView, Coordinate currentPosition, Coordinate newPosition){
        TranslateTransition xt = new TranslateTransition(myDuration,imageView);
        xt.setFromX(currentPosition.getX());
        xt.setFromY(currentPosition.getY());
        xt.setToX(newPosition.getX());
        xt.setToY(newPosition.getY());
        RotateTransition rt = new RotateTransition(Duration.millis(0.1), imageView);
        rt.setFromAngle(currentPosition.getAngle());
        rt.setToAngle(newPosition.getAngle());
        ParallelTransition combinedTransition = new ParallelTransition();
        combinedTransition.getChildren().addAll(xt,rt);
        myAnimationManager.addAnimation(id,combinedTransition);
    }

    /** Sets the duration in seconds for future animations
     *  @param duration The new duration in seconds for future animations*/
    public void setDuration(double duration){
        if(duration>0){
            myDuration = Duration.seconds(duration);
        }
    }


    /** Plays the queued animations for a specific sprite
     *  @param id The id of the turtle to play the animation*/
    public void playTurtleAnimation(String id){
        myAnimationManager.play(id);
    }

    /** Resets the fields of the {@code MovementManager} back to default values*/
    public void reset(){
        myDuration = Duration.seconds(DEFAULT_DURATION);
        myAnimationManager.killAllAnimations();
    }
}
