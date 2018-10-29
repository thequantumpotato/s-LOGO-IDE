package frontend.Undo;

import frontend.GUI.View;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public class ChangePenColorOperation extends Operation {

    private Color myOldColor;
    private Color myNewColor;

    public ChangePenColorOperation(View view, Color oldColor, Color newColor){
        this.myMoment = new Moment();
        this.myView = view;
        myOldColor = oldColor;
        myNewColor = newColor;

    }

    @Override
    public void execute() {
        this.myMoment.setState(this.myView);
        myView.changePenColor(myNewColor);
    }

    @Override
    public void unexecute() {
        myView.changePenColor(myOldColor);
    }
}
