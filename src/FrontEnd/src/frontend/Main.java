package frontend;

import backend.Turtle;
import backend.ModelController;
import javafx.application.Application;
import javafx.stage.Stage;
import backend.ModelController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Turtle turtle = new Turtle();
        Controller myController = new Controller(primaryStage,turtle);
        ModelController mc = new ModelController(turtle);
        mc.parseCommand("fd 50 bk 30");
    }
}