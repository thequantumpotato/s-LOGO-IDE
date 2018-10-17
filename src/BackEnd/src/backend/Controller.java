package backend;

import java.util.List;

public class Controller {
    private Interpreter interpreter;
    private List<String> myCommands;

    public Controller(){
        interpreter = new Interpreter();
    }

    /**
     * Parses the
     */
    public void parseCommand(String input) throws Exception {
        myCommands = interpreter.parse(input);

        //Use reflection on myCommands

    }

    public void updateVar(String varName, String varVal){

    }

}


