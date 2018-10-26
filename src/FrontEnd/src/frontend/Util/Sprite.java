package frontend.Util;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Custom extension of ImageView for more convenient ImageView manipulation
 * @author bpx
 */
public class Sprite extends ImageView {

    public static final int INACTIVE_OPACITY = 50;
    public static final int SHOW_OPACITY = 100;

    /** Initialize a {@code Sprite} using an {@code Image}
     * @param image The {@code Image} to be displayed*/
    public Sprite(Image image){
        super(image);
    }

    /** Hide the {@code Sprite} by making the {@code Sprite} transparent*/
    public void hide(){
        this.setOpacity(0);
    }

    /** Reveal the {@code Sprite} by making the {@code Sprite} completely opaque*/
    public void show(){
        this.setOpacity(SHOW_OPACITY);
    }

    /** De-emphasizes the {@code Sprite} by transforming colors to greyscale and reducing opacity*/
    public void setInactive(){
        this.setEffect(new ColorAdjust(0.0,-1.0,0.0,0.0));
        this.setOpacity(INACTIVE_OPACITY);
    }

    /** Restores the {@code Sprite} to the original state by removing color transforms
     *  and making the {@code Sprite} completely opaque*/
    public void setActive(){
        this.setEffect(null);
        this.setOpacity(100);
    }

    /** Sets the position of the {@code Sprite} using a {@code Coordinate}
     *  @param coordinate The {@code Coordinate} of the new position to set the {@code Sprite} to*/
    public void setPosition(Coordinate coordinate){
        this.setTranslateX(coordinate.getX());
        this.setTranslateY(coordinate.getY());
        this.setRotate(coordinate.getAngle());
    }
}
