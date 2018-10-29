package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

/**
 * An HBox that contains a TextArea to allow user input command text. When the submit button is clicked,
 * the command will be passed to the ViewAPI, the Controller and the Parser.
 *
 * @author Vincent Liu
 */
public class CommandView implements SubView {
    private final GridPane commandView = new GridPane();
    private final GridPane buttonBox = new GridPane();
    private TextArea input;
    private Button submitButton;
    private Button loadButton;
    private Button resetButton;
    private Button saveButton;

    private View myView;

    public CommandView(View myView_) {
        buttonBox.getStyleClass().add("buttonBox");
        myView = myView_;
        setUpCommandView();
        setUpInput();
        setUpButtonPaneRatio();
        setUpLoadButton();
        setUpRunButton();
        setUpSaveButton();
        setUpReSetButton();
        addElements();
    }

    private void setUpSaveButton() {
        saveButton = new Button("Save");
        saveButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        saveButton.setOnAction((final ActionEvent e) -> {
                    try {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setInitialFileName("Command.logo");
                        fileChooser.setTitle("Save Latest Command");
                        File file = fileChooser.showSaveDialog(myView.getMyStage());
                        if (file == null) return;
                        FileWriter writer = new FileWriter(file);
                        writer.write(myView.retrieveHistory());
                        writer.close();
                    } catch (Exception e1) {
                        myView.displayErrors(e1.toString());
                    }
                }
        );
    }

    private void setUpReSetButton() {
        resetButton = new Button("Reset");
        resetButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        resetButton.setOnAction((final ActionEvent e) -> {
                    myView.passCommand("cs");
                }
        );
    }

    private void setUpButtonPaneRatio() {
        var column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        var column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        var row1 = new RowConstraints();
        row1.setPercentHeight(50);
        var row2 = new RowConstraints();
        row2.setPercentHeight(50);
        buttonBox.getColumnConstraints().addAll(column1, column2);
        buttonBox.getRowConstraints().addAll(row1, row2);
    }

    private void setUpLoadButton() {
        loadButton = new Button("Load");
        loadButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        loadButton.setOnAction((final ActionEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(myView.getMyStage());
            if (file != null && file.getName().endsWith(".logo")) {
                try {
                    String res = "";
                    Scanner sc = new Scanner(file);
                    var firstLine = sc.nextLine();
                    if (!firstLine.startsWith("#"))
                        res = firstLine + " ";
                    while (sc.hasNextLine()) {
                        var thisLine = sc.nextLine();
                        if (!thisLine.startsWith("#"))
                        res = res + thisLine + " ";
                    }
                    System.out.println(res);
                    input.setText(res.substring(0, res.length() - 1));
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
    }

    private void setUpRunButton() {
        submitButton = new Button("Run");
        submitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        submitButton.setOnAction(action ->
                runCommand());
    }

    private void addElements() {
        buttonBox.add(loadButton, 1, 1);
        buttonBox.add(submitButton, 0, 0);
        buttonBox.add(saveButton, 1, 0);
        buttonBox.add(resetButton, 0, 1);
        commandView.add(input, 0, 0);
        commandView.add(buttonBox, 1, 0);
    }

    private void setUpCommandView() {
        var column1 = new ColumnConstraints();
        column1.setPercentWidth(70);
        var column2 = new ColumnConstraints();
        column2.setPercentWidth(30);
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

    // return values passed from backend to display in the promptText of the textArea
    public void returnValues(List<String> ret){
        input.setPromptText("Your last output was: " + ret);

    }
    @Override
    public Node getView() {
        return commandView;
    }
}
