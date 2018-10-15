package frontend;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class DisplayView implements SubView {

    private VBox DisplayBox;
    private ImageView turtleView;
    private int turtleX;
    private int turtleY;
    private int turtleAngle;

    public DisplayView(Image image) {
        DisplayBox = new VBox();
        turtleView = new ImageView(image);
        DisplayBox.getChildren().add(turtleView);
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
