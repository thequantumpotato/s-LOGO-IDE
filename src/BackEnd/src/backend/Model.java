package backend;

import java.util.List;

public class Model {
    private Interpreter interpreter;
    private Turtle turtle;

    public Model(){
        interpreter = new Interpreter();
        turtle = new Turtle();
    }

    private void initializeCommands(){
    }

    public void parse(String input){

    }
}
