package frontend.GUI.Display;


import frontend.GUI.View;
import frontend.Util.AnimationContainer;
import frontend.Util.Coordinate;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

/** Manages turtles on the display
 *  @author bpx */
public class TurtleManager extends Manager {

    public static final int DEFAULT_DURATION = 2;
    public static final double ORIGIN_X = 275;
    public static final double ORIGIN_Y = 250;

    private Group myRenderTarget;
    private View myView;
    private ArrayList<String> myTurtles;
    private SpriteContainer mySpriteContainer;
    private AnimationContainer myAnimationContainer;
    private MovementManager myMovementManager;
    private PathManager myPathManager;

    /** Initialize a new {@code TurtleManager} with the render target
     *  @param renderTarget The {@code Group} to render everything to */
    public TurtleManager(Group renderTarget, View myView_){
        super();
        myView = myView_;
        myRenderTarget = renderTarget;
        myTurtles = new ArrayList<>();
        mySpriteContainer = new SpriteContainer(renderTarget);
        myAnimationContainer = new AnimationContainer();
        myMovementManager = new MovementManager();
        myPathManager =  new PathManager(myRenderTarget);
    }

    public boolean contains(String id){
        return(myTurtles.contains(id));
    }

    /** Create a new turtle with the current settings and place it at the origin
     *  @param id The identifier for the turtle being created */
    public void createTurtle(String id){
        myTurtles.add(id);
        mySpriteContainer.addSprite(id);
        mySpriteContainer.getSprite(id).setOnMouseEntered(
                e -> myView.showState(id, mySpriteContainer.getSprite(id), myPathManager)
        );
        mySpriteContainer.getSprite(id).setOnMouseExited(
                e -> myView.noShow()
        );
        mySpriteContainer.getSprite(id).setPosition(new Coordinate(ORIGIN_X,ORIGIN_Y,0));
        mySpriteContainer.getSprite(id).setOnMousePressed(e -> {
            if(mySpriteContainer.isActive(id)){
                myView.passCommand(String.format("untell [ %s ]",id));
                mySpriteContainer.setActive(id,false);
            }else{
                myView.passCommand(String.format("tell [ %s ]",id));
                mySpriteContainer.setActive(id,true);
            }

        });
    }

    /** Move the specified turtle to a new position through animation
     *  @param id The identifier for the turtle to move
     *  @param newPosition The {@code Coordinate} to move the turtle to */
    public void moveTurtle(String id, Coordinate newPosition){
        myPathManager.addPath(id, new Line(mySpriteContainer.getSprite(id).getPosition().getX()+ mySpriteContainer.getSpriteSize()/2, mySpriteContainer.getSprite(id).getPosition().getY()+ mySpriteContainer.getSpriteSize()/2,
                newPosition.getX()+ mySpriteContainer.getSpriteSize()/2,newPosition.getY()+ mySpriteContainer.getSpriteSize()/2));
        myMovementManager.addTurtleAnimation(id, mySpriteContainer.getSprite(id), mySpriteContainer.getSprite(id).getPosition(),newPosition);
        mySpriteContainer.getSprite(id).setPosition(newPosition);
    }

    /** Update the visual display of the turtle with animations after {@code moveTurtle()} has been run */
    public void updateTurtles(){
        for(String id: myTurtles){
            mySpriteContainer.getSprite(id).toFront();
            myMovementManager.playTurtleAnimation(id);
            myPathManager.playPathDrawAnimation(id);
        }
    }

    /** Render the specified turtle to the root
     *  @param id The identifier for the turtle to render */
    public void show(String id){
        mySpriteContainer.getSprite(id).show();
    }

    /** Hide the specified turtle on the display
     *  @param id The identifier for the turtle to hide */
    public void hide(String id){
        mySpriteContainer.getSprite(id).hide();
    }

    /** Sets the active or inactive state of a sprite
     *  @param id The identifier for the turtle to change state
     *  @param state False means inactive, True means active */
    public void setActive(String id, boolean state){
        mySpriteContainer.setActive(id,state);
    }

    public void setTurtleSize(double size){
        mySpriteContainer.setSpriteSize(size);
    }

    public void setTurtleSize(String id, double size){
        mySpriteContainer.setSpriteSize(id,size);
    }

    public void setTurtleImage(Image image){
        mySpriteContainer.setSpriteImage(image);
    }

    public void penDown(){
        myPathManager.penDown();
    }

    public void penUp(){
        myPathManager.penUp();
    }

    public void setPenSize(double size){
        myPathManager.setPenSize(size);
    }

    public void setPenColor(Color color){
        myPathManager.setPenColor(color);
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
        mySpriteContainer.killAllSprites();
        myAnimationContainer.killAllAnimations();
        myMovementManager.reset();
        myPathManager.reset();
    }
}
