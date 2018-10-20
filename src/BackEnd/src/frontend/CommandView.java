package frontend;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

/**
 * An HBox that contains a TextArea to allow user input command text. When the submit button is clicked,
 * the command will be passed to the View, the Controller and the Parser.
 *
 * @author Vincent Liu
 */

public class CommandView implements SubView {
    private HBox commandView;
    private TextArea input;
    private Button submitButton;
    private View myView;

    public CommandView(View myView_) {
        myView = myView_;
        input = new TextArea();
        submitButton = new Button("Submit");
        commandView = new HBox(input, submitButton);
        commandView.getStyleClass().add("commandView");

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
