package frontend;

import javafx.stage.Stage;

/**
 * Controller mediates the communications between the View and the Model. <br>
 * Controller is initialized when the Main.java starts running. It will contain an instance of View and Model.
 *
 * @author Vincent Liu
 */
public class Controller {
    private View myView;

    public Controller(Stage primaryStage) {
        myView = new View(primaryStage, this);
        // initialize Model here
    }

    public void runCommand(String input) {
        System.out.println(input);
    }
}