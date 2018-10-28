package frontend.GUI;

import backend.Turtle;
import backend.TurtleGroup;
import frontend.API.ViewInternalAPI;
import frontend.ExternalAPI.ViewAPI;
import frontend.GUI.Display.DisplayView;
import frontend.GUI.SubViews.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Controller;

import java.util.List;
import java.util.Map;

/**
 * An aggregate of all the Nodes that will be displayed.
 *
 * @author Vincent Liu
 */
// TODO: 10/25/18 Make the windows expand and contract --- Accordion and TitlePane
// TODO: 10/25/18 Create controller and turtle for the different panes
public class View implements ViewInternalAPI, ViewAPI {
    public static final String TITLE = "SLogo";
    public static final String STYLESHEET = "style.css";
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 700;
    public final int FRAMES_PER_SECOND = 60;
    public final int DEFAULT_PEN_TIME = 1;
    public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private final String myLang;
    private final Turtle myTurtle;
    private DisplayView myDisplayView;
    private CommandView myCommandView;
    private VariableView myVariableView;
    private FunctionView myFunctionView;
    private HistoryView myHistoryView;
    private SettingView mySettingView;
    private HelpView myHelpView;
    private Scene myScene;
    private Controller myController;
    private Stage myStage;
    private Timeline animation;
    private TabPane tabPane;
    private VBox root;
    private AnchorPane anchorPane;
    final Button addButton = new Button("+");

    public View(Stage primaryStage, Controller myController_, Turtle turtle, String initLang) {
        root = new VBox();
        root.getStyleClass().add("root");
        myController = myController_;
        myStage = primaryStage;
        myTurtle = turtle;
        myLang = initLang;
        createTab(initLang);
        addAnchor();
        myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        myScene.getStylesheets().add(STYLESHEET);
        startView();
    }

    /**
     * Other methods
     **/

    private void createTab(String initLang) {
        tabPane = new TabPane();
        addTab(myTurtle, initLang);
    }

    private void addTab(Turtle turtle, String initLang) {
        GridPane newGridPane = createGridPane(turtle, initLang);
        newGridPane.setMaxHeight(SCREEN_HEIGHT - 35);
        addToTabPane(newGridPane);
    }

    private GridPane createGridPane(Turtle turtle, String initLang) {
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("gridPane");
        setUpGridPane(gridPane);
        initAndAddElements(gridPane, turtle, initLang);
        return gridPane;
    }

    private void setUpGridPane(GridPane gridPane) {
        var column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        var column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        var column3 = new ColumnConstraints();
        column3.setPercentWidth(20);
        var row1 = new RowConstraints();
        row1.setPercentHeight(10);
        var row2 = new RowConstraints();
        row2.setPercentHeight(40);
        var row3 = new RowConstraints();
        row3.setPercentHeight(40);
        var row4 = new RowConstraints();
        row4.setPercentHeight(10);
        gridPane.getColumnConstraints().addAll(column1, column2, column3);
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4);
    }

    private void addAnchor() {
        anchorPane = new AnchorPane();
        AnchorPane.setTopAnchor(tabPane, 5.0);
        AnchorPane.setLeftAnchor(tabPane, 5.0);
        AnchorPane.setRightAnchor(tabPane, 5.0);
        AnchorPane.setTopAnchor(addButton, 5.0);
        AnchorPane.setRightAnchor(addButton, 9.0);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addTab(new TurtleGroup(), myLang);
            }
        });
        anchorPane.getChildren().addAll(tabPane, addButton);
        anchorPane.setPadding(Insets.EMPTY);
        root.getChildren().add(anchorPane);
    }

    private void addToTabPane(GridPane gridPane) {
        Tab tab = new Tab();
        tab.setText("Tab " + (tabPane.getTabs().size() + 1));
        tab.setContent(gridPane);
        tabPane.getTabs().add(tab);
    }

    private void initAndAddElements(GridPane gridPane, Turtle turtle, String initLang) {
        myDisplayView = new DisplayView(this, turtle);
        myCommandView = new CommandView(this);
        myVariableView = new VariableView(this);
        myFunctionView = new FunctionView(this);
        myHistoryView = new HistoryView(this);
        myHelpView = new HelpView();
        mySettingView = new SettingView(this, initLang);
        gridPane.add(mySettingView.getView(), 0, 0, 3, 1);
        gridPane.add(myHelpView.getView(), 0, 1, 1, 1);
        gridPane.add(myHistoryView.getView(), 0, 2, 1, 1);
        gridPane.add(myDisplayView.getView(), 1, 1, 1, 2);
        gridPane.add(myVariableView.getView(), 2, 1, 1, 1);
        gridPane.add(myFunctionView.getView(), 2, 2, 1, 1);
        gridPane.add(myCommandView.getView(), 0, 3, 3, 1);
    }

    // Currently not used. An helper method to set up the animation
    public void startView() {
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY, myStage));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    // Currently not used. Update change in the animation.
    public void step(double elapsedTime, Stage stage) {
    }

    public DisplayView getMyDisplayView() {
        return myDisplayView;
    }

    public Stage getMyStage() {
        return myStage;
    }

    /**
     * Internal ExternalAPI
     **/

    @Override
    public void changeBgColor(Color bgColor) {
        myDisplayView.changeBgColor(bgColor);
    }

    @Override
    public void changePenColor(Color penColor) {
        myDisplayView.changePenColor(penColor);
    }

    @Override
    public void changePenSize(double penSize) {
        myDisplayView.changePenSize(penSize);
    }

    @Override
    public void changePenDown(boolean penDown) {
        myDisplayView.setPenDown(penDown);
    }

    @Override
    public void changeTurtleImg(Image image) {
        myDisplayView.changeTurtleImg(image);
    }

    @Override
    public void changeAnimationSpeed(Double time) {
        myDisplayView.changeAnimationSpeed(time);
    }

    @Override
    public void changeLanguage(String language) {
        new Controller(myStage, language);
    }

    @Override
    public void updateVar(Map<String, String> variable) {
        myController.updateVar(variable);
    }

    /**
     * External APIs
     **/

    @Override
    public void passCommand(String input) {
        myController.runCommand(input);
    }

    @Override
    public void displayErrors(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Erroneous Command: ");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @Override
    public void registerDisplay(Turtle turtle) {
        turtle.addAnObserver(myDisplayView);
    }

    @Override
    public void displayVar(Map<String, String> variable) {
        myVariableView.updateVariable(variable);
    }

    @Override
    public void displayFunc(List<String> function) {
        myFunctionView.updateFunction(function);
    }

    @Override
    public void updateHistory(String validInput) {
        myHistoryView.updateHistory(validInput);
    }

}
