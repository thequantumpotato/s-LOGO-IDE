package backend.Nodes;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Jose San Martin
 * A node class that extends our BasicNode interface. This class handles Lists, by having it's children be a list of
 * commands nodes, where the number of children is not specified.
 */
public class ListNode implements BasicNode {
    private String commandName;
    private List<BasicNode> myChildren;

    public ListNode(String commandString) {
        commandName = commandString;
        myChildren = new ArrayList<>();
    }

    public void addChild(BasicNode Child) {
        myChildren.add(Child);
    }

    //Do something about this, as a List has as many arguments as the user puts in
    public int getRequiredArguments() {
        return myChildren.size();
    }

    public int getNumChildren() {
        return myChildren.size();
    }

    public String getCommandName() {
        return commandName;
    }

    public List<BasicNode> getChildren() {
        return myChildren;
    }
}
