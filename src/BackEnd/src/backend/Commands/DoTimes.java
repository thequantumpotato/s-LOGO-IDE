package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.ArrayList;
import java.util.List;

public class DoTimes extends RootNode {

    public DoTimes(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        List<Object> nums = new ArrayList<>();
        for (Node c : (List<Node>) l.get(0)) {
            nums.add(c.run());
        }
        String varName = (String) nums.get(0);
        int limit = ((Double) nums.get(1)).intValue();

        List<Node> cList = (List<Node>) l.get(1);

        Object finalcommand = null;
        for (int i = 1; i <= limit; i++) {
            System.out.println("?");
            myStorage.makeVar(varName, i);// (Allow the command to use the current iteration number
            for (Node c : cList) {
                finalcommand = c.run();
            }
        }
        myStorage.removeVar(varName);
        return finalcommand;
    }
}
