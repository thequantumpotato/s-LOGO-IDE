package backend.Nodes;


/**
 * @Author Jose San Martin
 * A class that represents commands with one argument.
 * They are held in tree structures, and reflection is then used to execute these commands.
 *
 */
public class SingleCommandNode extends CommandNode{
    private int numArguments;
    private String commandName;

    public SingleCommandNode(String commandString){
        super(commandString);
        commandName = commandString;
        numArguments = 1;
        setNumArguments(numArguments);
    }

    public String getCommandName(){
        return commandName;
    }
}
