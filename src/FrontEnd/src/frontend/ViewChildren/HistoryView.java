package frontend.ViewChildren;

import frontend.API.SubView;
import frontend.View;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * A ListView that displays all the previous valid commands
 * @author Vincent Liu
 */

public class HistoryView implements SubView {
    private VBox historyView;
    private ListView historyList;
    private View myView;

    public HistoryView(View myView_) {
        myView = myView_;
        historyView = new VBox();
        Label title = new Label("Command History: ");
        historyList = new ListView();
        historyView.getChildren().addAll(title, historyList);
        historyView.getStyleClass().add("historyView");
    }

    public void updateHistory(String newHistory) {
        var clickableHistory = new Hyperlink(newHistory);
        clickableHistory.setOnAction( e -> runCommand(clickableHistory.getText()));
        historyList.getItems().add(clickableHistory);
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
        return historyView;
    }
}
