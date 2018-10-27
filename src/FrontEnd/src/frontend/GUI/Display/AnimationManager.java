package frontend.GUI.Display;

import frontend.Util.Coordinate;
import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.HashMap;

import static frontend.GUI.Display.TurtleManager.DEFAULT_DURATION;

/** Class for the management of sprite movement animations
 *  @author bpx */
public class AnimationManager {
    private HashMap<String, SequentialTransition> myAnimations;
    private Duration myDuration;

    /** Constructor initializes object with default fields */
    public AnimationManager(){
        myAnimations = new HashMap<>();
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
        if(!myAnimations.containsKey(id)){
            myAnimations.put(id,new SequentialTransition());
        }
        myAnimations.get(id).getChildren().add(combinedTransition);
    }

    /** Plays the queued animations for a specific sprite
     *  @param id The id of the turtle to play the animation*/
    public void playTurtleAnimation(String id){
        if(myAnimations.get(id).getStatus()!= Animation.Status.RUNNING){
            myAnimations.get(id).play();
            myAnimations.get(id).getChildren().clear();
        }
    }

    /** Sets the duration in seconds for future animations
     *  @param duration The new duration in seconds for future animations*/
    public void setDuration(double duration){
        if(duration>0){
            myDuration = Duration.seconds(duration);
        }
    }

    /** Remove key and value information for all animation queues*/
    public void killAllAnimations(){
        myAnimations.clear();
    }
}
