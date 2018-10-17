package frontend;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * DisplayView contains the display of the turtle as well as the panel for the user to change the
 * settings (eg. Pen color, background color, and turtle image). It is the playground for the turtle
 * to move around and draw different shapes.
 *
 * @author Vincent Liu
 */

public class DisplayView implements SubView {

    private GridPane displayView;
    private VBox turtleBox;
    private VBox settingBox;
    private ImageView turtleView;
    private int turtleX;
    private int turtleY;
    private int turtleAngle;

    public DisplayView(Image image, Coordinate turtleCoordinate_) {
        displayView = new GridPane();
        displayView.getStyleClass().add("displayView");

        var col1 = new ColumnConstraints();
        col1.setPercentWidth(20);
        var col2 = new ColumnConstraints();
        col2.setPercentWidth(80);

        displayView.getColumnConstraints().addAll(col1, col2);

        turtleBox = new VBox();
        turtleBox.getStyleClass().add("turtleBox");
        settingBox = new VBox();
        settingBox.getStyleClass().add("settingBox");

        turtleView = new ImageView(image);
        turtleBox.getChildren().add(turtleView);
        setTurtlePos(turtleCoordinate_);

        displayView.add(settingBox, 0, 0);
        displayView.add(turtleBox, 1,0);
    }

    public void setTurtlePos(Coordinate turtleCoordinate) {
        turtleX = turtleCoordinate.getX();
        turtleY = turtleCoordinate.getY();
        turtleAngle = turtleCoordinate.getAngle();
        turtleView.setX(turtleX);
        turtleView.setY(turtleY);
        turtleView.setRotate(turtleAngle);
    }

    public void changeBgColor(Color bgColor) {

    }

    public void changePenColor(Color penColor) {

    }

    public void changeTurtleImg(Image newTurtleImg) {

    }

    @Override
    public Node getView() {
        return displayView;
    }
}
