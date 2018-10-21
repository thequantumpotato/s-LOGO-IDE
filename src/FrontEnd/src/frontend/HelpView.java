package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class HelpView implements SubView {
    private VBox helpView;
    private ListView helpList;

    public HelpView() {
        helpView = new VBox();
        Label title = new Label("Command help: ");
        helpList = new ListView();
        helpView.getChildren().addAll(title, helpList);
        helpView.getStyleClass().add("helpView");
    }

    @Override
    public Node getView() {
        return helpView;
    }
}
