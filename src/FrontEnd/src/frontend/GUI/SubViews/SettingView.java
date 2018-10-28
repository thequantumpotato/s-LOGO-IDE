package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.Display.DisplayView;
import frontend.GUI.View;
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
    private ToolBar settingView;
    private ComboBox languageBox;
    private View myView;

    // TODO add option to change pen size and pen state?
    public SettingView(View myView_, String initLang) {
        myView = myView_;
        settingView = new ToolBar();
        settingView.getStyleClass().add("settingView");

        VBox bgBox = setUpBgColorPicker();
        VBox penBox = setUpPenColorPicker();
        CheckBox penDownCheckbox = setUpPenDownCheckbox();
        Button turtleButton = setUpTurtleImgChooser();
        setUpLangComboBox(initLang);
        VBox speedBox = setUpTurtleSpeedBox();

        settingView.getItems().addAll(bgBox, penBox, penDownCheckbox,speedBox, turtleButton, languageBox);
    }

    private VBox setUpTurtleSpeedBox() {
        VBox speedBox = new VBox();
        Label speedLabel = new Label("Animation Speed: " + myView.DEFAULT_PEN_TIME);
        Slider speedSlider = new Slider();
        speedSlider.setMin(1);
        speedSlider.setMax(100);
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
        return speedBox;
    }

    private void setUpLangComboBox(String initLang) {
        languageBox = new ComboBox();
        languageBox.getItems().addAll("English", "Chinese", "French", "German", "Italian", "Portuguese", "Russian", "Spanish");
        languageBox.setValue(initLang);
        languageBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                myView.changeLanguage(t1);
            }
        });
    }

    private Button setUpTurtleImgChooser() {
        final FileChooser fileChooser = new FileChooser();
        final Button turtleButton = new Button("Turtle Image");
        turtleButton.setOnAction((final ActionEvent e) -> {
            File file = fileChooser.showOpenDialog(myView.getMyStage());
            if (file != null) {
                myView.changeTurtleImg(new Image(file.toURI().toString()));
            }
        });
        return turtleButton;
    }

    private VBox setUpPenColorPicker() {
        VBox penBox = new VBox();
        ColorPicker penColorPicker = new ColorPicker();
        Label penLabel = new Label("Pen Color:");
        penColorPicker.setPromptText("Pen Color");
        penColorPicker.setOnAction(e -> {
            myView.changePenColor(penColorPicker.getValue());
        });
        penBox.getChildren().addAll(penLabel, penColorPicker);
        return penBox;
    }

    private CheckBox setUpPenDownCheckbox(){
        CheckBox cb = new CheckBox("Pen Down");
        cb.setAllowIndeterminate(false);
        cb.setIndeterminate(false);
        cb.setSelected(true);
        cb.selectedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                myView.changePenDown(newValue);
            }
        });
        return cb;
    }

    private VBox setUpBgColorPicker() {
        VBox bgBox = new VBox();
        ColorPicker bgColorPicker = new ColorPicker();
        Label bgLabel = new Label("Background Color:");
        bgColorPicker.setPromptText("Background Color");
        bgColorPicker.setValue(DisplayView.DEFAULT_BG_COLOR);
        bgColorPicker.setOnAction(e -> {
            myView.changeBgColor(bgColorPicker.getValue());
        });
        bgBox.getChildren().addAll(bgLabel, bgColorPicker);
        return bgBox;
    }

    @Override
    public Node getView() {
        return settingView;
    }
}
