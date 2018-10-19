package frontend;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;

/** Auxiliary class functioning to provide line-drawing functionality to the {@code DisplayView}
 * @author bpx
 * */
class Pen {
    public static final Duration DEFAULT_SPEED = Duration.seconds(1);
    private Group root;
    private Coordinate myPosition;
    private Duration myDrawSpeed;

    //constructor if no speed
    Pen(Coordinate origin){
        this(DEFAULT_SPEED,origin);
    }
    //full constructor
    Pen(Duration speed, Coordinate origin){

        myPosition = origin;

        myDrawSpeed = speed;
    }
    //copy constructor
    Pen(Pen pen){
        this(pen.myDrawSpeed,pen.myPosition);
    }

    //create pathdrawtransition to new point
    public LineDrawTransition drawPath(Coordinate newpos){
        Line line = new Line(newpos.getX(),newpos.getY(),newpos.getX(),newpos.getY());
        System.out.println("creating path draw transition!");
        LineDrawTransition linedraw = new LineDrawTransition(myDrawSpeed,line);
        linedraw.setFromX(myPosition.getX());
        linedraw.setFromY(myPosition.getY());
        linedraw.setToX(newpos.getX());
        linedraw.setToY(newpos.getY());
        myPosition = newpos;
        renderLine(line);
        return linedraw;
    }

    //move pen without drawing a line
    public void movePen(Coordinate newpos){
        myPosition = newpos;
    }

    //set draw speed
    public void setDrawSpeed(Duration duration){
        myDrawSpeed = duration;
    }

    //set render target
    public void setRoot(Group target){
        root = target;
    }
    public void renderLine(Line line){
        root.getChildren().add(line);
    }
}
