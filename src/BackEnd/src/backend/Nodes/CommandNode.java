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

    public List<BasicNode> getChildren(){
        return myChildren;
    }

    /**
     * Return the number of required arguments
     */
    public int getRequiredArguments(){
        return numArguments;
    }

    /**
     * Returns the current number of children
     */
    public int getNumChildren(){
        return myChildren.size();
    }
    public String getCommandName(){
        return commandName;
    }


}
