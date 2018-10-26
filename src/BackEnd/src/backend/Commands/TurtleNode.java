package backend.Commands;

import backend.Turtle;

import java.util.ArrayList;
import java.util.List;

abstract public class TurtleNode extends RootNode {

    public TurtleNode(Turtle t, List<Node> children) {
        super(t, children);
    }

    @Override
    abstract public Object run();

    protected List<Double> parseDoubles(List<Object> l){
        List<Double> res = new ArrayList<>();
        for(Object o: l){
            res.add((Double) o);
        }
        return res;
    }
}
