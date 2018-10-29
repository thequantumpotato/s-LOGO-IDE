package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;
import javafx.scene.paint.Color;

import java.util.List;

public class SetPenColor extends RootNode {
    public SetPenColor(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        Color c = Color.web((String) l.get(0));
        myTurtle.Changed();
        myTurtle.setPenColor(c);
        myTurtle.clear();
        return c;
    }
}
