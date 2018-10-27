package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * An HBox that contains a TextArea to allow user input command text. When the submit button is clicked,
 * the command will be passed to the ViewAPI, the Controller and the Parser.
 *
 * @author Vincent Liu
 */
// TODO: 10/25/18 Reset turtle button
public class CommandView implements SubView {
    private GridPane commandView;
    private TextArea input;
    private Button submitButton;
    private View myView;
    private VBox buttonBox;
    private Button loadButton;

    public CommandView(View myView_) {
        myView = myView_;
        setUpCommandView();
        setUpInput();
        setUpLoadButton();
        setUpRunButton();
        addElements();
    }

    private void addElements() {
        buttonBox.getChildren().addAll(submitButton, loadButton);
        commandView.add(input, 0, 0);
        commandView.add(buttonBox, 1, 0);
    }

    // TODO: 10/27/18 Test if the button works in loading file and adding them into the Input box 
    private void setUpLoadButton() {
        final FileChooser fileChooser = new FileChooser();
        final Button turtleButton = new Button("Turtle Image");
        turtleButton.setOnAction((final ActionEvent e) -> {
            File file = fileChooser.showOpenDialog(myView.getMyStage());
            if (file != null && file.getName().endsWith(".logo")) {
                // add command to textarea
            }
        });
    }

    private void setUpRunButton() {
        submitButton = new Button("Run");
        submitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        submitButton.setOnAction(action ->
                runCommand());
    }

    private void setUpCommandView() {
        commandView = new GridPane();
        var column1 = new ColumnConstraints();
        column1.setPercentWidth(90);
        var column2 = new ColumnConstraints();
        column2.setPercentWidth(10);
        commandView.getColumnConstraints().addAll(column1, column2);
        commandView.getStyleClass().add("commandView");
    }

    private void setUpInput() {
        input = new TextArea();
        input.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                runCommand();
            }
        });
    }

    private void runCommand() {
        try {
            myView.passCommand(input.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        input.setText("");
    }

    @Override
    public Node getView() {
        return commandView;
    }
}
