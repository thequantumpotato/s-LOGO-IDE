package frontend;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

public class SettingView implements SubView {
    //    private VBox settingView;
    private ToolBar settingView;
    private ComboBox languageBox;
    private View myView;

    /**
     * TO DO:
     * 1. add option to change pen size and pen state?
     */
    public SettingView(View myView_, String initLang) {
        myView = myView_;
//        settingView = new VBox();
        settingView = new ToolBar();
        settingView.getStyleClass().add("settingView");

        VBox bgBox = new VBox();
        ColorPicker bgColorPicker = new ColorPicker();
        Label bgLabel = new Label("Background Color:");
        bgColorPicker.setPromptText("Background Color");
        bgColorPicker.setValue(myView.DEFAULT_BG_COLOR);
        bgColorPicker.setOnAction(e -> {
            myView.changeBgColor(bgColorPicker.getValue());
        });
        bgBox.getChildren().addAll(bgLabel, bgColorPicker);

        VBox penBox = new VBox();
        ColorPicker penColorPicker = new ColorPicker();
        Label penLabel = new Label("Pen Color:");
        penColorPicker.setPromptText("Pen Color");
        penColorPicker.setOnAction(e -> {
            myView.changePenColor(penColorPicker.getValue());
        });
        penBox.getChildren().addAll(penLabel, penColorPicker);

        final FileChooser fileChooser = new FileChooser();
        final Button turtleButton = new Button("Turtle Image");
        turtleButton.setOnAction((final ActionEvent e) -> {
            File file = fileChooser.showOpenDialog(myView.getMyStage());
            if (file != null) {
                myView.changeTurtleImg(new Image(file.toURI().toString()));
            }
        });

        languageBox = new ComboBox();
        languageBox.getItems().addAll("English", "Chinese", "French", "German", "Italian", "Portuguese", "Russian", "Spanish");
        languageBox.setValue(initLang);
        languageBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                myView.changeLanguage(t1);
            }
        });


        VBox speedBox = new VBox();
        Label speedLabel = new Label("Animation Speed: " + myView.DEFAULT_PEN_TIME);
        Slider speedSlider = new Slider();
        speedSlider.setMin(1);
        speedSlider.setMax(10);
        speedSlider.setValue(myView.DEFAULT_PEN_TIME);
        speedSlider.setShowTickMarks(true);
        speedSlider.setShowTickLabels(true);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setSnapToTicks(true);
        speedSlider.setBlockIncrement(1);
        speedSlider.setOnMouseReleased(e -> {
            myView.changeAnimationSpeed(1 / speedSlider.getValue());
        });
        speedSlider.valueProperty().addListener((ov, old_val, new_val) -> {
            speedLabel.setText("Animation Speed: " + String.valueOf(new_val.doubleValue()));
        });
        speedBox.getChildren().addAll(speedLabel, speedSlider);

        settingView.getItems().addAll(bgBox, penBox, speedBox, turtleButton, languageBox);
    }

    @Override
    public Node getView() {
        return settingView;
    }
}
