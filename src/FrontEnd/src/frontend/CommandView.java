package frontend;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * An HBox that contains a TextArea to allow user input command text. When the submit button is clicked,
 * the command will be passed to the View, the Controller and the Parser.
 *
 * @author Vincent Liu
 */

public class CommandView implements SubView {
    private GridPane commandView;
    private TextArea input;
    private Button submitButton;
    private View myView;

    public CommandView(View myView_) {
        myView = myView_;
        input = new TextArea();
        input.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    myView.passCommand(input.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                input.setText("");
            }
        });
        submitButton = new Button("Run");
        submitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        commandView = new GridPane();
        commandView.getStyleClass().add("commandView");

        var column1 = new ColumnConstraints();
        column1.setPercentWidth(90);
        var column2 = new ColumnConstraints();
        column2.setPercentWidth(10);
        commandView.add(input, 0, 0);
        commandView.add(submitButton, 1, 0);
        commandView.getColumnConstraints().addAll(column1, column2);

        submitButton.setOnAction(action -> {
            try {
                myView.passCommand(input.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            input.setText("");
        });
    }

    @Override
    public Node getView() {
        return commandView;
    }
}
