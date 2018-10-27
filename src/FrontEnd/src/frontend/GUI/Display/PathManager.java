package frontend.GUI.Display;

import frontend.Util.LinePath;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;

import static frontend.GUI.Display.TurtleManager.DEFAULT_DURATION;

public class PathManager {
    private Group myRenderTarget;
    private ArrayList<Path> myPaths;
    private Duration myDuration;

    public PathManager(Group renderTarget){
        myRenderTarget = renderTarget;
        myPaths =  new ArrayList<>();
        myDuration = Duration.seconds(DEFAULT_DURATION);
    }

    public void addPath(Line...lines){
        LinePath newpath = new LinePath();
        for(Line l : lines){
            newpath.addLine(l);
        }
    }
}
