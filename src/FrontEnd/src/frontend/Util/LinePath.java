package frontend.Util;

import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/** Extension to {@code Path} class to allow for adding {@code Line} objects easily to the {@code Path}
 *  @author bpx*/
public class LinePath extends Path {
    public void addLine(Line line){
        this.getElements().add(new MoveTo(line.getStartX(),line.getStartY()));
        this.getElements().add(new LineTo(line.getEndX(),line.getEndY()));
    }
}
