package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HistoryView implements SubView {
    private VBox historyView;
    private ScrollPane historyPane;
    private ListView historyBox;
    private View myView;

    public HistoryView(View myView_) {
        myView = myView_;
        historyPane = new ScrollPane();
        historyPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        historyView = new VBox();
        Label title = new Label("Command History: ");
        historyBox = new ListView();
        historyView.getChildren().addAll(title, historyBox);

        historyPane.setContent(historyView);
    }

    public void updateHistory(String newHistory) {
        historyBox.getItems().add(new Text(newHistory));
    }
    
    @Override
    public Node getView() {
        return historyPane;
    }
}
