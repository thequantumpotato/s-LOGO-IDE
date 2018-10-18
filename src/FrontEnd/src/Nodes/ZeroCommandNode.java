package Nodes;

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
    }
