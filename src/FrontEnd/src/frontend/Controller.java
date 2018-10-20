package frontend;

import backend.ModelController;
import backend.Turtle;
import javafx.stage.Stage;

/**
 * Controller mediates the communications between the View and the Model. <br>
 * Controller is initialized when the Main.java starts running. It will contain an instance of View and Model.
 *
 * @author Vincent Liu
 */
public class Controller {
    private View myView;
    private Turtle myTurtle;
    private ModelController modelController;


    public Controller(Stage primaryStage, Turtle myTurtle_) {
        myTurtle = myTurtle_;
        myView = new View(primaryStage, this, myTurtle);
        myView.registerDisplay(myTurtle);
        modelController = new ModelController(myTurtle);
    }

    public void runCommand(String input) throws Exception {
        modelController.parseCommand(input);
    }

    private void initializeCommands() {

    }

    public void parse(String input) {

    }
}
