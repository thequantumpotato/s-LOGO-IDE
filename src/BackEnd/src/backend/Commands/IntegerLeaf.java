package backend.Commands;

import java.util.List;

public class IntegerLeaf extends LeafNode {
    public IntegerLeaf(String value) {
        super(value);
    }

    @Override
    public Object run() {
        return Double.parseDouble(value);
    }

    @Override
    public int getNumChildren() {
        return 0;
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }
}
