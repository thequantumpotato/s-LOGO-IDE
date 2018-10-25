package main;

import backend.Turtle;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static final String DEFAULT_LANGUAGE = "English";
    public static final String SYNTAX = "languages/Syntax";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        new Controller(primaryStage, new Turtle(), DEFAULT_LANGUAGE, SYNTAX);
    }
}