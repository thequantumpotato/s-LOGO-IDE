package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A panel to display all the functions that the user defines.
 *
 * @author Vincent Liu
 */
public class FunctionView implements SubView {

    private VBox functionView;
    private View myView;

    public FunctionView(View myView_) {
        myView = myView_;
        Label title = new Label("Functions");
        functionView = new VBox(title);
        functionView.getStyleClass().add("functionView");
    }


    @Override
    public Node getView() {
        return functionView;
    }
}
