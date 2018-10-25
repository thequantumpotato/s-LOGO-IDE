package backend.Nodes;

import java.util.ArrayList;
import java.util.List;

public class LoopNode implements BasicNode {

    private int repititions;
    private String commandName;
    private List<BasicNode> myChildren;

    public LoopNode(int rep, String command) {
        commandName = command;
        repititions = rep;
        myChildren = new ArrayList<>();
    }

    @Override
    public void addChild(BasicNode Child) {
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
    public List<BasicNode> getChildren() {
        return myChildren;
    }

    public int getReps() {
        return repititions;
    }
}
