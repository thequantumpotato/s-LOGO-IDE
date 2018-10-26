package backend.Nodes;

import backend.Commands.Node;

import java.util.ArrayList;
import java.util.List;

public class LoopNode implements Node {

    private int repititions;
    private String commandName;
    private List<Node> myChildren;

    public LoopNode(int rep, String command) {
        commandName = command;
        repititions = rep;
        myChildren = new ArrayList<>();
    }

    @Override
    public void addChild(Node Child) {
        myChildren.add(Child);
    }

    @Override
    public int getRequiredArguments() {
        return 0;
    }

    @Override
    public int getNumChildren() {
        return myChildren.size();
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public List<Node> getChildren() {
        return myChildren;
    }

    public int getReps() {
        return repititions;
    }
}
