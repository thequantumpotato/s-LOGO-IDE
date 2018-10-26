package frontend.GUI.Display;

import frontend.Util.Coordinate;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {

    public static final int INACTIVE_OPACITY = 50;
    public static final int SHOW_OPACITY = 100;

    public Sprite(Image image){
        super(image);
        this.setPreserveRatio(true);
    }

    public void hide(){
        this.setOpacity(0);
    }

    public void show(){
        this.setOpacity(SHOW_OPACITY);
    }

    public void setInactive(){
        this.setEffect(new ColorAdjust(0.0,-1.0,0.0,0.0));
        this.setOpacity(INACTIVE_OPACITY);
    }

    public void setActive(){
        this.setEffect(null);
        this.setOpacity(100);
    }

    public void setPosition(Coordinate coordinate){
        this.setTranslateX(coordinate.getX());
        this.setTranslateY(coordinate.getY());
        this.setRotate(coordinate.getAngle());
    }
}
