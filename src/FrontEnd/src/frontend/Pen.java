package frontend;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * Auxiliary class functioning to provide line-drawing functionality to the {@code DisplayView}
 *
 * @author bpx
 */
class Pen {
    public static final int DEFAULT_SIZE = 1;
    public static final Duration DEFAULT_SPEED = Duration.seconds(1);
    public static final Color DEFAULT_COLOR = Color.WHITE;
    private Paint myColor;
    private double mySize;
    private Group root;
    private Coordinate myPosition;
    private Duration myDrawSpeed;

    //constructor if no speed
    Pen(Coordinate origin) {
        this(DEFAULT_SPEED, origin);
    }

    //full constructor
    Pen(Duration speed, Coordinate origin) {

        mySize = DEFAULT_SIZE;

        myColor = DEFAULT_COLOR;

        myPosition = origin;

        myDrawSpeed = speed;
    }

    //copy constructor
    Pen(Pen pen) {
        this(pen.myDrawSpeed, pen.myPosition);
    }

    //create pathdrawtransition to new point
    public ParallelTransition drawPath(Coordinate newpos) {
        ParallelTransition pl = new ParallelTransition();
        Line line = new Line(newpos.getX(), newpos.getY(), newpos.getX(), newpos.getY());
        line.setOpacity(0);
        line.setStroke(myColor);
        line.setStrokeWidth(mySize);
        LineDrawTransition linedraw = new LineDrawTransition(myDrawSpeed, line);
        linedraw.setFromX(myPosition.getX());
        linedraw.setFromY(myPosition.getY());
        linedraw.setToX(newpos.getX());
        linedraw.setToY(newpos.getY());
        FadeTransition fd = new FadeTransition(Duration.millis(1), line);
        fd.setToValue(100);
        myPosition = newpos;
        pl.getChildren().add(linedraw);
        pl.getChildren().add(fd);
        renderLine(line);
        return pl;
    }

    //move pen without drawing a line
    public void movePen(Coordinate newpos) {
        myPosition = newpos;
    }

    //set line stroke width
    public void setPenSize(double newsize) {
        if (newsize > 0) {
            mySize = newsize;
        }
    }

    //set line color
    public void setPenColor(Paint newcolor) {
        myColor = newcolor;
    }

    //set draw speed
    public void setDrawSpeed(Duration duration) {
        myDrawSpeed = duration;
    }

    //set render target
    public void setRoot(Group target) {
        root = target;
    }

    public void renderLine(Line line) {
        root.getChildren().add(line);
    }
}
