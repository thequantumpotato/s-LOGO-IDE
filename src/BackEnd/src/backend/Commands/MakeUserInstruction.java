package backend.Commands;

import backend.Storage.Function;
import backend.Storage.Storage;
import backend.Turtle;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is very similar to the getuserInstruction class and is included to complete the
 *   recursion feature.
 * It is well-designed due to similar reasons in getuserInstruction.
 */
public class MakeUserInstruction extends RootNode {

    public MakeUserInstruction(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        String name = (String) l.get(0);
        System.out.println("name of function:"+name);
        Function f = new Function(name);
        List<Node> argsList = (List<Node>) l.get(1);
        for(Node n: argsList){
            String tmp = (String) n.run();
            f.addArgument(tmp);
        }
        List<Node> nodeList = (List<Node>) l.get(2);
        f.setBody(nodeList);
        myStorage.makeFunction(name, f);
        return 1;
    }
}
