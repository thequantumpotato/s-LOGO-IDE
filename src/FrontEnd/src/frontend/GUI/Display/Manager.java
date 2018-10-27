package frontend.GUI.Display;

import javafx.scene.Group;
import javafx.util.Duration;

/** Super class for manager type objects that will handle time-based events */
public abstract class Manager {

    public static final int DEFAULT_DURATION = 2;

    private Duration myDuration;

    public Manager(){
        myDuration = Duration.seconds(DEFAULT_DURATION);
    }

    /** Sets the duration in seconds for future animations
     *  @param duration The new duration in seconds for future animations*/
    public void setDuration(double duration){
        if(duration>0){
            myDuration = Duration.seconds(duration);
        }
    }

    public Duration getDuration(){
        return Duration.valueOf(myDuration.toString());
    }

    public abstract void reset();

}
