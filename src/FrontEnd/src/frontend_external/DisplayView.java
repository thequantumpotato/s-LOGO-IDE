package frontend_external;

import frontend_external.SubView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DisplayView implements SubView {

    private ImageView turtleView;
    private int turtleX;
    private int turtleY;
    private int turtleAngle;

    public DisplayView(Image image) {
        turtleView = new ImageView(image);
    }

    public void setTurtlePos(Coordinate turtleCoordinate){
     turtleView.setX(turtleCoordinate.getX());
     turtleView.setY(turtleCoordinate.getY());
     turtleView.setRotate(turtleCoordinate.getAngle());
    }

    @Override
    public Node getView() {

        return turtleView;
    }
}
