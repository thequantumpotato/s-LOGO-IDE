package backend.Commands;

import backend.Storage.Storage;
import backend.Turtle;

import java.util.List;
import java.util.Random;

public class RandomNode extends RootNode {
    public RandomNode(Storage storage, Turtle turtle, List<Node> children) {
        super(storage, turtle, children);
    }

    @Override
    public Object run() {
        List<Integer> l = parseIntegers(parseDoubles(runChildren()));
        return new Random().nextInt(l.get(0));
    }
}
