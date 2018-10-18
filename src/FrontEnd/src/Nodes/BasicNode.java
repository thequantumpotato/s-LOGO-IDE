package Nodes;

import java.util.List;

public interface BasicNode {
    void addChild(BasicNode Child);
    int getRequiredArguments();
    int getNumChildren();
    String getCommandName();
    List<BasicNode> getChildren();
    boolean isCommand();

}
