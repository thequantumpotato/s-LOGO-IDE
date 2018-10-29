package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.Display.PathManager;
import frontend.GUI.View;
import frontend.Util.Pen;
import frontend.Util.Sprite;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


// onclick/hover on a turtle's x and y (javafx coor vs. relative coor)
// -> get turtle from backend -> pass all the information of the turtle through receiveTurtle(Turtle)
// -> present the information

public class StateView implements SubView {
    private VBox stateView = new VBox();
    private ListView stateList = new ListView();

    public StateView(){
        stateView.getChildren().addAll(new Label("Turtle State"), stateList);
        stateView.getStyleClass().add("stateView");
        showDefault();
    }

    public void showDefault(){
        stateList.getItems().clear();
        setUpTurtleFields();
        setUpPenFields();
    }

    public void setUpPenFields(){
        Text penColorT = new Text("Pen Color: ");
        Text penDownT = new Text("Pen State ");
        Text penSizeT = new Text("Pen Size: ");
        stateList.getItems().addAll(penColorT, penDownT, penSizeT);
    }

    public void setUpTurtleFields(){
        Text idT = new Text("ID: ");
        Text xT = new Text("x: ");
        Text yT = new Text("y: ");
        Text directionT = new Text("Direction: ");
        stateList.getItems().addAll(idT, xT, yT, directionT);
    }

    public void changeState(String id, Sprite sprite, PathManager pathManager){
        stateList.getItems().clear();
        updateTurtleVars(id, sprite);
        updatePenVars(pathManager);
    }

    private void updatePenVars(PathManager pathManager) {
        Pen myPen = pathManager.getMyPen();
        boolean penDown = pathManager.isPenDown();
        Color penColor = myPen.getMyColor();
        double penSize = myPen.getMySize();
        setUpdatedPenFields(penDown, penColor, penSize);
    }

    private void updateTurtleVars(String id, Sprite sprite) {
        double x = sprite.getMyX();
        double y = sprite.getMyY();
        double direction = sprite.getMyAngle();
        setUpdatedTurtleFields(id, x, y, direction);
    }

    public void setUpdatedPenFields(boolean penDown, Color penColor, double penSize){
        Text penColorT = new Text("Pen Color: " + penColor);
        Text penDownT = new Text("Pen State " + (penDown ? "Down" : "Up"));
        Text penSizeT = new Text("Pen Size: " + penSize);
        stateList.getItems().addAll(penColorT, penDownT, penSizeT);
    }

    public void setUpdatedTurtleFields(String id, double x, double y, double direction){
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
