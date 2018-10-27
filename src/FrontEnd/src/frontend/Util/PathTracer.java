package frontend.Util;

import javafx.animation.SequentialTransition;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/** Custom transition class to trace {@code LinePath} objects
 *  @author bpx */
public class PathTracer{

    private double myDuration;
    private SequentialTransition myAnimation;

    public PathTracer(double duration, LinePath linePath){
        myAnimation = new SequentialTransition();
        myDuration = duration/linePath.getLines().size();
        for(Line l : linePath.getLines()){
            myAnimation.getChildren().add(new LineDrawTransition(Duration.seconds(myDuration),l) );
        }
    }

    public SequentialTransition getTracer() {
        return myAnimation;
    }
}
