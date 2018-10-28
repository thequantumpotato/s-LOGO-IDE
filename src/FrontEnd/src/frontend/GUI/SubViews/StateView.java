package frontend.GUI.SubViews;

import backend.Turtle;
import frontend.API.SubView;
import frontend.GUI.View;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


// onclick/hover on a turtle's x and y (javafx coor vs. relative coor)
// -> get turtle from backend -> pass all the information of the turtle through receiveTurtle(Turtle)
// -> present the information

public class StateView implements SubView {
    private View myView;
    // turtle info
    private Integer id;
    private double x;
    private double y;
    private double direction;
    private boolean isActive;
    // pen info
    private Color penColor;
    private boolean penDown;
    private double penSize;
    private VBox container = new VBox();
    private TitledPane stateView = new TitledPane();

    //state of a turtle (i.e., its ID, position, heading) and
    // its pen (i.e., up/down, color, thickness)

    public StateView(View myView_){
        myView = myView_;
        stateView.setText("Turtle State");
        setUpContainer();
        stateView.setContent(container);
    }

    private void setUpContainer() {
//        Text id = new Text(id.toString());
    }

    public void receiveTurtle(Turtle turtle){
        turtle.getX();
    }

    @Override
    public Node getView() {
        return stateView;
    }
}
