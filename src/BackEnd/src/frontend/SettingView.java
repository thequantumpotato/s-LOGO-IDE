package frontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingView implements SubView {
    private VBox settingView;
    private View myView;

    public SettingView(View myView_) {
        myView = myView_;
        Label title = new Label("Settings");
        settingView = new VBox(title);
        settingView.getStyleClass().add("settingView");
    }

    @Override
    public Node getView() {
        return settingView;
    }
}
