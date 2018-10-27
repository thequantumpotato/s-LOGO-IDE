package frontend.GUI.Display;

import frontend.Util.AnimationContainer;
import frontend.Util.LinePath;
import frontend.Util.PathTracer;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import java.util.ArrayList;


/** Class for the management of line drawing on the display
 *  @author bpx */
public class PathManager extends Manager{
    public static final String PATH_KEY = "path";
    private ArrayList<Path> myPaths;
    private AnimationContainer myAnimationContainer;



    public PathManager(){
        super();
        myPaths =  new ArrayList<>();
        myAnimationContainer = new AnimationContainer();

    }

    public void addPath(Line...lines){
        LinePath newpath = new LinePath();
        for(Line l : lines){
            newpath.addLine(l);
        }

        PathTracer pathTracer = new PathTracer(super.getDuration().toSeconds(),newpath);
        myAnimationContainer.addAnimation(PATH_KEY,pathTracer.getTracer());
    }

    @Override
    public void reset(){
        myPaths.clear();
        myAnimationContainer.killAllAnimations();
    }
}
