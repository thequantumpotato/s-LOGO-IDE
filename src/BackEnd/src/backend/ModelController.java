package backend;

import backend.Nodes.BasicNode;

import java.lang.reflect.Method;
import java.util.List;

public class ModelController {
    private Interpreter interpreter;
    private List<BasicNode> myCommands;
    //private Command commander;

    public ModelController(){
        interpreter = new Interpreter();
        //commander = new Command();
    }

    /**
     * Parses the
     */
    public void parseCommand(String input) throws Exception {
        myCommands = interpreter.parse(input);
        System.out.println(myCommands);
        //for(String s:myCommands){
        //    if(Integer.parseInt(s))
        //    Method command = commander.getClass().getDeclaredMethod(s);
        //    command.invoke(commander);
        //}

        //Use reflection on myCommands

    }

    public void updateVar(String varName, String varVal){

    }


}


