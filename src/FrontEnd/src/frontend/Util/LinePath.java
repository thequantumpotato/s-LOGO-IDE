package frontend.Util;

import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.ArrayList;

/** Extension to {@code Path} class to allow for adding {@code Line} objects easily to the {@code Path}
 *  @author bpx*/
public class LinePath extends Path {
    private ArrayList<Line> myLines;

    /** Default constructor */
    public LinePath(){
        super();
        myLines = new ArrayList<>();
    }

    /** Converts a {@code Line} object to a {@code Path} element and adds it to the {@code Path}*/
    public void addLine(Line line){
        myLines.add(line);
        this.getElements().add(new MoveTo(line.getStartX(),line.getStartY()));
        this.getElements().add(new LineTo(line.getEndX(),line.getEndY()));
    }

    /** Returns the length of the {@code LinePath} based on the combined lengths of all composing {@code Line} objects*/
    public double length(){
        double distance = 0;
        for(Line l : myLines){
            distance += Math.sqrt(Math.pow(l.getEndX()-l.getStartX(),2) + Math.pow(l.getEndY()-l.getStartY(),2));
        }
        return distance;
    }
}
