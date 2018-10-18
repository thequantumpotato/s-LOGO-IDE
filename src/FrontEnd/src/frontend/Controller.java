package frontend;

import Nodes.BasicNode;
import javafx.stage.Stage;

import java.util.List;

/**
 * Controller mediates the communications between the View and the Model. <br>
 * Controller is initialized when the Main.java starts running. It will contain an instance of View and Model.
 *
 * @author Vincent Liu
 */
public class Controller {
    private View myView;

    private Interpreter interpreter;
    private Turtle turtle;
    private List<BasicNode> myCommands;

    public Controller(Stage primaryStage) {
        myView = new View(primaryStage, this);
        // initialize Model here
        interpreter = new Interpreter();
        turtle = new Turtle();
    }

    public void runCommand(String input) throws Exception {
        myCommands = interpreter.parse(input);

    }

    private void initializeCommands() {

    }

    public void parse(String input) {

    }
}