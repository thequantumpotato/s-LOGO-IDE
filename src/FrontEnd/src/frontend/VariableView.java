package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A panel to display all the variables that the user defines.
 *
 * @author Vincent Liu
 */

public class VariableView implements SubView {

    private VBox variableView;
    private View myView;

    public VariableView(View myView_) {
        myView = myView_;
        Label title = new Label("Variables");
        variableView = new VBox(title);
        variableView.getStyleClass().add("variableView");
    }

    @Override
    public Node getView() {
        return variableView;
    }
}
