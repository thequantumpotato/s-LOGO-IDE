package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class HistoryView implements SubView {
    private VBox historyView;
    private ScrollPane historyPane;
    private VBox history;
    private View myView;
    private List<String> myHistory;

    public HistoryView(View myView_){
        myView = myView_;
        historyPane = new ScrollPane();
        historyPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        historyView = new VBox();
        historyView.getStyleClass().add("historyView");

        Label title = new Label("Command History");
        historyView.getChildren().addAll(title, historyPane);
    }


    
    @Override
    public Node getView() {
        return historyView;
    }
}
