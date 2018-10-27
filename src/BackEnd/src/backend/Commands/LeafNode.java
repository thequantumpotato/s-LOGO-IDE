package backend.Commands;

abstract public class LeafNode implements Node {

    @Override
    public void addChild(Node child) {
        return;
    }

    @Override
    public int getNumChildren() {
        return 0;
    }

    @Override
    abstract public Object run();
}
