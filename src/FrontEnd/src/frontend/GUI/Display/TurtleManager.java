package frontend.GUI.Display;


import frontend.Util.AnimationContainer;
import frontend.Util.Coordinate;
import javafx.scene.Group;
import javafx.scene.shape.Line;

import java.util.ArrayList;

/** Manages turtles on the display
 *  @author bpx */
public class TurtleManager extends Manager {

    public static final int DEFAULT_DURATION = 2;
    public static final double ORIGIN_X = 275;
    public static final double ORIGIN_Y = 300;

    private Group myRenderTarget;
    private ArrayList<String> myTurtles;
    private SpriteContainer mySpriteContainer;
    private AnimationContainer myAnimationContainer;
    private MovementManager myMovementManager;
    private PathManager myPathManager;

    /** Initialize a new {@code TurtleManager} with the render target
     *  @param renderTarget The {@code Group} to render everything to */
    public TurtleManager(Group renderTarget){
        super();
        myRenderTarget = renderTarget;
        myTurtles = new ArrayList<>();
        mySpriteContainer = new SpriteContainer(renderTarget);
        myAnimationContainer = new AnimationContainer();
        myMovementManager = new MovementManager();
        myPathManager =  new PathManager(myRenderTarget);
    }

    /** Create a new turtle with the current settings and place it at the origin
     *  @param id The identifier for the turtle being created */
    public void createTurtle(String id){
        myTurtles.add(id);
        mySpriteContainer.addTurtle(id);
        mySpriteContainer.getTurtle(id).setPosition(new Coordinate(ORIGIN_X,ORIGIN_Y,0));
    }

    /** Move the specified turtle to a new position through animation
     *  @param id The identifier for the turtle to move
     *  @param newPosition The {@code Coordinate} to move the turtle to */
    public void moveTurtle(String id, Coordinate newPosition){
        myPathManager.addPath(id, new Line(mySpriteContainer.getTurtle(id).getPosition().getX()+ mySpriteContainer.getSpriteSize()/2, mySpriteContainer.getTurtle(id).getPosition().getY()+ mySpriteContainer.getSpriteSize()/2,
                newPosition.getX()+ mySpriteContainer.getSpriteSize()/2,newPosition.getY()+ mySpriteContainer.getSpriteSize()/2));
        myMovementManager.addTurtleAnimation(id, mySpriteContainer.getTurtle(id), mySpriteContainer.getTurtle(id).getPosition(),newPosition);
    }

    /** Update the visual display of the turtle with animations after {@code moveTurtle()} has been run */
    public void updateTurtles(){
        for(String id: myTurtles){
            mySpriteContainer.getTurtle(id).toFront();
            myMovementManager.playTurtleAnimation(id);
            myPathManager.playPathDrawAnimation(id);
        }
    }

    /** Render the specified turtle to the root
     *  @param id The identifier for the turtle to render */
    public void show(String id){
        mySpriteContainer.getTurtle(id).show();
    }

    /** Hide the specified turtle on the display
     *  @param id The identifier for the turtle to hide */
    public void hide(String id){
        mySpriteContainer.getTurtle(id).hide();
    }

    /** Sets the active or inactive state of a sprite
     *  @param id The identifier for the turtle to change state
     *  @param state False means inactive, True means active */
    public void setActive(String id, boolean state){
        mySpriteContainer.setActive(id,state);
    }

    /** Sets duration of all time-based turtle operations
     *  @param duration The duration in seconds for time-based operations to take*/
    @Override
    public void setDuration(double duration){
        super.setDuration(duration);
        myMovementManager.setDuration(duration);
        myPathManager.setDuration(duration);
    }

    /** Resets the display */
    @Override
    public void reset() {
        mySpriteContainer.killAllTurtles();
        myAnimationContainer.killAllAnimations();
        myMovementManager.reset();
        myPathManager.reset();
    }
}
