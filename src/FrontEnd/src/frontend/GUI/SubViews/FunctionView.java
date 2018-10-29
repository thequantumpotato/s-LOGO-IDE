package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.View;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

import java.util.List;

/**
 * A ListView that displays all the previous valid commands
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

    // add all the functions from the backend to the frontend to be displayed
    public void updateFunction(List<String> func) {
        functionList.getItems().clear();
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
