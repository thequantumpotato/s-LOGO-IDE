package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.View;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * A ListView that displays all the previous valid commands
 *
 * @author Vincent Liu
 */


/**
 * A panel (using TableView) to contain and display all the functions that the user defines.
 * The functions are editable, and the new values entered by the use will be updated in the backend.
 *
 * @author Vincent Liu
 */
public class FunctionView implements SubView {


    private final TitledPane functionView = new TitledPane();
    private final ListView functionList = new ListView();
    private View myView;

    public FunctionView(View myView_) {
        myView = myView_;
        setUpList();
    }

    private void setUpList() {
        functionView.setText("Functions");
        functionView.setContent(functionList);
        functionView.getStyleClass().add("functionView");
    }

    public void updateFunction(List<String> func) {
        for (int i = 0; i < func.size(); i++) {
            var clickableFunction = new Hyperlink(func.get(i));
            clickableFunction.setOnAction(e -> runCommand(clickableFunction.getText()));
            functionList.getItems().add(clickableFunction);
        }
    }

    private void runCommand(String text) {
        try {
            myView.passCommand(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Node getView() {
        return functionView;
    }
}
