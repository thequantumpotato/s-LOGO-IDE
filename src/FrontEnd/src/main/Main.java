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
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        new Controller(primaryStage, new Turtle(), DEFAULT_LANGUAGE, SYNTAX);
        //ModelController mc = new ModelController(turtle);
        //mc.parseCommand("repeat 50 [ fd 40 rt 25 ]");
    }
}

//
//import javafx.application.Application;
//        import javafx.event.ActionEvent;
//        import javafx.event.EventHandler;
//        import javafx.geometry.Pos;
//        import javafx.geometry.Side;
//        import javafx.scene.Group;
//        import javafx.scene.Scene;
//        import javafx.scene.control.Label;
//        import javafx.scene.control.Menu;
//        import javafx.scene.control.MenuBar;
//        import javafx.scene.control.MenuItem;
//        import javafx.scene.control.Tab;
//        import javafx.scene.control.TabPane;
//        import javafx.scene.layout.BorderPane;
//        import javafx.scene.layout.HBox;
//        import javafx.scene.paint.Color;
//        import javafx.stage.Stage;
//
//public class Main extends Application {
//
//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Tabs");
//        Group root = new Group();
//        Scene scene = new Scene(root, 400, 250, Color.WHITE);
//
//        TabPane tabPane = new TabPane();
//
//        BorderPane borderPane = new BorderPane();
//        for (int i = 0; i < 5; i++) {
//            Tab tab = new Tab();
//            tab.setText("Tab" + i);
//            HBox hbox = new HBox();
//            hbox.getChildren().add(new Label("Tab" + i));
//            hbox.setAlignment(Pos.CENTER);
//            tab.setContent(hbox);
//            tabPane.getTabs().add(tab);
//        }
//        // bind to take available space
//        borderPane.prefHeightProperty().bind(scene.heightProperty());
//        borderPane.prefWidthProperty().bind(scene.widthProperty());
//
//        borderPane.setCenter(tabPane);
//        root.getChildren().add(borderPane);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//}