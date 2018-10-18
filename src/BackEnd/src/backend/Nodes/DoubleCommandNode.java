package backend.Nodes;


/**
 * @Author Jose San Martin
 * A class that represents commands with two argument.
 * They are held in tree structures, and reflection is then used to execute these commands.
 *
 */
public class DoubleCommandNode extends CommandNode{
    private int numArguments;
    private String commandName;

    public DoubleCommandNode(String commandString){
        super(commandString);
        commandName = commandString;
        numArguments = 2;
        setNumArguments(numArguments);
    }


    public String getCommandName(){
        return commandName;
    }

    @Override
    public boolean isCommand() {
        return true;
    }
}
