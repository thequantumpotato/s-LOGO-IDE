package frontend;

import frontend.GUI.View;
import frontend.Undo.ChangePenColorOperation;
import frontend.Undo.Moment;
import frontend.Undo.Operation;
import javafx.scene.paint.Color;

import java.util.Stack;

/** Wrapper class for the {@code View} that enables undo and redo operations
 *  @author bpx */
public class GUIWrapper {

    private int undoRedoPointer = -1;
    private Stack<Operation> commandStack = new Stack<>();

    private View myView;

    /** Constructor takes in the {@code View} which the {@code GUIWrapper} will operate on */
    public GUIWrapper(View view) {
        myView = view;
    }

    /** Create a new {@code Moment} by capturing the current state of the {@code View}*/
    public Moment createMoment() {
        Moment moment = new Moment();
        moment.setState(this.myView);
        return moment;
    }

    /** Set the contained {@code View} to the state defined in a {@code Moment}
     *  @param moment The {@code Moment} containing the state to set the {@View} state to*/
    public void setToMoment(Moment moment) {
        this.myView = moment.getState();
    }

    /** Wrapper operation for changing the pen color
     *  @param color The new {@code Color} to change the pen to*/
    public void changePenColor(Color color) {
        deleteElementsAfterPointer(undoRedoPointer);
        Operation operation = new ChangePenColorOperation(myView, myView.getPenColor(), color);
        operation.execute();
        commandStack.push(operation);
        undoRedoPointer++;
    }

    /** Helper function for removing stack elements after the current position of the pointer
     *  @param undoRedoPointer The current value of the pointer*/
    private void deleteElementsAfterPointer(int undoRedoPointer) {
        if (commandStack.size() < 1) return;
        for (int i = commandStack.size() - 1; i > undoRedoPointer; i--) {
            commandStack.remove(i);
        }
    }

    /** Undo an operation by un-executing the most recent {@code Operation} in the stack
     *  based on the position of the pointer*/
    public void undo() {
        if (undoRedoPointer < 0) return;
        Operation operation = commandStack.get(undoRedoPointer);
        operation.unexecute();
        undoRedoPointer--;
    }

    /** Redo an operation by re-executing the most recent {@code Operation} in the stack
     *  based on the position of the pointer*/
    public void redo() {
        if (undoRedoPointer == commandStack.size() - 1)
            return;
        undoRedoPointer++;
        Operation operation = commandStack.get(undoRedoPointer);
        operation.execute();
    }
}
