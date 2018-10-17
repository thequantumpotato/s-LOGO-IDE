package frontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * An aggregate of all the Nodes that will be displayed.
 *
 * @author Vincent Liu
 */

public class View {
    public static final String TURTLE_IMAGE = "turtle.jpg";
    public static final String TITLE = "SLogo";
    public static final String STYLESHEET = "style.css";
    private DisplayView myDisplayView;
    private CommandView myCommandView;
    private VariableView myVariableView;
    private FunctionView myFunctionView;
    private HistoryView myHistoryView;

    private GridPane root;
    private Scene myScene;
    private Controller myController;
    private Stage myStage;
    private Timeline animation;

    private int elapsedTime;

    public final int SCREEN_WIDTH = 1000;
    public final int SCREEN_HEIGHT = 700;
    public final int FRAMES_PER_SECOND = 60;
    public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    public View(Stage primaryStage, Controller myController_){
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
        row3.setPercentHeight(20);

        root.getColumnConstraints().addAll(column1, column2, column3);
        root.getRowConstraints().addAll(row1, row2, row3);

        myDisplayView = new DisplayView(new Image(this.getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)), new Coordinate(0, 0, 90));

        myCommandView = new CommandView(this);

        myVariableView = new VariableView(this);

        myFunctionView = new FunctionView(this);

        myHistoryView = new HistoryView(this);

        root.add(myDisplayView.getView(), 0, 0, 2, 2);
        root.add(myVariableView.getView(), 2, 0, 1, 1);
        root.add(myFunctionView.getView(), 2, 1, 1, 1);
        root.add(myHistoryView.getView(), 0, 2,3,1);
        root.add(myCommandView.getView(), 0, 2,3,1);

        myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        myScene.getStylesheets().add(STYLESHEET);
        startView();
    }

    public void startView(){

        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY, myStage));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public void passCommand(String input) {
        myController.runCommand(input);
    }


    /**
     * TO DO : MAKE THE TURTLE MOVE!
     * @param elapsedTime
     * @param stage
     */
    public void step(double elapsedTime, Stage stage) {
        this.elapsedTime++;
        root.getChildren().remove(myDisplayView);

        myDisplayView = new DisplayView(new Image(this.getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)), new Coordinate(this.elapsedTime, 0, 90));
        root.add(myDisplayView.getView(), 0, 0, 2, 2);
    }
}
