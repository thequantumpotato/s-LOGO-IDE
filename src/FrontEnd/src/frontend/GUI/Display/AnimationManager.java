package frontend.GUI.Display;

import javafx.animation.SequentialTransition;

import java.util.HashMap;

public class AnimationManager {
    private HashMap<String, SequentialTransition> myAnimations;

    public AnimationManager(){
        myAnimations = new HashMap<>();
    }

}
