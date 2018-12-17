package backend.Commands;

/**
 * node in the tree that do not have any children
 *   usually a number, double, or string as data type
 *
 * @author Harry Xie
 */
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
