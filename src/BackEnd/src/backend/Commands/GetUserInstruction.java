package backend.Commands;

import backend.Storage.Function;
import backend.Storage.Storage;
import backend.Turtle;

import java.util.ArrayList;
import java.util.List;

public class GetUserInstruction extends RootNode {

    public GetUserInstruction(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        System.out.println("getuserinstruction:" + myChildren.toString());
        String name = (String) l.get(0);
        System.out.println("l.get1:" + l.get(1));
        List<Node> argsList = (List<Node>) l.get(1);
        List<Object> argsVals = new ArrayList<>();
        for(Node n: argsList){
            argsVals.add(n.run());
        }
        Function f = myStorage.getFunction(name).newInstance();
        f.setArguments(argsVals);
        myStorage.pushToStack(f);
        Object res = null;
        System.out.println("f.getNodeList" + f.getNodeList());
        for(Node n: f.getNodeList()){
            res = n.run();
        }
        myStorage.popFromStack();
        return res;
    }
}
