package backend;

import backend.Nodes.BasicNode;
import backend.Nodes.ArgumentNode;
import backend.Nodes.CommandNode;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Jose San Martin, Harry Xie
 * A controller class that performs reflection on a list of trees to move the turtle.
 */
public class ModelController {
    private Interpreter interpreter;
    private List<BasicNode> myCommands;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private Command commander;
    private Turtle myTurtle;
    private Map<String, ArgumentNode> variableMap = new HashMap<>();
    private Map<String, CommandNode> instructionMap = new HashMap<>();
    private Reflector myReflector;
    private Mediator myMediator;
    private final String colorPath = "backend/resources/Color";
    private ResourceBundle myColors;

    public ModelController(Turtle turtle, List<Map.Entry<String, Pattern>> symbolList){
        mySymbols = symbolList;
        interpreter = new Interpreter(symbolList);
        commander = new Command(this, turtle);
        myTurtle = turtle;
        myReflector = new Reflector(commander, myTurtle);
        myMediator = new Mediator();
        myColors = ResourceBundle.getBundle(colorPath);
    }

    /**
     * Parses the Command
     */
    public void parseCommand(String input) throws Exception {
        myCommands = interpreter.parse(input); //returns a list of root nodes
        // System.out.println(myCommands);

        for (BasicNode node : myCommands) {
            myReflector.execute(node);
        }
        myTurtle.Changed();
        myTurtle.notifyObservers(true);
        myTurtle.clear();
    }

    public boolean createVariable(String name){
        if(variableMap.keySet().contains(name)){
            return false;
        }
        variableMap.put(name, new ArgumentNode("0.0"));
        return true;
    }

    public boolean setVariable(String name, ArgumentNode value){
        if(!variableMap.keySet().contains(name)){
            return false;
        }
        variableMap.put(name, value);
        return true;
    }

    public ArgumentNode getVariable(String name){
        if(!variableMap.keySet().contains(name)){
            return null;
        }
        return variableMap.get(name);
    }

    public boolean createInstruction(String name, CommandNode inst){
        if(instructionMap.keySet().contains(name)){
            return false;
        }
        instructionMap.put(name, inst);
        return true;
    }

    public BasicNode getInstruction(String name){
        if(!instructionMap.keySet().contains(name)){
            return null;
        }
        return instructionMap.get(name);
    }

    private boolean isNumeric(String s){
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public void updateVar(String varName, String varVal){

    }

    public void clearScreen(){
        myMediator.clear();
    }

//    public void penUp(){
//        myMediator.penUp();
//    }
//
//    public void penDown(){
//        myMediator.penDown();
//    }
//
//    public void show(){
//        myMediator.show();
//    }
//
//    public void hide(){
//        myMediator.hide();
//    }
//
//    public boolean getPenStatus(){
//        myMediator.getPenStatus();
//    }
//
    public void setBackground(String colorName){
        try {
            String rgb = myColors.getString(colorName);
            List<Integer> rgbList = new ArrayList<>();
            for(String e: rgb.split(",")){
                rgbList.add(Integer.parseInt(e));
            }
            myMediator.setBackground(new Color(rgbList.get(0), rgbList.get(1).rgbList.get(2)));
        } catch(MissingResourceException e){
            handleMissingResource();
        }
    }

}

