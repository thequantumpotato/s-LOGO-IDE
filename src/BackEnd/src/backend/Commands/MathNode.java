package backend.Commands;

import backend.Turtle;

import java.util.ArrayList;
import java.util.List;

abstract public class MathNode extends RootNode {

    public MathNode(Turtle t) {
        super(t);
    }

    protected List<Double> parseDoubles(List<Object> l){
        List<Double> res = new ArrayList<>();
        for(Object o: l){
            res.add((Double) o);
        }
        return res;
    }

    protected List<Integer> parseIntegers(List<Double> doubles){
        List<Integer> res = new ArrayList<>();
        for(Double d: doubles){
            res.add(d.intValue());
        }
        return res;
    }
}
