package frontend.Util;

import javafx.animation.*;
import java.util.HashMap;

/** Class for the management of animations by ID
 *  @author bpx */
public class AnimationManager {
    private HashMap<String, Animation> myAnimations;

    /** Constructor initializes object with default fields */
    public AnimationManager(){
        myAnimations = new HashMap<>();
    }

    /** Add a new key-value pair to the {@code Animation} map*/
    public void addAnimation(String id, Animation animation){
        myAnimations.put(id,animation);
    }

    /** Play the {@code Animation} specified if it exists and is not already playing
     *  @param id The key value of the {@code Animation} to be played*/
    public void play(String id){
        if(myAnimations.containsKey(id) && myAnimations.get(id).getStatus()!= Animation.Status.RUNNING){
            myAnimations.get(id).play();
        }
    }

    /** Remove key and value information for all animation queues*/
    public void killAllAnimations(){
        myAnimations.clear();
    }
}
