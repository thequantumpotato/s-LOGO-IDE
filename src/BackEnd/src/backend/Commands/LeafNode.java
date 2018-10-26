package backend.Commands;

abstract public class LeafNode implements Node {

    protected String value;

    public LeafNode(String value){
        this.value = value;
    }

    @Override
    public void addChild(Node child) {
        return;
    }

    @Override
    abstract public Object run();
}
