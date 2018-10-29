package frontend.Util;

import javafx.animation.*;
import java.util.HashMap;

/** Class for the management of animations by ID
 *  @author bpx */
public class AnimationContainer {
    private HashMap<String, Animation> myAnimations;

    /** Constructor initializes object with default fields */
    public AnimationContainer(){
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
            myAnimations.remove(id);
        }
    }

    /** Returns whether the {@code AnimationContainer} contains the specified identifier
     *  @param id The key to check */
    public boolean contains(String id){
        return(myAnimations.containsKey(id));
    }

    public Animation get(String id){
        return myAnimations.get(id);
    }

    /** Remove key and value information for all animation queues*/
    public void killAllAnimations(){
        myAnimations.clear();
    }
}
