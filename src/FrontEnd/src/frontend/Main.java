package frontend;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Controller myController = new Controller(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}