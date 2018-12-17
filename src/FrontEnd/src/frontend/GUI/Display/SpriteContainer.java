package frontend.GUI.Display;

import frontend.Util.Sprite;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.HashMap;

import static frontend.GUI.Display.DisplayView.TURTLE_IMAGE;
import static frontend.GUI.Display.DisplayView.TURTLE_SIZE;

/**
 * This class manages the sprites contained in {@code DisplayView}
 *
 * @author bpx
 */
public class SpriteContainer {
    private Group myRenderTarget;
    private HashMap<String, Sprite> mySprites;
    private HashMap<String, Boolean> myStates;
    private String mySpritePath;
    private double mySpriteSize;

    /**
     * Constructor initializes a new SpriteContainer with all default settings
     */
    public SpriteContainer(Group renderTarget) {
        myRenderTarget = renderTarget;
        mySprites = new HashMap<>();
        myStates = new HashMap<>();
        mySpritePath = TURTLE_IMAGE;
        mySpriteSize = TURTLE_SIZE;
    }

    public void setSpriteImage(Image image) {
        for (String s : mySprites.keySet()) {
            mySprites.get(s).setImage(image);
        }
    }

    /**
     * Sets the image height and width of a specific sprite
     *
     * @param id   The identifier for the sprite to change
     * @param size The new pixel height and width dimensions of the sprite
     */
    public void setSpriteSize(String id, double size) {
        mySprites.get(id).setSize(size);
    }

    /**
     * Returns a non-modifiable copy of the current sprite size
     */
    public double getSpriteSize() {
        return Double.valueOf(mySpriteSize);
    }

    /**
     * Sets the image height and width of all current future sprites
     *
     * @param size The pixel height and width dimensions of all new sprites
     */
    public void setSpriteSize(double size) {
        if (size > 0) {
            mySpriteSize = size;
        }
        for (String s : mySprites.keySet()) {
            mySprites.get(s).setSize(size);
        }
    }

    /**
     * Add a new {@code Sprite} using current settings
     *
     * @param id The identifier for the {@code Sprite} in the HashMap
     */
    public void addSprite(String id) {
        Sprite turtle = new Sprite(new Image(this.getClass().getClassLoader().getResourceAsStream(mySpritePath)));
        turtle.setFitWidth(mySpriteSize);
        turtle.setFitHeight(mySpriteSize);
        mySprites.put(id, turtle);
        myStates.put(id, true);
        myRenderTarget.getChildren().add(turtle);
        if(!id.equals("0")){
            turtle.setPosition(mySprites.get(String.valueOf(Integer.parseInt(id)-1)).getPosition());
        }
    }

    /**
     * Retrieve a sprite from the HashMap
     *
     * @param id The identifier for the sprite that will be retrieved
     */
    public Sprite getSprite(String id) {
        if (mySprites.containsKey(id)) {
            return mySprites.get(id);
        } else {
            return new Sprite(new Image(this.getClass().getClassLoader().getResourceAsStream(mySpritePath)));
        }

    }

    /**
     * Sets a sprite to be active or inactive visually
     *
     * @param id    The identifier for the turtle to change
     * @param state Active is true, inactive is false
     */
    public void setActive(String id, boolean state) {
        if (state) {
            myStates.put(id, true);
            mySprites.get(id).setActive();
        } else {
            myStates.put(id, false);
            mySprites.get(id).setInactive();
        }
    }

    /** Returns whether the queried sprite is currently active or not
     *  @param id The identifier for the sprite*/
    public boolean isActive(String id) {
        return myStates.get(id);
    }

    /**
     * Remove all entries from the HashMap of sprites
     */
    public void killAllSprites() {
        mySprites.forEach((s, imageView) -> myRenderTarget.getChildren().remove(imageView));
        mySprites.clear();
        myStates.clear();
    }


}
