package backend.Nodes;

public interface BasicNode {
    public void addChild(BasicNode Child);
    public int getRequiredArguments();
    public int getNumChildren();

}
