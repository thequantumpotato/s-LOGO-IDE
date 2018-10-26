package frontend.ViewChildren.Display;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

import static frontend.GUI.Display.DisplayView.TURTLE_IMAGE;
import static frontend.GUI.Display.TurtleView.TURTLE_SIZE;


public class SpriteManager {
    private HashMap<String,ImageView> mySprites;
    private String mySpritePath;
    private double mySpriteSize;

    public SpriteManager(){
        mySprites = new HashMap<>();
        mySpritePath = TURTLE_IMAGE;
        mySpriteSize = TURTLE_SIZE;
    }

    public void addTurtle(String id){
        Sprite turtle = new Sprite(new Image(this.getClass().getClassLoader().getResourceAsStream(mySpritePath)));
        turtle.setFitWidth(mySpriteSize);
        turtle.setFitHeight(mySpriteSize);
        mySprites.put(id,turtle);
    }

    public ImageView getTurtle(String id){
        return mySprites.get(id);
    }




}
