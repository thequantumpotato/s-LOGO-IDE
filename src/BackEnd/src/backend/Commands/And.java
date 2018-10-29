package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class And extends RootNode {

    public And(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        return ((Boolean) l.get(0)) && ((Boolean) l.get(1));
    }
}
