package frontend.GUI.Display;

import frontend.Util.AnimationContainer;
import frontend.Util.Coordinate;
import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


/** Class for the management of movement animations of the sprites. Subclass of {@code Manager}
 *  @author bpx */
public class MovementManager extends Manager{

    private AnimationContainer myAnimationContainer;

    /** Constructor initializes object with default fields */
    public MovementManager(){
        super();
        myAnimationContainer = new AnimationContainer();
    }

    /** Add a new turtle movement animation to the animation queue
     *  @param imageView The sprite to apply the animation to
     *  @param currentPosition The current position of the sprite in the coordinate system
     *  @param newPosition The target position of the sprite in the coordinate system */
    public void addTurtleAnimation(String id, ImageView imageView, Coordinate currentPosition, Coordinate newPosition){
        TranslateTransition xt = new TranslateTransition(super.getDuration(),imageView);
        xt.setFromX(currentPosition.getX());
        xt.setFromY(currentPosition.getY());
        xt.setToX(newPosition.getX());
        xt.setToY(newPosition.getY());
        RotateTransition rt = new RotateTransition(Duration.millis(0.1), imageView);
        rt.setFromAngle(currentPosition.getAngle());
        rt.setToAngle(newPosition.getAngle());
        ParallelTransition combinedTransition = new ParallelTransition();
        combinedTransition.getChildren().addAll(xt,rt);
        myAnimationContainer.addAnimation(id,combinedTransition);
    }

    /** Plays the queued animations for a specific sprite
     *  @param id The id of the turtle to play the animation*/
    public void playTurtleAnimation(String id){
        myAnimationContainer.play(id);
    }

    /** Resets the fields of the {@code MovementManager} back to default values*/
    @Override
    public void reset(){
        super.setDuration(TurtleManager.DEFAULT_DURATION);
        myAnimationContainer.killAllAnimations();
    }
}
