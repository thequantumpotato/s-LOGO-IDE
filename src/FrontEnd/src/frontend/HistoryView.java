package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HistoryView implements SubView {
    private VBox historyPane;
    private ScrollPane historyView;
    private ListView historyList;
    private View myView;

    public HistoryView(View myView_) {
        myView = myView_;
        historyView = new ScrollPane();
        historyView.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        historyPane = new VBox();
        Label title = new Label("Command History: ");
        historyList = new ListView();
        historyPane.getChildren().addAll(title, historyList);

        historyView.setContent(historyPane);
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
