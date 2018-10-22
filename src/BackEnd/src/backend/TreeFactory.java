package backend;

import backend.Nodes.*;

import java.util.*;
import java.util.regex.Pattern;

public class TreeFactory {

    /**
     * @author Jose San Martin
     * Factory for creating a list of trees from the input command
     */
    private static final String commandProps = "backend/resources/Command";
    private static final String commandError = "backend/resources/Errors";
    public ResourceBundle myErrors;
    private List<Map.Entry<String, Pattern>> mySymbols;

    public TreeFactory() {
        mySymbols = new ArrayList<>();
        addPatterns(commandProps);
        myErrors = ResourceBundle.getBundle(commandError);
    }


    /**
     * Returns a list of Nodes when given a List of string commands. Each Node is the root of a tree
     * of commands. with the children being the arguments
     */
    //Commands have children, while arguments don't (they are simply argumentNodes)
    public List<BasicNode> getRoots(List<String> commands) throws IllegalCommandException {
        List<BasicNode> myRoots = new ArrayList<>();
        while (commands.size() != 0) {
            String command = commands.remove(0);
            BasicNode myRoot;
            if (isLeftParenthesis(command)) { //check if its a parenthesis FIRST
                //make the NEXT item a command, not the current parenthesis
                String nextCommand = commands.remove(0);
                myRoot = createRoot(nextCommand);
            } else if (isRightParenthesis(command)) { //check if, basically, we shouldn't add anymore.
                //Continue to the next commands, as this one is OVER darling!
                continue;
            } else {
                myRoot = createRoot(command);
            }
            //If we haven't reached the max number of arguments required
            while (myRoot.getNumChildren() != myRoot.getRequiredArguments()) {
                //Check if the next item is an open bracket (because lists are ALWAYS children)
                BasicNode nextChild;
                if (isOpenBracket(commands.get(0))) {
                    nextChild = generateList(commands);
                    commands.remove(0); //Remove that ending parenthesis!
                } else {
                    nextChild = createChild(commands);
                }
                if (nextChild == null) {
                    throw new IllegalCommandException();
                }
                myRoot.addChild(nextChild);
            }
            myRoots.add(myRoot);
        }

        return myRoots;
    }

    //TODO: Implement brackets and parenthesis
    private BasicNode createChild(List<String> commands) throws IllegalCommandException {
        if (commands.size() == 0) {
            return null;
        }
        BasicNode newChild;
        String nextChild = commands.remove(0);
        if (isLeftParenthesis(nextChild)) { //check if its a parenthesis FIRST
            String nextCommand = commands.remove(0);
            newChild = createRoot(nextCommand);
            generateCommand(newChild, commands);
        } else if (!isNumeric(nextChild)) {
            newChild = createRoot(nextChild);
            //This child has its own arguments that we need to add. Use recursion!
            generateCommand(newChild, commands);
        } else {
            newChild = new ArgumentNode(nextChild);
        }

        return newChild;

    }

    private BasicNode createRoot(String command) throws IllegalCommandException {
        BasicNode newNode;
        if(isVariable(command)){
            newNode = new SingleCommandNode("GetVariable");
            newNode.addChild(new ArgumentNode(command.substring(1))); //Variables need to have a child to begin with
            System.out.println(command.substring(1));
        }
        else if (!isNumeric(command)) {
            String numArgs = getArgNum(command);
            //System.out.println(numArgs);
            if (numArgs == null) {
                throw new IllegalCommandException(myErrors.getString("commandError"));
            }
            //TODO Get rid of these ifelse statements
            if (numArgs.equals("Single")) {
                newNode = new SingleCommandNode(command);
            } else if (numArgs.equals("Double")) {
                newNode = new DoubleCommandNode(command);
            } else if (numArgs.equals("Triple")) {
                newNode = new TripleCommandNode(command);
            } else {
                newNode = new ZeroCommandNode(command);
            }


        } else {
            newNode = new ArgumentNode(command);
        }
        return newNode;
    }

    /**
     * Creates a list with all of the commands inside of the list
     * Returns the root of a ListNode, which is an argument to another command
     */
    private BasicNode generateList(List<String> Commands) throws IllegalCommandException {
        String newList = Commands.remove(0);
        BasicNode commandList = new ListNode(newList);
        while (!isCloseBracket(Commands.get(0))) {
            //Create the new command and add all of it's children
            String nextCommand = Commands.remove(0);
            BasicNode newChild = createRoot(nextCommand);
            generateCommand(newChild, Commands);
            //Add this command to our ListNode
            commandList.addChild(newChild);
        }

        return (commandList);
    }

    /**
     * A utility method to help us obtain all of the children of a command
     */
    public void generateCommand(BasicNode commandRoot, List<String> Commands) throws IllegalCommandException {
        while (commandRoot.getNumChildren() != commandRoot.getRequiredArguments()) {
            commandRoot.addChild(createChild(Commands));
        }
    }

    private boolean isVariable(String s){
        return s.matches(":[a-zA-Z_]+");
    }
    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+|\\\"[a-zA-Z]+");
    }

    private boolean isLeftParenthesis(String s) {
        return s.matches("GroupStart");
    }

    private boolean isRightParenthesis(String s) {
        return s.matches("GroupEnd");
    }

    private boolean isOpenBracket(String s) {
        return s.matches("ListStart");
    }

    private boolean isCloseBracket(String s) {
        return s.matches("ListEnd");
    }


    private void addPatterns(String syntax) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns whether a command requires one, two three, or more arguments
     */
    private String getArgNum(String text) {
        for (var e : mySymbols) {
            if (e.getValue().matcher(text).matches()) {
                return e.getKey();
            }
        }
        //This will never get thrown, because we would have thrown it in a previous matching method
        return null;
    }
}
