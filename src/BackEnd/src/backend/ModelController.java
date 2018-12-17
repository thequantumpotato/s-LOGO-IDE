package backend;

import backend.Commands.Node;
import backend.Storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * This class is a good design in my opinion because of how simple it is yet how much it does. Its purpose is to essentially
 * be the controller of the BackEnd module, and it is just that. This class acts as the communicator with the FrontEnd, and it
 * also acts as a data pipeline for the String recieved by the FrontEnd.
 */

/**
 * @author Jose San Martin, Harry Xie
 * A controller class that performs reflection on a list of trees to move the turtle.
 */
public class ModelController {
    private Interpreter interpreter;
    private List<Node> myCommands;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private Turtle myTurtle;
    private TreeFactory myTreeFactory;
    private Storage myStorage;

    public ModelController(Turtle turtle, List<Map.Entry<String, Pattern>> symbolList) {
        mySymbols = symbolList;
        interpreter = new Interpreter(symbolList);
        myTurtle = turtle;
        myStorage = new Storage();
        myTreeFactory = new TreeFactory(myTurtle, myStorage);
    }
    /**
     * My main Data Pipeline
     */
    public List<String> Pipeline(String input) throws Exception {
        List<String> commands = interpreter.parse(input);
        for (String command : commands) {
            System.out.println(command);
        }
        myCommands = myTreeFactory.getRoots(commands);

        List<Object> returnList = new ArrayList<>();
        for (Node node : myCommands) {
            returnList.add(node.run());
        }
        ArrayList<String> listString = parseToString(returnList);
        myTurtle.Changed();
        myTurtle.notifyAllObservers(true);
        myTurtle.clear();

        return listString;
    }
    private ArrayList<String> parseToString(List<Object> toParse) {
        ArrayList<String> toReturn = new ArrayList<>();
        for (Object p : toParse) {
            toReturn.add(p.toString());
        }
        return toReturn;
    }
    /**
     * Part of the External backend API. Allows the FrontEnd access to the stored Variables
     */
    public Map<String, String> updateVar() {
        return myStorage.getVarMap();
    }

    /**
     * Part of the External backend API. Allows the FrontEnd access to the stored Functions
     */
    public List<String> updateFunc() {
        return myStorage.getInsList();
    }

}

