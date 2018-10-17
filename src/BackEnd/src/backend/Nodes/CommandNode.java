package backend.Nodes;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jose San MArtin
 * A super class to represent all types of commands
 *
 */

public abstract class CommandNode implements BasicNode {
    private int numArguments;
    private String commandName;
    private List<BasicNode> myChildren;

    public CommandNode(String commandString){
        commandName = commandString;
        myChildren = new ArrayList<>();
    }

    public void setNumArguments(int arguments){
        numArguments = arguments;
    }

    public void addChild(BasicNode child) {
        myChildren.add(child);
    }

    public int getNumArguments(){
        return numArguments;
    }
    public String getCommandName(){
        return commandName;
    }


}
