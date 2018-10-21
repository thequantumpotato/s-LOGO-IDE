package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * A panel to display all the variables that the user defines.
 *
 * @author Vincent Liu
 */

public class VariableView implements SubView {

    private VBox variableView;
    private ListView variableList;
    private View myView;

    public VariableView(View myView_) {
        myView = myView_;
        variableList = new ListView();
        Label title = new Label("Variables");
        variableView = new VBox();
        variableView.getChildren().addAll(title, variableList);
        variableView.getStyleClass().add("variableView");
    }

    public void updateVariable(Map<String, String> var) {

    }

    @Override
    public Node getView() {
        return variableView;
    }
}
