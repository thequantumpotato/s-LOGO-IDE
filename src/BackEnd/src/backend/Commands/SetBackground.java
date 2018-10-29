package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;
import javafx.scene.paint.Color;

import java.util.List;

public class SetBackground extends RootNode {

    public SetBackground(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        System.out.println("#" + ((String) l.get(0)).substring(1));
        Color c = Color.web("#" + ((String) l.get(0)).substring(1));
        myTurtle.Changed();
        myTurtle.setBgColor(c);
        myTurtle.clear();
        return c;
    }
}
