package frontend.GUI.Display;

import javafx.util.Duration;

/** Super class for manager type objects that will handle time-based events
 *  @author bpx*/
public abstract class Manager {

    private Duration myDuration;

    /** Default constructor*/
    public Manager(){
        myDuration = Duration.seconds(TurtleManager.DEFAULT_DURATION);
    }

    /** Sets the duration in seconds for future animations
     *  @param duration The new duration in seconds for future animations*/
    public void setDuration(double duration){
        if(duration>0){
            myDuration = Duration.seconds(duration);
        }
    }

    /** Returns the {@code Duration} of the {@code Manager} without providing access to the field*/
    public Duration getDuration(){
        return Duration.seconds(myDuration.toSeconds());
    }

    /** Abstract method, reset the state of classes extending {@code Manager} to default*/
    public abstract void reset();

}
