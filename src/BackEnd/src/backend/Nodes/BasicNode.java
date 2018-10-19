package backend.Nodes;

import java.util.List;

public interface BasicNode {
    public void addChild(BasicNode Child);
    public int getRequiredArguments();
    public int getNumChildren();
    public String getCommandName();
    public List<BasicNode> getChildren();

}
