package frontend.GUI.Display;


import frontend.GUI.View;
import frontend.Util.AnimationContainer;
import frontend.Util.Coordinate;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

import static frontend.GUI.Display.DisplayView.TURTLE_IMAGE;
import static frontend.GUI.Display.DisplayView.TURTLE_SIZE;

/**
 * Manages turtles on the display
 *
 * @author bpx
 */
public class TurtleManager extends Manager {

    public static final double DEFAULT_DURATION = 0.2;
    public static final double ORIGIN_X = 275;
    public static final double ORIGIN_Y = 250;

    private Group myRenderTarget;
    private View myView;
    private ImageView myFake;
    private ArrayList<String> myTurtles;
    private SpriteContainer mySpriteContainer;
    private AnimationContainer myAnimationContainer;
    private MovementManager myMovementManager;
    private PathManager myPathManager;

    /**
     * Initialize a new {@code TurtleManager} with the render target
     *
     * @param renderTarget The {@code Group} to render everything to
     */
    public TurtleManager(Group renderTarget, View myView_) {
        super();
        myView = myView_;
        myFake = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));
        myFake.setFitWidth(TURTLE_SIZE);
        myFake.setFitHeight(TURTLE_SIZE);
        myFake.setTranslateX(ORIGIN_X);
        myFake.setTranslateY(ORIGIN_Y);
        myRenderTarget = renderTarget;
        myTurtles = new ArrayList<>();
        mySpriteContainer = new SpriteContainer(renderTarget);
        myAnimationContainer = new AnimationContainer();
        myMovementManager = new MovementManager();
        myPathManager = new PathManager(myRenderTarget);
    }

    /** Returns whether the {@code TurtleManager} currently contains a turtle with the specified ID
     *  @param id The identifier for the turtle */
    public boolean contains(String id) {
        return (myTurtles.contains(id));
    }

    /**
     * Create a new turtle with the current settings and place it at the origin
     *
     * @param id The identifier for the turtle being created
     */
    public void createTurtle(String id) {
        myTurtles.add(id);
        mySpriteContainer.addSprite(id);
        mySpriteContainer.getSprite(id).setOnMouseEntered(
                e -> myView.showState(id, mySpriteContainer.getSprite(id), myPathManager)
        );
        mySpriteContainer.getSprite(id).setOnMouseExited(
                e -> myView.noShow()
        );
        mySpriteContainer.getSprite(id).setPosition(new Coordinate(ORIGIN_X, ORIGIN_Y, 0));
        mySpriteContainer.getSprite(id).setOnMousePressed(e -> {
            if (mySpriteContainer.isActive(id)) {
                myView.passCommand(String.format("untell [ %s ]", id));
                mySpriteContainer.setActive(id, false);
            } else {
                myView.passCommand(String.format("tell [ %s ]", id));
                mySpriteContainer.setActive(id, true);
            }

        });
    }

    /**
     * Move the specified turtle to a new position through animation
     *
     * @param id          The identifier for the turtle to move
     * @param newPosition The {@code Coordinate} to move the turtle to
     */
    public void moveTurtle(String id, Coordinate newPosition) {
        myPathManager.addPath(id, new Line(mySpriteContainer.getSprite(id).getPosition().getX() + mySpriteContainer.getSpriteSize() / 2, mySpriteContainer.getSprite(id).getPosition().getY() + mySpriteContainer.getSpriteSize() / 2,
                newPosition.getX() + mySpriteContainer.getSpriteSize() / 2, newPosition.getY() + mySpriteContainer.getSpriteSize() / 2));
        myMovementManager.addTurtleAnimation(id, mySpriteContainer.getSprite(id), mySpriteContainer.getSprite(id).getPosition(), newPosition);
        mySpriteContainer.getSprite(id).setPosition(newPosition);
    }

    /**
     * Update the visual display of the turtle with animations after {@code moveTurtle()} has been run
     */
    public void updateTurtles() {
        for (String id : myTurtles) {
            mySpriteContainer.getSprite(id).toFront();
            myMovementManager.playTurtleAnimation(id);
            myPathManager.playPathDrawAnimation(id);
        }
    }

    /**
     * Render the specified turtle to the root
     *
     * @param id The identifier for the turtle to render
     */
    public void show(String id) {
        mySpriteContainer.getSprite(id).show();
    }

    /**
     * Hide the specified turtle on the display
     *
     * @param id The identifier for the turtle to hide
     */
    public void hide(String id) {
        mySpriteContainer.getSprite(id).hide();
    }

    /**
     * Sets the active or inactive state of a sprite
     *
     * @param id    The identifier for the turtle to change state
     * @param state False means inactive, True means active
     */
    public void setActive(String id, boolean state) {
        mySpriteContainer.setActive(id, state);
    }

    /** Sets the size of all turtles
     *  @param size The new size to set the turtles to */
    public void setTurtleSize(double size) {
        mySpriteContainer.setSpriteSize(size);
    }

    /** Sets the size of a specific turtle
     *  @param id The identifier for the turtle*/
    public void setTurtleSize(String id, double size) {
        mySpriteContainer.setSpriteSize(id, size);
    }

    /** Change the sprite of all turtles
     *  @param image The new {@code Image} to set the turtle sprites to */
    public void setTurtleImage(Image image) {
        mySpriteContainer.setSpriteImage(image);
    }

    /** Set the pen to be down */
    public void penDown() {
        myPathManager.penDown();
    }

    /** Set the pen to be up*/
    public void penUp() {
        myPathManager.penUp();
    }

    /** Set the size of the pen
     *  @param size The new width in pixels of the pen*/
    public void setPenSize(double size) {
        myPathManager.setPenSize(size);
    }

    /** Returns the current {@code Color} of the pen */
    public Color getPenColor() {
        return myPathManager.getMyPen().getMyColor();
    }

    /** Sets the color of the pen
     *  @param color The new {@code Color} for the pen to use*/
    public void setPenColor(Color color) {
        myPathManager.setPenColor(color);
    }

    /** Create a temporary rendering of a turtle at the origin*/
    public void makeFake() {
        if (!myRenderTarget.getChildren().contains(myFake)) {
            myRenderTarget.getChildren().add(myFake);
        }
    }

    /** Hide the temporary rendering of the turtle at the origin*/
    public void hideFake() {
        myRenderTarget.getChildren().remove(myFake);
    }

    /**
     * Sets duration of all time-based turtle operations
     *
     * @param duration The duration in seconds for time-based operations to take
     */
    @Override
    public void setDuration(double duration) {
        super.setDuration(duration);
        myMovementManager.setDuration(duration);
        myPathManager.setDuration(duration);
    }

    /**
     * Resets the display
     */
    @Override
    public void reset() {
        myTurtles.clear();
        mySpriteContainer.killAllSprites();
        myAnimationContainer.killAllAnimations();
        myMovementManager.reset();
        myPathManager.reset();
        myView.resetTurtle(); // make the slider back to default value and set the path speed
    }
}
