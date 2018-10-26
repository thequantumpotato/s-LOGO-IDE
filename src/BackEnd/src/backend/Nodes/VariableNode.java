package backend.Nodes;

public class VariableNode extends CommandNode {

    private String commandName;
    private int numArguments;

    public VariableNode(String commandString) {
        super(commandString);
        commandName = commandString;
        numArguments = 0;
        setNumArguments(numArguments);
    }


    public String getCommandName() {
        return commandName;
    }

    @Override
    public Object run() {
        return null;
    }
}
