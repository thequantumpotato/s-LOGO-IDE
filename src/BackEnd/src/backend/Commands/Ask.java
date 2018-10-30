package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose San Martin (js665)
 */
public class Ask extends RootNode {
    public Ask(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> children = runChildren();
        List<Node> nums = (List<Node>) children.get(0);
        List<Object> dList = new ArrayList<>();
        for (Node c : nums) {
            dList.add(c.run());
        }
        for (Object id : dList) {
            myTurtle.setActive((Double) id);
        }
        List<Node> cList = (List<Node>) children.get(1);
        Object lastcom = null;
        for (Node c : cList) {
            lastcom = c.run();
        }
        for (Object id : dList) {
            myTurtle.setInactive((Double) id);
        }

        return lastcom;
    }
}
