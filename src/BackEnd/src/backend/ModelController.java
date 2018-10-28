package backend;

import backend.Commands.LeafNode;
import backend.Commands.Node;
import backend.Storage.Storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Jose San Martin, Henry Xie
 * A controller class that performs reflection on a list of trees to move the turtle.
 */
public class ModelController {
    private Interpreter interpreter;
    private List<Node> myCommands;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private Turtle myTurtle;
    private TreeFactory myTreeFactory;
    private boolean hasNewVar;
    private boolean hasNewFunc;
    private Storage myStorage;

    public ModelController(Turtle turtle, List<Map.Entry<String, Pattern>> symbolList) {
        mySymbols = symbolList;
        interpreter = new Interpreter(symbolList);
        myTurtle = turtle;
        myStorage = new Storage();
        myTreeFactory = new TreeFactory(myTurtle, myStorage);
    }

    /**
     * Parses the Command
     */
    public void parseCommand(String input) throws Exception {
        //returns a list of root nodes. e.g. [Forward, 50]
        List<String> commands = interpreter.parse(input);
        for(String command:commands){
            System.out.println(command);
        }
        //Turn our command arraylist into a tree structure of command nodes
        myCommands = myTreeFactory.getRoots(commands);
        System.out.println("My turtle before running command: " + myTurtle.getTurtleLeaf(0));

        for (Node node : myCommands) {
            node.run();
        }
        System.out.println("Turtle Ran!");
        myTurtle.Changed();
        myTurtle.notifyAllObservers(true);
        myTurtle.clear();
    }


    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public Map<String, String> updateVar() {
        return myStorage.getVarMap();
    }

    public List<String> updateFunc() {
        return myStorage.getInsList();
    }

}

