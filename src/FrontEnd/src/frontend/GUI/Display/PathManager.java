package frontend.GUI.Display;

import frontend.Util.AnimationManager;
import frontend.Util.LinePath;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import java.util.ArrayList;

/** Class for the management of line drawing on the display
 *  @author bpx */
public class PathManager {
    private Group myRenderTarget;
    private ArrayList<Path> myPaths;
    private AnimationManager myAnimationManager;


    public PathManager(Group renderTarget){
        myRenderTarget = renderTarget;
        myPaths =  new ArrayList<>();
        myAnimationManager = new AnimationManager();
    }

    public void addPath(Line...lines){
        LinePath newpath = new LinePath();
        for(Line l : lines){
            newpath.addLine(l);
        }
    }
}
