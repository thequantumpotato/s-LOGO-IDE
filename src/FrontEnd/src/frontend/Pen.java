package frontend;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

class Pen {
    public static final Duration DEFAULT_SPEED = Duration.seconds(1);
    private Path myPath;
    private Coordinate myPosition;
    private Duration myDrawSpeed;

    //constructor if no speed
    Pen(Coordinate origin){
        this(DEFAULT_SPEED,origin);
    }
    //full constructor
    Pen(Duration speed, Coordinate origin){
        myPath = new Path();
        myPosition = origin;
        myPath.getElements().add(new MoveTo(myPosition.getX(),myPosition.getY()));
        myDrawSpeed = speed;
    }
    //copy constructor
    Pen(Pen pen){
        this(pen.myDrawSpeed,pen.myPosition);
    }

    //create pathdrawtransition to new point
    public PathDrawTransition drawPath(Coordinate newpos){
        PathDrawTransition pathdraw = new PathDrawTransition(myDrawSpeed,myPath);
        pathdraw.setFromX(myPosition.getX());
        pathdraw.setFromY(myPosition.getY());
        pathdraw.setToX(newpos.getX());
        pathdraw.setToY(newpos.getY());
        return pathdraw;
    }

    //move pen without drawing a line
    public void movePen(Coordinate newpos){
        myPath.getElements().add(new MoveTo(newpos.getX(),newpos.getY()));
        myPosition = newpos;
    }

    //set draw speed
    public void setDrawSpeed(Duration duration){
        myDrawSpeed = duration;
    }

    public void renderPath(Group root){
        root.getChildren().add(myPath);
    }
}
