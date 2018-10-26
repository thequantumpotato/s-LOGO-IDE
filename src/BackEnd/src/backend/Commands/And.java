package backend.Commands;

import java.util.List;

public class And extends MathNode{

    public And(List<Node> children) {
        super(children);
    }

    @Override
    public Object run() {
        List<Integer> l = parseIntegers(parseDoubles(runChildren()));
        boolean first = l.get(0) == 1;
        boolean second = l.get(1) == 1;
        return first && second;
    }
}
