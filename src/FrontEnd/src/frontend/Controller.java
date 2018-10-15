package frontend;

import javafx.stage.Stage;

public class Controller {
    private View myView;


    public Controller(Stage primaryStage){
        myView = new View(primaryStage, this);

    }
}