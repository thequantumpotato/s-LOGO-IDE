package frontend.Undo;

import frontend.GUI.View;
import javafx.scene.paint.Color;

/** Concrete implementation of an {@code Operation} that allows for execution and un-execution
 *  of a pen-color changing input from the user
 *  Based on the Command design pattern
 *  @author bpx */
public class ChangePenColorOperation extends Operation {

    private Color myOldColor;
    private Color myNewColor;

    /** Constructor
     *  @param view The {@code View} which the {@code ChangePenColorOperation} will act upon
     *  @param oldColor The previous {@code Color} of the pen
     *  @param newColor The new {@code Color} to change the pen to*/
    public ChangePenColorOperation(View view, Color oldColor, Color newColor) {
        this.myMoment = new Moment();
        this.myView = view;
        myOldColor = oldColor;
        myNewColor = newColor;
    }

    /** Execute the {@code ChangePenColorOperation}*/
    @Override
    public void execute() {
        this.myMoment.setState(this.myView);
        myView.changePenColor(myNewColor);
    }

    /** Un-execute the {@code ChangePenColorOperation}*/
    @Override
    public void unexecute() {
        myView.changePenColor(myOldColor);
    }
}
