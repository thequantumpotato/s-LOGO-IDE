package backend;//import backend.backend.ModelController;

import frontend.View;
import javafx.stage.Stage;

/**
 * backend.Controller mediates the communications between the View and the Model. <br>
 * backend.Controller is initialized when the frontend.Main.java starts running. It will contain an instance of View and Model.
 *
 * @author Vincent Liu
 */
public class Controller {
    private View myView;

    private Interpreter interpreter;
    private Turtle turtle;
    private ModelController modelController;

    public Controller(Stage primaryStage) {
        myView = new View(primaryStage, this);
        // initialize Model here
        interpreter = new Interpreter();
        turtle = new Turtle();
        modelController = new ModelController(myView);
    }

    public void runCommand(String input) throws Exception {
        modelController.parseCommand(input);
    }

    private void initializeCommands() {

    }

    public void parse(String input) {

    }
}