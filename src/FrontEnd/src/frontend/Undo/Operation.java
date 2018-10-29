package frontend.Undo;

import frontend.GUI.View;

public abstract class Operation {

    View myView;

    Moment myMoment;

    public abstract void execute();

    public abstract void unexecute();
}
