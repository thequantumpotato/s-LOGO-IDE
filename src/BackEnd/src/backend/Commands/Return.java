package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class Return extends RootNode {

    public Return(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        System.out.println("return children" + myChildren);
        List<Object> l = runChildren();
        return l.get(0);
    }
}
