package frontend.Display;

import frontend.Coordinate;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static frontend.Display.DisplayView.TURTLE_DEFAULT_X;
import static frontend.Display.DisplayView.TURTLE_DEFAULT_Y;
import static frontend.Display.DisplayView.TURTLE_IMAGE;

/** Class that encapsulates all the behavior needed to manipulate the turtle sprite on the GUI
 * @author bpx
 */
public class TurtleView {
    public static final int TURTLE_SIZE = 25;
    private double myX;
    private double myY;
    private double myAngle;
    private double myWidth;
    private double myHeight;

    private Pen myPen;
    private ImageView myView;
    private Group myRoot;

    /** Default constructor**/
    public TurtleView(Pen pen, Group root){
        this(TURTLE_DEFAULT_X,TURTLE_DEFAULT_Y,TURTLE_IMAGE, pen, root);
    }

    /** Full constructor
     * @param xpos The initial X position of the {@code TurtleView}
     * @param ypos The initial Y position of the {@code TurtleView}
     * @param image The path to image file representing the turtle sprite
     * @param pen The reference to the Pen object that will be used to trace the turtle's path
     * @param root The reference to the Group to which {@code TurtleView} will render its turtle sprite to
     */
    public TurtleView(double xpos, double ypos, String image, Pen pen, Group root){
        myX = xpos;
        myY = ypos;
        myAngle = 0;
        myWidth = TURTLE_SIZE;
        myHeight = TURTLE_SIZE;
        myPen = pen;
        myView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(image)));
        myView.setFitWidth(myWidth);
        myView.setFitHeight(myHeight);
        myRoot = root;
        myRoot.getChildren().add(myView);
    }

    public void show(){
        if(!myRoot.getChildren().contains(myView)){
            myRoot.getChildren().add(myView);
        }
    }

    public void hide(){
        myRoot.getChildren().remove(myView);
    }

    public void setPosition(Coordinate coordinate){
        myX = coordinate.getX();
        myY = coordinate.getY();
        myAngle = coordinate.getAngle();
        myView.setTranslateX(myX);
        myView.setTranslateY(myY);
        myView.setRotate(myAngle);
    }

}
