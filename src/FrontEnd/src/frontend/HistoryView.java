package frontend;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class HistoryView implements SubView {
    private ScrollPane historyView;
    private View myView;

    public HistoryView(View myView_) {
        myView = myView_;
        historyView = new ScrollPane();
        historyView.getStyleClass().add("historyView");
    }

    @Override
    public Node getView() {
        return historyView;
    }
}
