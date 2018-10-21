package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * A panel to display all the functions that the user defines.
 *
 * @author Vincent Liu
 */
public class FunctionView implements SubView {

    private VBox functionView;
    private ListView functionList;
    private View myView;

    public FunctionView(View myView_) {
        myView = myView_;
        functionList = new ListView();
        Label title = new Label("Functions");
        functionView = new VBox();
        functionView.getChildren().addAll(title, functionList);
        functionView.getStyleClass().add("functionView");
    }

    public void updateFunction(Map<String, String> func) {

    }

    @Override
    public Node getView() {
        return functionView;
    }
}
