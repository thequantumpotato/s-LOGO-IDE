package frontend.GUI.Display;


import frontend.Util.AnimationContainer;
import javafx.scene.Group;

/** Manages turtles on the display
 *  @author bpx */
public class TurtleManager extends Manager {

    public static final int DEFAULT_DURATION = 2;

    private SpriteManager mySpriteManager;
    private AnimationContainer myAnimationContainer;
    private MovementManager myMovementManager;
    private PathManager myPathManager;

    public TurtleManager(Group renderTarget){
        super();
        mySpriteManager = new SpriteManager(renderTarget);
        myAnimationContainer = new AnimationContainer();
        myMovementManager = new MovementManager();
        myPathManager =  new PathManager();
    }

    public void createTurtle(){

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
