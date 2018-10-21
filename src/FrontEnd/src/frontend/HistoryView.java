package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HistoryView implements SubView {
    private VBox historyView;
    private ListView historyList;

    public HistoryView() {
        historyView = new VBox();
        Label title = new Label("Command History: ");
        historyList = new ListView();
        historyView.getChildren().addAll(title, historyList);
        historyView.getStyleClass().add("historyView");
    }

    public void updateHistory(String newHistory) {
        historyList.getItems().add(new Text(newHistory));
    }

    @Override
    public Node getView() {
        return historyView;
    }
}
