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
    private Model model;

    public Controller(Stage primaryStage) {
        myView = new View(primaryStage, this);
        // initialize Model here
        interpreter = new Interpreter();
        turtle = new Turtle();
        model = new Model();
    }

    public void runCommand(String input) throws Exception {
        model.parse(input);
    }

    private void initializeCommands() {

    }

    public void parse(String input) {

    }
}