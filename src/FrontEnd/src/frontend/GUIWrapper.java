package frontend;

import frontend.GUI.View;
import frontend.Undo.ChangePenColorOperation;
import frontend.Undo.Moment;
import frontend.Undo.Operation;
import javafx.scene.paint.Color;

import java.util.Stack;

public class GUIWrapper {

    private int undoRedoPointer = -1;
    private Stack<Operation> commandStack = new Stack<>();

    private View myView;

    public GUIWrapper(View view) {
        myView = view;
    }

    public Moment createMoment() {
        Moment moment = new Moment();
        moment.setState(this.myView);
        return moment;
    }

    public void setToMoment(Moment moment) {
        this.myView = moment.getState();
    }

    public void changePenColor(Color color) {
        deleteElementsAfterPointer(undoRedoPointer);
        Operation operation = new ChangePenColorOperation(myView, myView.getPenColor(), color);
        operation.execute();
        commandStack.push(operation);
        undoRedoPointer++;
    }

    private void deleteElementsAfterPointer(int undoRedoPointer) {
        if (commandStack.size() < 1) return;
        for (int i = commandStack.size() - 1; i > undoRedoPointer; i--) {
            commandStack.remove(i);
        }
    }

    public void undo() {
        if (undoRedoPointer < 0) return;
        Operation operation = commandStack.get(undoRedoPointer);
        operation.unexecute();
        undoRedoPointer--;
    }

    public void redo() {
        if (undoRedoPointer == commandStack.size() - 1)
            return;
        undoRedoPointer++;
        Operation operation = commandStack.get(undoRedoPointer);
        operation.execute();
    }
}
