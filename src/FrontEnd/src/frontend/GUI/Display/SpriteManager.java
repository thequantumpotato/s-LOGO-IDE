package frontend.GUI.Display;

import frontend.Util.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

import static frontend.GUI.Display.DisplayView.TURTLE_IMAGE;
import static frontend.GUI.Display.TurtleView.TURTLE_SIZE;

/**
 * This class manages the sprites contained in {@code DisplayView}
 * @author bpx
 */
public class SpriteManager {
    private HashMap<String,ImageView> mySprites;
    private String mySpritePath;
    private double mySpriteSize;

    /** Constructor initializes a new SpriteManager with all default settings*/
    public SpriteManager(){
        mySprites = new HashMap<>();
        mySpritePath = TURTLE_IMAGE;
        mySpriteSize = TURTLE_SIZE;
    }

    /** Sets the image path for all future sprites
     *  @param newPath The path to the image file to be used as the sprite*/
    public void setSpritePath(String newPath){
        if(newPath!=null){
            mySpritePath = newPath;
        }
    }

    /** Sets the image height and width of all future sprites
     *  @param size The pixel height and width dimensions of all new sprites*/
    public void setSpriteSize(double size){
        if(size>0){
            mySpriteSize = size;
        }
    }

    /** Add a new {@code Sprite} using current settings
     *  @param id The identifier for the {@code Sprite} in the HashMap*/
    public void addTurtle(String id){
        Sprite turtle = new Sprite(new Image(this.getClass().getClassLoader().getResourceAsStream(mySpritePath)));
        turtle.setFitWidth(mySpriteSize);
        turtle.setFitHeight(mySpriteSize);
        mySprites.put(id,turtle);
    }

    /** Retrieve a sprite from the HashMap
     * @param id The identifier for the sprite that will be retrieved*/
    public ImageView getTurtle(String id){
        return mySprites.get(id);
    }

    /** Remove all entries from the HashMap of sprites*/
    public void killAllTurtles(){
        mySprites.clear();
    }


}
