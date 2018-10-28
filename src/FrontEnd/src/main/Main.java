package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static final String DEFAULT_LANGUAGE = "English";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Controller myController = new Controller(primaryStage, DEFAULT_LANGUAGE);
        //myController.runCommand("fd 50");
    }
}