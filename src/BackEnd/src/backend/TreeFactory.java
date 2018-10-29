package backend;

import backend.Commands.*;
import backend.Commands.Number;
import backend.Storage.Storage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;

public class TreeFactory {

    /**
     * @author Jose San Martin
     * Factory for creating a list of trees from the input command
     */
    private static final String commandProps = "backend/resources/Command";
    private static final String commandError = "backend/resources/Errors";
    private static final String pathToNode = "backend.Commands.";
    public ResourceBundle myErrors;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private Turtle myTurtle;
    private Storage myStorage;

    public TreeFactory(Turtle turtle, Storage storage) {
        myTurtle = turtle;
        mySymbols = new ArrayList<>();
        addPatterns(commandProps);
        myErrors = ResourceBundle.getBundle(commandError);
        myStorage = storage;
    }


    /**
     * Returns a list of Nodes when given a List of string commands. Each Node is the root of a tree
     * of commands. with the children being the arguments
     */
    //MostCommands have children, while arguments don't (they are simply argumentNodes)
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
            //If we haven't reached the max number of arguments required
            while (myRoot.getNumChildren() != getArgNum(command)) {
                //Check if the next item is an open bracket (because lists are ALWAYS children yes ma'am)
                Node nextChild;
                if (isOpenBracket(commands.get(0))) {
                    System.out.println("Here");
                    nextChild = generateList(commands);
                    commands.remove(0); //Remove that ending bracket!
                }
                else if(isVariable(commands.get(0)) & command.equals("MakeVariable")){ //We are currently making/updating a var
                    nextChild = newVariable(commands);
                }
                else {
                    nextChild = createChild(commands); //if not list, it is a command
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

    private Node createChild(List<String> commands) throws IllegalCommandException {
        if (commands.size() == 0) {
            return null;
        }
        Node newChild;
        String nextChild = commands.remove(0);
        //if (isLeftParenthesis(nextChild)) { //check if its a parenthesis FIRST
        //    String nextCommand = commands.remove(0);
        //    newChild = createRoot(nextCommand);
        //    generateCommand(newChild, commands, getArgNum(nextCommand));}
        if (isOpenBracket(nextChild)) {
            newChild = generateList(commands);
            commands.remove(0); //Remove that ending bracket!
        }
          else if (!isNotCommand(nextChild)) {
            newChild = createRoot(nextChild);
            //This child has its own arguments that we need to add. Use recursion!
            generateCommand(newChild, commands, getArgNum(nextChild));
        } else {
            newChild = getLeafNode(nextChild);
        }

        return newChild;

    }

    private Node createRoot(String command) throws IllegalCommandException {
        Node newNode;
        //if (isVariable(command)) {
        //    newNode = new GetVariable(myStorage, myTurtle, new ArrayList<>());
        //    newNode.addChild(new Number(command.substring(1))); //Variables need to have a child to begin with
        //    System.out.println(command.substring(1));}
        if (!isNotCommand(command)) {
            newNode = reflect(command); //Use reflection to get our class

        } else {
            //If not command or variable, it is a LeafNode
            newNode = getLeafNode(command);
        }
        return newNode;
    }

    /**
     * Creates a list with all of the commands inside of the list
     * Returns the root of a ListNode, which is an argument to another command
     */
    private Node generateList(List<String> Commands) throws IllegalCommandException {
        String newList = Commands.remove(0);
        Node commandList = new ListNode(myStorage, myTurtle, new ArrayList<>());
        while (!isCloseBracket(Commands.get(0))) {
            //Create the new command and add all of it's children
            String nextCommand = Commands.remove(0);
            Node newChild = createRoot(nextCommand);
            if(newChild instanceof RootNode && !(newChild instanceof GetVariable)){
                int numChild = getArgNum(nextCommand);
                generateCommand(newChild, Commands, numChild);
            }
            //Add this command to our ListNode
            commandList.addChild(newChild);
        }

        return (commandList);
    }

    /**
     * A utility method to help us obtain all of the children of a command
     */
    public void generateCommand(Node commandRoot, List<String> Commands, int numChild) throws IllegalCommandException {
        while (commandRoot.getNumChildren() != numChild) {
            commandRoot.addChild(createChild(Commands));
        }
    }


    /**
     * Returns the correct type of LeafNode that should be instantiated
     * That is either a Text that corresponds to a new number, a variable that we are grabbing,
     * or a
     */
    //TODO: NOT WORKING!!!!!
    public Node getLeafNode(String nextChild){
        if(isVariable(nextChild) & !myStorage.hasVar(nextChild.substring(1))){
            myStorage.addVarName(nextChild.substring(1));
            return new Text(nextChild.substring(1));
        }
        else if(myStorage.hasVar(nextChild.substring(1))){
            //return new Number(myStorage.getVar(nextChild.substring(1)).toString());
            var temp = new GetVariable(myStorage, myTurtle, new ArrayList<>());
            temp.addChild(new Text(nextChild.substring(1)));
            return temp;


        }
        else{
            return new Number(nextChild);
        }
    }
    /**
     * Reflection
     */
    public Node reflect(String command) throws IllegalCommandException {
        Class myClass;
        try {
            myClass = Class.forName(pathToNode+command);
        } catch (ClassNotFoundException e) {
            throw new IllegalCommandException(e);
        }

        Class[] types = {Storage.class, Turtle.class, List.class};
        Constructor constructor;
        try {
            constructor = myClass.getConstructor(types);
        } catch (NoSuchMethodException e) {
            throw new IllegalCommandException(e);
        }

        Object[] parameters = {myStorage, myTurtle, new ArrayList<>()};
        Object newInstance = null;
        try {
            newInstance = constructor.newInstance(parameters);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (Node) newInstance;
    }

    public void unlimitedParams(List<String> commands, List<Node> myRoots) throws IllegalCommandException {
        String rootCommand = commands.remove(0);
        System.out.println("um");
        while(!isRightParenthesis(commands.get(0))){
            Node mainCommand = createRoot(rootCommand); //Create the top command every as long as there are new parameters
            generateCommand(mainCommand, commands, getArgNum(rootCommand)); //generates a command
            myRoots.add(mainCommand);
        }
        commands.remove(0); //remove the last parenthesis

    }

    private Node newVariable(List<String> commands){
        String child = commands.remove(0);
        myStorage.addVarName(child.substring(1));
        return new Text(child.substring(1));
    }






    private boolean isVariable(String s) {
        return s.matches(":[a-zA-Z_]+");
    }

    private boolean isNotCommand(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+|\\\"[a-zA-Z]+|:[a-zA-Z_]+");
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
    private int getArgNum(String text) {
        for (var e : mySymbols) {
            if (e.getValue().matcher(text).matches()) {
                return Integer.parseInt(e.getKey());
            }
        }
        //This will never get thrown, because we would have thrown it in a previous matching method
        return 0;
    }
}
