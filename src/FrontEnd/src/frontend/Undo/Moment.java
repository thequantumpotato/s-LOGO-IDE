package frontend.Undo;

import frontend.GUI.View;

/**
 * Object for storing state of the GUI
 * Based on the Memento design pattern
 * @author bpx
 */
public class Moment {

    View myView;

    /** Returns the {@code View} captured in the {@code Moment}*/
    public View getState() {
        return myView;
    }

    /** Sets the {@code Moment} to a new state
     *  @param view The {@code View} to store in the {@code Moment}*/
    public void setState(View view) {
        this.myView = view;
    }
}
