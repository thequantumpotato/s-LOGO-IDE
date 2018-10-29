package frontend.Undo;

import frontend.GUI.View;

/**
 * Object for storing state of the GUI
 *
 * @author bpx
 * https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java
 */
public class Moment {

    View myView;

    public View getState() {
        return myView;
    }

    public void setState(View view) {
        this.myView = view;
    }
}
