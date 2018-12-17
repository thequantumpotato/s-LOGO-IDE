package backend;

import backend.Commands.*;
import backend.Commands.Number;
import backend.Storage.Storage;
import java.util.*;
import java.util.regex.Pattern;

/**
 * This class is a good design because it encapsulates the logic for creating the command tree hierarchy. By using the Factory
 * design pattern, this class simply takes in a List of parsed commands, churns out all of the logic required
 * to turn that list into a list of independent trees, and then returns that list of trees. Making the communication between
 * such a complicated class and the ModelController removes unneeded dependency, and makes extensions much easier.
 * I refactored this class heavily by essentially getting rid of some duplicate code I had, making each method meaningfull, and reducing
 * the length of some long methods.
 */

public class TreeFactory {

    /**
     * @author Jose San Martin (js665)
     * Factory for creating a list of trees from the input command
     */
    private static final String commandProps = "backend/resources/Command";
    private static final String commandError = "backend/resources/Errors";
    private static final String MANUAL_VARIABLE = "MakeVariable";
    public ResourceBundle myErrors;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private Turtle myTurtle;
    private Storage myStorage;
    private Reflector myReflector;

    public TreeFactory(Turtle turtle, Storage storage) {
        myTurtle = turtle;
        mySymbols = new ArrayList<>();
        addPatterns(commandProps);
        myErrors = ResourceBundle.getBundle(commandError);
        myStorage = storage;
        myReflector = new Reflector(myTurtle, myStorage);
    }

    /**
     * Returns a list of Nodes when given a List of string commands. Each Node is the root of a tree
     * of commands. with the children being the arguments
     */
    public List<Node> getRoots(List<String> commands) throws IllegalCommandException {
        List<Node> myRoots = new ArrayList<>();
        while (commands.size() != 0) {
            String command = commands.remove(0);
            Node myRoot;
            if (isLeftParenthesis(command)) { //check if its a parenthesis FIRST
                unlimitedParams(commands, myRoots);
                continue;
            } else {
                myRoot = createRoot(command);
            }
            myRoot = getAllChildren(command, commands, myRoot);
            myRoots.add(myRoot);
        }

        return myRoots;
    }

    private Node getAllChildren(String command, List<String> commands, Node myRoot) throws IllegalCommandException {
        //If we haven't reached the max number of arguments required
        while (myRoot.getNumChildren() != getArgNum(command)) {
            Node nextChild;
            if (isOpenBracket(commands.get(0))) {
                nextChild = generateList(commands);
                commands.remove(0); //Remove that ending bracket!
            } else if (isVariable(commands.get(0)) & command.equals(MANUAL_VARIABLE)) { //We are currently making/updating a var
                nextChild = newVariable(commands);
            } else {
                nextChild = createChild(commands); //if not list, it is a command
            }
            if (nextChild == null) {
                throw new IllegalCommandException();
            }
            myRoot.addChild(nextChild);
        }
        return myRoot;
    }

    private Node createChild(List<String> commands) throws IllegalCommandException {
        if (commands.size() == 0) {
            return null;
        }
        Node newChild;
        if (isOpenBracket(commands.get(0))) {
            newChild = generateList(commands);
            commands.remove(0); //Remove that ending bracket!
        }
        else if (!isNotCommand(commands.get(0)) & getArgNum(commands.get(0))!= 99) {
            String nextChild = commands.remove(0);
            Node temp  = createRoot(nextChild);
            newChild = getAllChildren(nextChild, commands, temp);
        } else {
            String nextChild = commands.remove(0);
            newChild = getLeafNode(nextChild);
        }
        return newChild;

    }

    private Node createRoot(String command) throws IllegalCommandException {
        Node newNode;
        if (!isNotCommand(command)) {
            newNode = myReflector.reflect(command);

        } else {
            newNode = getLeafNode(command);
        }
        return newNode;
    }

    /**
     * Creates a list with all of the commands inside of the list
     * Returns the root of a ListNode, which is an argument to another command
     */
    private Node generateList(List<String> Commands) throws IllegalCommandException {
        String newList = Commands.remove(0); // Remove the bracket
        Node commandList = new ListNode(myStorage, myTurtle, new ArrayList<>());
        while (!isCloseBracket(Commands.get(0))) {
            //Create the new command and add all of it's children
            String nextCommand = Commands.remove(0);
            Node newChild = createRoot(nextCommand);
            if (newChild instanceof RootNode && !(newChild instanceof GetVariable)) {
                newChild = getAllChildren(nextCommand, Commands, newChild);
            }
            //Add this command to our ListNode
            commandList.addChild(newChild);
        }

        return (commandList);
    }

    /**
     * Returns the correct type of LeafNode that should be instantiated
     * That is either a Text that corresponds to a new number, a variable that we are grabbing,
     * or a
     */
    public Node getLeafNode(String nextChild) {
        if (isVariable(nextChild) & !myStorage.hasVar(nextChild.substring(1))) {
            myStorage.addVarName(nextChild.substring(1));
            return new Text(nextChild.substring(1));
        } else if (myStorage.hasVar(nextChild.substring(1))) {
            var temp = new GetVariable(myStorage, myTurtle, new ArrayList<>());
            temp.addChild(new Text(nextChild.substring(1)));
            return temp;
        } else if (isNumeric(nextChild)) {
            return new Number(nextChild);
        } else {
            return new Text(nextChild);
        }
    }

    public void unlimitedParams(List<String> commands, List<Node> myRoots) throws IllegalCommandException {
        String rootCommand = commands.remove(0);
        while (!isRightParenthesis(commands.get(0))) {
            Node tempCommand = createRoot(rootCommand); //Create the top command every as long as there are new parameters
            Node mainCommand = getAllChildren(rootCommand, commands, tempCommand);
            myRoots.add(mainCommand);
        }
        commands.remove(0); //remove the last parenthesis

    }

    private Node newVariable(List<String> commands) {
        String child = commands.remove(0);
        myStorage.addVarName(child.substring(1));
        return new Text(child.substring(1));
    }


    /**
     * Basic helper methods to check whether a string is numeric, a variable, or not a command at all
     */
    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    private boolean isVariable(String s) {
        return s.matches(":[a-zA-Z_]+");
    }

    private boolean isNotCommand(String s) {
        return s.matches(
                "[-+]?\\d*\\.?\\d+|\\\"[a-zA-Z]+|:[a-zA-Z_]+|^@.*");
    }

    /**
     * Basic helper methods to check whether a string is a bracket or parenthesis
     */
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

    /**
     * Returns whether a command requires one, two three, or more arguments
     */
    private int getArgNum(String text) {
        for (var e : mySymbols) {
            if (e.getValue().matcher(text).matches()) {
                return Integer.parseInt(e.getKey());
            }
        }
        return 0;
    }
    private void addPatterns(String syntax) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }
}
