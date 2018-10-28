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
    private SpriteManager mySpriteManager;
    private AnimationContainer myAnimationContainer;
    private MovementManager myMovementManager;
    private PathManager myPathManager;

    /** Initialize a new {@code TurtleManager} with the render target
     *  @param renderTarget The {@code Group} to render everything to */
    public TurtleManager(Group renderTarget){
        super();
        myRenderTarget = renderTarget;
        myTurtles = new ArrayList<>();
        mySpriteManager = new SpriteManager(renderTarget);
        myAnimationContainer = new AnimationContainer();
        myMovementManager = new MovementManager();
        myPathManager =  new PathManager(myRenderTarget);
    }

    /** Create a new turtle with the current settings and place it at the origin
     *  @param id The identifier for the turtle being created */
    public void createTurtle(String id){
        myTurtles.add(id);
        mySpriteManager.addTurtle(id);
        mySpriteManager.getTurtle(id).setPosition(new Coordinate(ORIGIN_X,ORIGIN_Y,0));
    }

    /** Move the specified turtle to a new position through animation
     *  @param id The identifier for the turtle to move
     *  @param newPosition The {@code Coordinate} to move the turtle to */
    public void moveTurtle(String id, Coordinate newPosition){
        myPathManager.addPath(id, new Line(mySpriteManager.getTurtle(id).getPosition().getX()+mySpriteManager.getSpriteSize()/2,mySpriteManager.getTurtle(id).getPosition().getY()+mySpriteManager.getSpriteSize()/2,
                newPosition.getX()+mySpriteManager.getSpriteSize()/2,newPosition.getY()+mySpriteManager.getSpriteSize()/2));
        myMovementManager.addTurtleAnimation(id,mySpriteManager.getTurtle(id),mySpriteManager.getTurtle(id).getPosition(),newPosition);
    }

    /** Update the visual display of the turtle with animations after {@code moveTurtle()} has been run */
    public void updateTurtles(){
        for(String id: myTurtles){
            mySpriteManager.getTurtle(id).toFront();
            myMovementManager.playTurtleAnimation(id);
            myPathManager.playPathDrawAnimation(id);
        }
    }

    /** Render the turtle to the root, if it is not already */
    public void show(String id){
        mySpriteManager.getTurtle(id).show();
    }

    public void hide(String id){
        mySpriteManager.getTurtle(id).hide();
    }

    private Coordinate adjustPosition(double x, double y, double theta) {
        double newtheta = Math.toDegrees((2 * Math.PI) - theta);
        return new Coordinate(x + ORIGIN_X, ORIGIN_Y - y, newtheta);
    }

    @Override
    public void setDuration(double duration){
        super.setDuration(duration);
        myMovementManager.setDuration(duration);
        myPathManager.setDuration(duration);
    }

    @Override
    public void reset() {
        mySpriteManager.killAllTurtles();
        myAnimationContainer.killAllAnimations();
        myMovementManager.reset();
        myPathManager.reset();
    }
}
