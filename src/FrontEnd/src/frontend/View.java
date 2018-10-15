package frontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class View {
    public final String BOUNCER_IMAGE = "ball.gif";
    public final String TITLE = "SLogo";
    private DisplayView myDisplayView;
    private Group root;
    private Scene myScene;
    private Controller myController;
    private Stage myStage;
    private Timeline animation;

    public final int SIZE = 700;
    public final int FRAMES_PER_SECOND = 60;
    public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    public View(Stage primaryStage, Controller myController_){
        myController = myController_;
        myStage = primaryStage;
        root = new Group();
        myDisplayView = new DisplayView(new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE)));
        myDisplayView.setTurtlePos(new Coordinate(100, 100, 100));

        root.getChildren().add(myDisplayView.getView());
        myScene = new Scene(root, SIZE, SIZE);
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

    public void step(double elapsedTime, Stage stage) {

    }
}
