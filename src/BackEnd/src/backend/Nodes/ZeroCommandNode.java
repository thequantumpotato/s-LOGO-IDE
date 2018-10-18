package backend.Nodes;

public class ZeroCommandNode extends CommandNode {

        private int numArguments;
        private String commandName;

        public ZeroCommandNode(String commandString){
            super(commandString);
            commandName = commandString;
            numArguments = 0;
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

