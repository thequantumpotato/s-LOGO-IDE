package frontend.GUI;

import backend.Turtle;
import backend.TurtleGroup;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Controller;

/**
 * Tab class is created to make each tab separate in its functionality. By assigning each tab with a TurtleGroup and
 * a ModelController, the whenever the setting changes in the frontend (e.g. Language) or when a new tab is added,
 * the existing tabs won't be affected.
 *
 * @author Vincent Liu
 */
public class TabView {
    public static final String TITLE = "SLogo";
    public static final String STYLESHEET = "style.css";
    public final int FRAMES_PER_SECOND = 60;
    public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final int SCREEN_HEIGHT = 700;
    public static final int SCREEN_WIDTH = 1000;
    private final Stage myStage;
    private final String myLanguage;
    private final VBox root = new VBox();
    private final TabPane tabPane = new TabPane();
    private AnchorPane anchorPane;
    private Scene myScene;
    private Timeline animation;
    final Button addButton = new Button("+");

    public TabView(Stage primaryStage, String language) {
        myStage = primaryStage;
        myLanguage = language;
        initializeAnchorPane();
        createTab(new Controller(myStage, myLanguage), language);
        addTabToAnchorPane();
        display();
    }

    private void display() {
        myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        myScene.getStylesheets().add(STYLESHEET);
        startView();
    }

    private void createTab(Controller controller, String initLang) {
        addTab(controller, new TurtleGroup(), initLang);
    }

    public void addTab(Controller controller, Turtle turtle, String initLang) {
        GridPane newGridPane = controller.getMyView();
        newGridPane.setMaxHeight(SCREEN_HEIGHT - 35);
        addToTabPane(newGridPane);
    }

    private void addToTabPane(GridPane gridPane) {
        Tab tab = new Tab();
        tab.setText("Tab " + (tabPane.getTabs().size() + 1));
        tab.setContent(gridPane);
        tabPane.getTabs().add(tab);
    }

    private void initializeAnchorPane() {
        anchorPane = new AnchorPane();
        AnchorPane.setTopAnchor(tabPane, 5.0);
        AnchorPane.setLeftAnchor(tabPane, 5.0);
        AnchorPane.setRightAnchor(tabPane, 5.0);
        AnchorPane.setTopAnchor(addButton, 5.0);
        AnchorPane.setRightAnchor(addButton, 9.0);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                var newController = new Controller(myStage, myLanguage);
                addTab(newController, newController.getMyTurtle(), myLanguage);
            }
        });
        anchorPane.setPadding(Insets.EMPTY);
    }

    private void addTabToAnchorPane() {
        anchorPane.getChildren().addAll(tabPane, addButton);
        root.getChildren().add(anchorPane);
    }

    public void startView() {
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
}
