package frontend;

import backend.Turtle;
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

import java.util.Map;

/*
Ben
1. Turtle Movement (note, the turtle starts in the center of the display which is considered (0, 0).)
   - enter commands to the turtle interactively by entering text commands
   - see the results of the turtle executing commands displayed visually.
2. UI settings
   - set a background color for the turtle's display area
   - set an image to use for the turtle
   - set a color to use for the pen
3. Choose the language in which SLogo commands are understood (here are a few translations)
   - take advantage of Resource Bundle
4. Access help about available commands. it could just be a "command reference page", like the assignment web page, or you could integrate it into the GUI like IntelliJ does, using these reference text files
   - Create a new page for help.

Vincent
1. See commands previously run in the environment
    - History Panel, display all commands in scrollPane
2. User-defined Panels
    see variables currently available in the environment
        - VariablePanel, need to know the data structure of the info passed back
    see user-defined commands currently available in the environment
        - FunctionPanel, need to know the data structure of the info passed back
3. See errors that may result from entered commands in a user friendly way (i.e., not just printed to the console)
   - error types need to be defined in the Parse class in the method of parsing

 */

/**
 * An aggregate of all the Nodes that will be displayed.
 *
 * @author Vincent Liu
 */

public class View {
    public static final String TURTLE_IMAGE = "turtle.png";
    public static final String TITLE = "SLogo";
    public static final String STYLESHEET = "style.css";
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 700;
    public final int FRAMES_PER_SECOND = 60;
    public final int DEFAULT_PEN_TIME = 1;
    public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Color DEFAULT_BG_COLOR = Color.BLACK;
    private DisplayView myDisplayView;
    private CommandView myCommandView;
    private VariableView myVariableView;
    private FunctionView myFunctionView;
    private HistoryView myHistoryView;
    private SettingView mySettingView;
    private GridPane root;
    private Scene myScene;
    private Controller myController;
    private Stage myStage;
    private Timeline animation;
    private int elapsedTime;

    public View(Stage primaryStage, Controller myController_, Turtle turtle) {
        myController = myController_;
        myStage = primaryStage;
        root = new GridPane();
        root.getStyleClass().add("root");
        elapsedTime = 0;

        var column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        var column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        var column3 = new ColumnConstraints();
        column3.setPercentWidth(20);
        var row1 = new RowConstraints();
        row1.setPercentHeight(40);
        var row2 = new RowConstraints();
        row2.setPercentHeight(40);
        var row3 = new RowConstraints();
        row3.setPercentHeight(10);
        var row4 = new RowConstraints();
        row4.setPercentHeight(10);

        root.getColumnConstraints().addAll(column1, column2, column3);
        root.getRowConstraints().addAll(row1, row2, row3, row4);

        myDisplayView = new DisplayView(this, new Image(this.getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)), turtle);
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


        myCommandView = new CommandView(this);

        myVariableView = new VariableView(this);

        myFunctionView = new FunctionView(this);

        myHistoryView = new HistoryView(this);

        mySettingView = new SettingView(this);

        root.add(mySettingView.getView(), 0, 0, 1, 2);
        root.add(myDisplayView.getView(), 1, 0, 1, 2);
        root.add(myVariableView.getView(), 2, 0, 1, 1);
        root.add(myFunctionView.getView(), 2, 1, 1, 1);
        root.add(myHistoryView.getView(), 0, 2, 3, 1);
        root.add(myCommandView.getView(), 0, 3, 3, 1);

        myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        myScene.getStylesheets().add(STYLESHEET);
        startView();
    }

    /**
     * Internal APIs
     **/
    public void changeBgColor(Color bgColor) {
         myDisplayView.changeBgColor(bgColor);
    }

    public void changePenColor(Color penColor) {
        myDisplayView.changePenColor(penColor);
    }

    public void changeTurtleImg(Image newTurtleImg) {
        myDisplayView.changeTurtleImg(newTurtleImg);
    }

    public void changeAnimationSpeed(Double time){
        myDisplayView.changeAnimationSpeed(time);
    }

    public void passCommand(String input) throws Exception {
        myController.runCommand(input);
        myHistoryView.updateHistory(input);
    }


    /**
     * External APIs
     **/
    public void displayVars(Map<String, String> variables) {

    }

    public void displayFunctions(Map<String, String> functions) {

    }

    public void displayErrors(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Erroneous Command: ");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public void registerDisplay(Turtle turtle) {
        turtle.addObserver(myDisplayView);
    }

    /**
     * Other methods
     **/
    public void step(double elapsedTime, Stage stage) { }

    // helper method to set up the animation, which is currently not used
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


    public Stage getMyStage() {
        return myStage;
    }
}
