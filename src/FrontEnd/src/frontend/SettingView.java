package frontend;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

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
        bgColorPicker.setValue(myView.DEFAULT_BG_COLOR);
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

        VBox turtleBox = new VBox();
        final FileChooser fileChooser = new FileChooser();

        final Button turtleButton = new Button("Choose Turtle Image");
        turtleBox.getChildren().add(turtleButton);

        turtleButton.setOnAction((final ActionEvent e) -> {
            File file = fileChooser.showOpenDialog(myView.getMyStage());
            if (file != null) {
                myView.changeTurtleImg(new Image(file.toURI().toString()));
            }
        });
        settingView.getChildren().addAll(bgBox, penBox, turtleBox);
    }

    @Override
    public Node getView() {
        return settingView;
    }
}
