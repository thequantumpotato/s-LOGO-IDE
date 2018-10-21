package backend.Nodes;

import java.util.List;

public class ArgumentNode implements BasicNode {
    private String argument;
    private int numArguments = 0;

    public ArgumentNode(String value){
        argument = value;
    }

    public void addChild(BasicNode node) {
        return;
    }

    public int getNumChildren() {
        return 0;
    }

    public int getRequiredArguments() {
        return numArguments;
    }

    public String getCommandName() {
        return argument;
    }

    public List<BasicNode> getChildren() {
        return null;
    }


}
