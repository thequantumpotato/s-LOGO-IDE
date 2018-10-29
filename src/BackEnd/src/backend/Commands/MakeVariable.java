package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class MakeVariable extends RootNode {
    public MakeVariable(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        myStorage.makeVar((String) l.get(0), l.get(1));
        return l.get(1);
    }
}
