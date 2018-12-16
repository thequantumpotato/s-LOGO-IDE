package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.Display.DisplayView;
import frontend.GUI.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * UI ToolBar for the user to graphically manipulate turtle settings, including pen color, background color,
 * language setting, animation speed, pen size, pen state (down or up), and turtle image
 *
 * @author Vincent LIu
 * @author bpx
 **/

public class SettingView implements SubView {
    private final ObservableList<String> list =
            FXCollections.observableArrayList(
                    "English", "Chinese", "French", "German",
                    "Italian", "Portuguese", "Russian", "Spanish", "Urdu");
    VBox speedBox = new VBox();
    private Stage turtleImagesWindow;
    private ToolBar settingView;
    private ComboBox languageBox;
    private View myView;
    private CheckBox penDownCheckbox;
    private ColorPicker penColorPicker;
    private Slider penSizeSlider;
    private Slider speedSlider;
    private ColorPicker bgColorPicker;
    private Button undoButton;
    private Button redoButton;

    public SettingView(View myView_, String initLang) {
        myView = myView_;
        turtleImagesWindow = setUpTurtleImagesWindow();
        settingView = new ToolBar();
        settingView.getStyleClass().add("settingView");

        VBox bgBox = setUpBgColorPicker();
        VBox penBox = setUpPenColorPicker();
        penDownCheckbox = setUpPenDownCheckbox();
        VBox penSizeBox = setUpPenSizeSlider();
        Button turtleButton = setUpTurtleImgChooser();
        setUpLangComboBox(initLang);
        VBox speedBox = setUpTurtleSpeedBox();
        undoButton = new Button("Undo");
        undoButton.setOnAction(event -> myView_.getMyGUIWrapper().undo());

        redoButton = new Button("Redo");
        redoButton.setOnAction(event -> myView_.getMyGUIWrapper().redo());

        settingView.getItems().addAll(bgBox, penBox, penDownCheckbox, penSizeBox, speedBox, turtleButton, languageBox, undoButton, redoButton);
    }

    private Stage setUpTurtleImagesWindow() {
        Group root = new Group();
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setScene(scene);
        VBox imageBox = new VBox(5.0);
        ScrollPane imagesHolder = new ScrollPane(imageBox);
        final FileChooser fileChooser = new FileChooser();
        for(String id : myView.getTurtleImages().keySet()){
            Button button = new Button();
            button.setGraphic(new ImageView(myView.getTurtleImages().get(id)));
            imageBox.getChildren().add(button);
            button.setOnMousePressed(event -> {
                File file = fileChooser.showOpenDialog(myView.getMyStage());
                if (file != null) {
                    myView.changeTurtleImg(id,new Image(file.toURI().toString()));
                    button.setGraphic(new ImageView(new Image(file.toURI().toString())));
                }
            });
        }
        root.getChildren().add(imagesHolder);
        return window;
    }

    private VBox setUpTurtleSpeedBox() {
        speedBox = new VBox();
        Label speedLabel = new Label("Animation Speed: " + myView.DEFAULT_PEN_TIME);
        speedSlider = new Slider();
        speedSlider.setMin(1);
        speedSlider.setMax(100);
        speedSlider.setValue(myView.DEFAULT_PEN_TIME);
        speedSlider.setShowTickMarks(true);
        speedSlider.setShowTickLabels(true);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setSnapToTicks(true);
        speedSlider.setBlockIncrement(1);
        speedSlider.setOnMouseReleased(e -> {
            myView.changeAnimationSpeed(2 / speedSlider.getValue());
        });
        speedSlider.valueProperty().addListener((ov, old_val, new_val) -> {
            speedLabel.setText("Animation Speed: " + String.valueOf(new_val.doubleValue()));
        });
        speedBox.getChildren().addAll(speedLabel, speedSlider);
        return speedBox;
    }

    public void resetTurtleSpeedBox() {
        speedSlider.setValue(10.0);
        setUpTurtleSpeedBox();
    }


    private VBox setUpPenSizeSlider() {
        VBox sizeBox = new VBox();
        Label sizeLabel = new Label("Pen Size: " + myView.DEFAULT_PEN_SIZE);
        penSizeSlider = new Slider();
        penSizeSlider.setMin(1);
        penSizeSlider.setMax(20);
        penSizeSlider.setValue(myView.DEFAULT_PEN_SIZE);
        penSizeSlider.setShowTickMarks(true);
        penSizeSlider.setShowTickLabels(true);
        penSizeSlider.setMajorTickUnit(1);
        penSizeSlider.setSnapToTicks(true);
        penSizeSlider.setBlockIncrement(1);
        penSizeSlider.setOnMouseReleased(e -> {
            myView.changePenSize(penSizeSlider.getValue());
        });
        penSizeSlider.valueProperty().addListener((ov, old_val, new_val) -> {
            sizeLabel.setText("Pen Size: " + String.valueOf(new_val.doubleValue()));
        });
        sizeBox.getChildren().addAll(sizeLabel, penSizeSlider);
        return sizeBox;
    }

    private void setUpLangComboBox(String initLang) {
        languageBox = new ComboBox<>(list);
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
            turtleImagesWindow = setUpTurtleImagesWindow();
            turtleImagesWindow.show();
        });
        return turtleButton;
    }

    private VBox setUpPenColorPicker() {
        VBox penBox = new VBox();
        penColorPicker = new ColorPicker();
        Label penLabel = new Label("Pen Color:");
        penColorPicker.setPromptText("Pen Color");
        penColorPicker.setOnAction(e -> {
            myView.getMyGUIWrapper().changePenColor(penColorPicker.getValue());
        });
        penBox.getChildren().addAll(penLabel, penColorPicker);
        return penBox;
    }

    private CheckBox setUpPenDownCheckbox() {
        CheckBox cb = new CheckBox("Pen Down");
        cb.setAllowIndeterminate(false);
        cb.setIndeterminate(false);
        cb.setSelected(true);
        cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                myView.changePenDown(newValue);
            }
        });
        return cb;
    }

    private VBox setUpBgColorPicker() {
        VBox bgBox = new VBox();
        bgColorPicker = new ColorPicker();
        Label bgLabel = new Label("Background Color:");
        bgColorPicker.setPromptText("Background Color");
        bgColorPicker.setValue(DisplayView.DEFAULT_BG_COLOR);
        bgColorPicker.setOnAction(e -> {
            myView.changeBgColor(bgColorPicker.getValue());
        });
        bgBox.getChildren().addAll(bgLabel, bgColorPicker);
        return bgBox;
    }

    /**
     * External sync method to make sure pen {@code CheckBox} reflects actual pendown status
     *
     * @param state True means pen is down, false means pen is up
     */
    public void setPenDown(boolean state) {
        penDownCheckbox.setSelected(state);
    }

    /**
     * External sync method to make sure pen {@code ColorPicker} reflects actual pen color
     *
     * @param color The {@code Color} to set the {@code ColorPicker} to
     */
    public void setPenColor(Color color) {
        penColorPicker.setValue(color);
    }

    /**
     * External sync method to make sure pen size {@code Slider} reflects actual pen size
     *
     * @param size The size to set the pen size {@code Slider} to
     */
    public void setPenSize(double size) {
        if (size > 0) {
            penSizeSlider.setValue(size);
        }
    }

    /**
     * External sync method to ensure background {@code ColorPicker} reflects actual background color
     *
     * @param color The {@code Color} to set the {@code ColorPicker} to
     */
    public void setBackgroundColor(Color color) {
        bgColorPicker.setValue(color);
    }

    @Override
    public Node getView() {
        return settingView;
    }
}
