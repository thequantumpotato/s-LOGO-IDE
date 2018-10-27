package frontend.GUI.Display;

import frontend.Util.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import java.util.ArrayList;

import static frontend.GUI.Display.TurtleManager.ORIGIN_X;
import static frontend.GUI.Display.TurtleManager.ORIGIN_Y;


/** Class for the management of line drawing on the display
 *  @author bpx */
public class PathManager extends Manager{

    private boolean penDown;
    private Pen myPen;
    private ArrayList<Path> myPaths;
    private AnimationContainer myAnimationContainer;

    /** Default constructor */
    public PathManager(){
        super();
        penDown = true;
        myPen = new Pen(new Coordinate(ORIGIN_X,ORIGIN_Y,0));
        myPaths =  new ArrayList<>();
        myAnimationContainer = new AnimationContainer();
    }

    /** Adds any number of {@code Line} objects to the {@code Path} and generates the appropriate animations
     *  @param id The identifier for the turtle for which this path applies to
     *  @param lines The {@code Line} objects that will be added to the {@code Path}*/
    public void addPath(String id, Line...lines){
        if(penDown){
            LinePath newpath = new LinePath();
            for(Line l : lines){
                newpath.addLine(l);
            }
            if(penDown){
                myAnimationContainer.addAnimation(id,myPen.drawLinePath(newpath));
            }
            else{
                myPen.movePen(new Coordinate(lines[lines.length-1].getEndX(),lines[lines.length-1].getEndY(),0));
            }
        }
    }

    /** Plays the {@code PathTracer} animation for the specified turtle
     *  @param id The turtle to play the animation for */
    public void playPathDrawAnimation(String id){
        myAnimationContainer.play(id);
    }

    public void penUp(){
        penDown = false;
    }

    public void penDown(){
        penDown = true;
    }

    /** Resets {@code PathManager} fields to default state */
    @Override
    public void reset(){
        myPaths.clear();
        myAnimationContainer.killAllAnimations();
    }
}
