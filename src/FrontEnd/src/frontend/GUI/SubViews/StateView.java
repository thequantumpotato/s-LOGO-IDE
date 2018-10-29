package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.Display.PathManager;
import frontend.Util.Pen;
import frontend.Util.Sprite;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * The panel that shows all the information about the turtle and the pen.
 * <p>
 * When the mouse is not hovering above a turtle, the StateView will display just the attribute name
 * but not the content.
 * <p>
 * When the mouse is hovering above a turtle, StateView show turtle's x and y and direction,
 * its active state, and the pen's size, color, and whether the pen is up or down.
 *
 * @author Vincent Liu
 */
public class StateView implements SubView {
    private VBox stateView = new VBox();
    private ListView stateList = new ListView();

    public StateView() {
        stateView.getChildren().addAll(new Label("Turtle State"), stateList);
        stateView.getStyleClass().add("stateView");
        showDefault();
    }

    /**
     * Only show the attribute name but not the content.
     * This method is called when the mouse exits the Sprite.
     */
    public void showDefault() {
        stateList.getItems().clear();
        setUpTurtleFields();
        setUpPenFields();
    }

    public void setUpPenFields() {
        Text penColorT = new Text("Pen Color: ");
        Text penDownT = new Text("Pen State: ");
        Text penSizeT = new Text("Pen Size: ");
        stateList.getItems().addAll(penColorT, penDownT, penSizeT);
    }

    public void setUpTurtleFields() {
        Text idT = new Text("ID: ");
        Text xT = new Text("x: ");
        Text yT = new Text("y: ");
        Text directionT = new Text("Direction: ");
        stateList.getItems().addAll(idT, xT, yT, directionT);
    }

    /**
     * This command is passed by View from the TurtleManager when the mouse is hovering above a Sprite.
     * It changes the information presented on the StateView
     *
     * @param id
     * @param sprite
     * @param pathManager
     */
    public void changeState(String id, Sprite sprite, PathManager pathManager) {
        stateList.getItems().clear();
        updateTurtleVars(id, sprite);
        updatePenVars(pathManager);
    }

    /**
     * After receiving information about the Sprite and the turtle id, extract necessary information to present,
     * including the x, y, direction, and of course, id itself
     *
     * @param id
     * @param sprite
     */
    private void updateTurtleVars(String id, Sprite sprite) {
        double x = sprite.getMyX();
        double y = sprite.getMyY();
        double direction = sprite.getMyAngle();
        setUpdatedTurtleFields(id, x, y, direction);
    }

    /**
     * After receiving information about the PathManager, extract necessary information to present, including
     * pen's state (down or up), color, and size
     *
     * @param pathManager
     */
    private void updatePenVars(PathManager pathManager) {
        Pen myPen = pathManager.getMyPen();
        boolean penDown = pathManager.isPenDown();
        Color penColor = myPen.getMyColor();
        double penSize = myPen.getMySize();
        setUpdatedPenFields(penDown, penColor, penSize);
    }

    public void setUpdatedPenFields(boolean penDown, Color penColor, double penSize) {
        Text penColorT = new Text("Pen Color: " + penColor);
        Text penDownT = new Text("Pen State: " + (penDown ? "Down" : "Up"));
        Text penSizeT = new Text("Pen Size: " + penSize);
        stateList.getItems().addAll(penColorT, penDownT, penSizeT);
    }

    public void setUpdatedTurtleFields(String id, double x, double y, double direction) {
        Text idT = new Text("ID: " + id);
        Text xT = new Text("x: " + x);
        Text yT = new Text("y: " + y);
        Text directionT = new Text("Direction: " + direction);
        stateList.getItems().addAll(idT, xT, yT, directionT);
    }

    @Override
    public Node getView() {
        return stateView;
    }
}
