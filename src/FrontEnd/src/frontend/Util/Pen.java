package frontend.Util;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static frontend.GUI.Display.TurtleManager.DEFAULT_DURATION;

/**
 * Auxiliary class functioning to provide line-drawing functionality
 * @author bpx
 */
public class Pen {

    public static final int DEFAULT_SIZE = 1;
    public static final Color DEFAULT_COLOR = Color.WHITE;

    private Color myColor;
    private double mySize;
    private Group root;
    private List<Line> myLines;
    private Coordinate myPosition;
    private Duration myDrawSpeed;

    public Color getMyColor() {
        return myColor;
    }

    public double getMySize() {
        return mySize;
    }

    /** Constructor for no {@code Duration}
     *  @param origin The starting point of the {@code Pen}*/
    public Pen(Coordinate origin) {
        this(Duration.seconds(DEFAULT_DURATION), origin);
    }

    /** Full constructor
     *  @param speed Duration in seconds to draw a line
     *  @param origin The starting point of the {@code Pen}*/
    public Pen(Duration speed, Coordinate origin) {
        myLines = new ArrayList<>();
        mySize = DEFAULT_SIZE;
        myColor = DEFAULT_COLOR;
        myPosition = origin;
        myDrawSpeed = speed;
    }

    /** Cloning constructor if a copy is needed
     *  @param pen The {@code Pen} object to clone */
    public Pen(Pen pen) {
        this(pen.myDrawSpeed, pen.myPosition);
    }

    /** Creates a line drawing animation from {@code Pen} current position to {@code Coordinate} new position
     *  @param newpos The {@code Coordinate} destination of the line drawing animation */
    public ParallelTransition drawLine(Coordinate newpos) {
        ParallelTransition pl = new ParallelTransition();
        Line line = new Line(newpos.getX(), newpos.getY(), newpos.getX(), newpos.getY());
        prepareLine(line);
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
        myLines.add(line);
        renderLine(line);
        return pl;
    }

    /** Creates a line drawing animation from a {@code LinePath}
     *  @param linePath The {@code LinePath} to use as reference when drawing the line*/
    public SequentialTransition drawLinePath(LinePath linePath){
        SequentialTransition st = new SequentialTransition();
        System.out.println(myDrawSpeed.toSeconds()+" "+linePath.getLines().size());
        setDrawSpeed((Duration.seconds(myDrawSpeed.toSeconds()/linePath.getLines().size())));
        for(Line l : linePath.getLines()){
            ParallelTransition pl = new ParallelTransition();
            movePen(new Coordinate(l.getStartX(),l.getStartY(),0));
            pl.getChildren().add(drawLine(new Coordinate(l.getEndX(),l.getEndY(),0)));
            st.getChildren().add(pl);
        }
        setDrawSpeed(Duration.seconds(myDrawSpeed.toSeconds()*linePath.getLines().size()));
        return st;
    }

    /** Helper method that sets up a fully drawn line for animation by formatting the line and hiding it
     *  @param line The {@code Line} to be formatted*/
    private void prepareLine(Line line){
        line.setOpacity(0);
        line.setStroke(myColor);
        line.setStrokeWidth(mySize);
    }

    /** Moves the pen without drawing a line
     *  @param newpos The {@code Coordinate} destination of the movement */
    public void movePen(Coordinate newpos) {
        myPosition = newpos;
    }

    /** Sets the stroke width of the pen
     *  @param newsize The new size in pixels of the {@code Pen} stroke width*/
    public void setPenSize(double newsize) {
        if (newsize > 0) {
            mySize = newsize;
        }
    }

    /** Set the stroke color of the {@code Pen}
     *  @param newcolor The new {@code Paint} value for the color of the {@code Pen}*/
    public void setPenColor(Color newcolor) {
        myColor = newcolor;
    }

    /** Set the animation duration for line drawing
     *  @param duration The new {@code Duration} of the line drawing animation */
    public void setDrawSpeed(Duration duration) {
        myDrawSpeed = duration;
    }

    /** Set the target to which this {@code Pen} will render to
     *  @param target The {@code Group} to render to */
    public void setRoot(Group target) {
        root = target;
    }

    /** Renders a line to the render target
     *  @param line The {@code Line} to render */
    public void renderLine(Line line) {
        root.getChildren().add(line);
    }

    /** Returns the current {@code Duration} of drawing speed */
    public Duration getMyDrawSpeed() {
        return myDrawSpeed;
    }

    /** Erase all lines that have been drawn */
    public void eraseAll() {
        for (Line l : myLines) {
            root.getChildren().remove(l);
        }
    }
}
