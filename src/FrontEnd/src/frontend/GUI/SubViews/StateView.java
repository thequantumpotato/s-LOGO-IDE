package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.View;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;

public class StateView implements SubView {
    private TitledPane stateView;
    private View myView;


    public StateView(View myView_){

    }



    @Override
    public Node getView() {
        return stateView;
    }
}
