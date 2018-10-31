package frontend.Undo;

import frontend.GUI.View;

/** Abstract extendable class which allows for flexible creation of undo and redo operations for
 *  any user operation, based on the Command design pattern
 *  @author bpx*/
public abstract class Operation {

    View myView;

    Moment myMoment;

    /** Execute the {@code Operation}*/
    public abstract void execute();

    /** Un-execute the {@code Operation}*/
    public abstract void unexecute();
}
