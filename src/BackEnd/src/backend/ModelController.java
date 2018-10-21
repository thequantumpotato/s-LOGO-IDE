package backend;


import backend.Nodes.BasicNode;
import backend.Nodes.argumentNode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Jose San Martin, Henry
 * A controller class that performs reflection on a list of trees to move the turtle.
 */
public class ModelController {
    private Interpreter interpreter;
    private List<BasicNode> myCommands;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private Command commander;
    private Turtle myTurtle;
    private Reflector myReflector;

    public ModelController(Turtle turtle, List<Map.Entry<String, Pattern>> symbolList){
        mySymbols = symbolList;
        interpreter = new Interpreter(symbolList);
        commander = new Command();
        myTurtle = turtle;
        myReflector = new Reflector(commander, myTurtle);

    }

    /**
     * Parses the
     */
    public void parseCommand(String input) throws Exception {
        myCommands = interpreter.parse(input); //returns a list of root nodes
        // System.out.println(myCommands);

        for(BasicNode node: myCommands){
            myReflector.execute(node);
        }
        myTurtle.Changed();
        myTurtle.notifyObservers(true);
        myTurtle.clear();

    }

    public void updateVar(String varName, String varVal){

    }

}

