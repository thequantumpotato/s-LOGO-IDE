package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class GetVariable extends RootNode {

    public GetVariable(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }


    @Override
    public Object run() {
        System.out.println("get variable children: " + myChildren.get(0).run());
        List<Object> l = runChildren();
        System.out.println("myStorage.getVar: " + myStorage.getVar((String)l.get(0)));
        return myStorage.getVar((String) l.get(0));
    }
}
