package frontend.GUI;

import backend.Turtle;
import frontend.ExternalAPI.ViewAPI;
import frontend.API.ViewInternalAPI;
import frontend.GUI.Display.DisplayView;
import frontend.GUI.SubViews.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Controller;

import java.util.Map;

/**
 * An aggregate of all the Nodes that will be displayed.
 *
 * @author Vincent Liu
 */
// TODO: 10/25/18 Add TabPane and (AnchorPane)
// TODO: 10/25/18 Make the windows expand and contract (setexpanded)
// TODO: 10/25/18 Load logo file and run them by line (How to run a file with multiple lines)
// TODO: 10/25/18 Create Listeners (update) and Speakers (List of Listeners, addListener, removeListener, updateAll)
public class View implements ViewInternalAPI, ViewAPI {
    public static final String TITLE = "SLogo";
    public static final String STYLESHEET = "style.css";
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 700;
    public final int FRAMES_PER_SECOND = 60;
    public final int DEFAULT_PEN_TIME = 1;
    public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private DisplayView myDisplayView;
    private CommandView myCommandView;
    private VariableView myVariableView;
    private FunctionView myFunctionView;
    private HistoryView myHistoryView;
    private SettingView mySettingView;
    private HelpView myHelpView;
    private GridPane root;
    private Scene myScene;
    private Controller myController;
    private Stage myStage;
    private Timeline animation;

    public View(Stage primaryStage, Controller myController_, Turtle turtle, String initLang) {
        myController = myController_;
        myStage = primaryStage;
        setUpRootGridPane();
        initAndAddElements(turtle, initLang);
        myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        myScene.getStylesheets().add(STYLESHEET);
        startView();
    }

    /**
     * Other methods
     **/
    private void initAndAddElements(Turtle turtle, String initLang) {
        myDisplayView = new DisplayView(this, turtle);

        myCommandView = new CommandView(this);

        myVariableView = new VariableView(this);

        myFunctionView = new FunctionView(this);

        myHistoryView = new HistoryView(this);

        myHelpView = new HelpView();

        mySettingView = new SettingView(this, initLang);

        root.add(mySettingView.getView(), 0, 0, 3, 1);
        root.add(myHelpView.getView(), 0, 1, 1, 1);
        root.add(myHistoryView.getView(), 0, 2, 1, 1);
        root.add(myDisplayView.getView(), 1, 1, 1, 2);
        root.add(myVariableView.getView(), 2, 1, 1, 1);
        root.add(myFunctionView.getView(), 2, 2, 1, 1);
        root.add(myCommandView.getView(), 0, 3, 3, 1);
    }

    private void setUpRootGridPane() {
        root = new GridPane();
        root.getStyleClass().add("root");
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

        root.getColumnConstraints().addAll(column1, column2, column3);
        root.getRowConstraints().addAll(row1, row2, row3, row4);
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
    public void changePenSize(double penSize){
        myDisplayView.changePenSize(penSize);
    }

    @Override
    public void changePenDown(boolean penDown){
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
        new Controller(myStage, new Turtle(), language, "languages/Syntax");
    }

    @Override
    public void updateHistory(String validInput) {
        myHistoryView.updateHistory(validInput);
    }

    @Override
    public void addVar(Map<String, String> variable) {
        myVariableView.updateVariable(variable);
    }

    @Override
    public void updateFunction(Map<String, String> function) {
        myFunctionView.updateFunction(function);
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
        turtle.addObserver(myDisplayView);
    }
}
/*
 * Below is a demo program demonstrating the functionality of the Pen. However, you must first define all the points
 * that the turtle will traverse, and create all the updates, and then play them.
 * */
//        myDisplayView.changeBgColor(Color.BLACK);
//        myDisplayView.changePenColor(Color.DARKSEAGREEN);
        /*myDisplayView.updateTurtle(new Coordinate(300,300,120),Duration.seconds(2));
        myDisplayView.changePenColor(Color.DARKBLUE);
        myDisplayView.changePenSize(5);
        myDisplayView.updateTurtle(new Coordinate(-100,120,-60),Duration.seconds(2));
        myDisplayView.setPenDown(false);
        myDisplayView.updateTurtle(new Coordinate(360,10,30),Duration.seconds(2));
        myDisplayView.setPenDown(true);
        myDisplayView.changePenColor(Color.INDIANRED);
        myDisplayView.changePenSize(10);
        myDisplayView.updateTurtle(new Coordinate(300,300,-170),Duration.seconds(2));
        myDisplayView.playAnims();*/
