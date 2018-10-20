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

    private VBox variableBox;
    private ScrollPane variableView;
    private ListView variableList;
    private View myView;

    public VariableView(View myView_) {
        myView = myView_;

        variableView = new ScrollPane();
        variableView.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        variableBox = new VBox();
        Label title = new Label("Variables");
        variableList = new ListView();
        variableBox.getChildren().addAll(title, variableList);

        variableBox.getStyleClass().add("variableBox");
    }

    public void updateVariable(Map<String, String> var) {

    }

    @Override
    public Node getView() {
        return variableBox;
    }
}
