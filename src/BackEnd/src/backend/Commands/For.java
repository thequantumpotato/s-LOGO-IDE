package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.ArrayList;
import java.util.List;

public class For extends RootNode{
    public For(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        List<Object> nums = new ArrayList<>();
        for(Node c: (List<Node>)l.get(0)){
            nums.add(c.run());
        }
        String varName = (String) nums.get(0);
        int start = ((Double)nums.get(1)).intValue();
        int end = ((Double)nums.get(2)).intValue();
        int increment = ((Double)nums.get(3)).intValue();

        List<Node> cList = (List<Node>) l.get(1);

        Object finalcommand = null;
        for(int i = start; i < end; i=i+increment){
            myStorage.makeVar(varName, i);// (Allow the command to use the current iteration number
            for(Node c: cList){
                finalcommand = c.run();
            }
        }

        return finalcommand;
    }

}
