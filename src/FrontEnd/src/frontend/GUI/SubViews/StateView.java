package frontend.GUI.SubViews;

import frontend.API.SubView;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;

public class StateView implements SubView {
    TitledPane stateView;





    @Override
    public Node getView() {
        return stateView;
    }
}
