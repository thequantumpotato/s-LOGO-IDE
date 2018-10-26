package backend.Nodes;

import backend.Commands.Node;

import java.util.List;

public abstract class ArgumentNode implements Node {
    private String argument;
    private int numArguments = 0;

    public ArgumentNode(String value) {
        argument = value;
    }

    public void addChild(Node node) {
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

    public List<Node> getChildren() {
        return null;
    }


}
