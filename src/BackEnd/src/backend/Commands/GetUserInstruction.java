package backend.Commands;

import backend.Storage.Function;
import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;

public class GetUserInstruction extends RootNode {

    public GetUserInstruction(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Object> l = runChildren();
        String name = (String) l.get(0);
        List<Object> argsList = (List<Object>) l.get(1);
        Function f = myStorage.getFunction(name).newInstance();
        f.setArguments(argsList);
        myStorage.pushToStack(f);
        Object res = null;
        for(Node n: f.getNodeList()){
            if(n instanceof Return){
                res = n.run();
                break;
            }
        }
        myStorage.popFromStack();
        return res;
    }
}
