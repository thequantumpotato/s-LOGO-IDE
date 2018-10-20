package frontend;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingView implements SubView {
    private VBox settingView;
    private View myView;

    /** TO DO: implement a tool bar to enclose all settings */

    public SettingView(View myView_) {
        myView = myView_;
        settingView = new VBox();
        settingView.getStyleClass().add("settingView");

        VBox bgBox = new VBox();
        Label bgLabel = new Label("Background Color: ");
        ColorPicker bgColorPicker = new ColorPicker();
        bgColorPicker.setOnAction(e -> {
            myView.changeBgColor(bgColorPicker.getValue());
        });
        bgBox.getChildren().addAll(bgLabel, bgColorPicker);

        VBox penBox = new VBox();
        Label penLabel = new Label("Pen Color: ");
        ColorPicker penColorPicker = new ColorPicker();
        penColorPicker.setOnAction(e -> {
            myView.changePenColor(penColorPicker.getValue());
        });
        penBox.getChildren().addAll(penLabel, penColorPicker);

        settingView.getChildren().addAll(bgBox, penBox);
    }

    @Override
    public Node getView() {
        return settingView;
    }
}
