package backend.Commands;

import backend.Turtle;

import java.util.List;

public class And extends MathNode{

    public And(Turtle t, List<Node> children) {
        super(t);
    }


    public Object run() {
        List<Integer> l = parseIntegers(parseDoubles(runChildren()));
        boolean first = l.get(0) == 1;
        boolean second = l.get(1) == 1;
        return first && second;
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }
}
