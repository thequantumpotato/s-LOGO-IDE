package main;

import frontend.GUI.TabView;
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
        new TabView(primaryStage, DEFAULT_LANGUAGE);
    }
}