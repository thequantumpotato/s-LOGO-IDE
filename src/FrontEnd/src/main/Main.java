package main;

import frontend.GUI.TabView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main is the main file to run the program.
 * It sets up the program with the default language English.
 * It contains a TabView which can add multiple Tabs.
 * @author Vincent Liu
 */
public class Main extends Application {
    public static final String DEFAULT_LANGUAGE = "English";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(new Group()));
        new TabView(primaryStage, DEFAULT_LANGUAGE);
    }
}