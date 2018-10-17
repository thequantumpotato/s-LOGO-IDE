package BackEnd.src.backend;

public class Model {
    private Interpreter interpreter;
    private Turtle turtle;

    public Model(){
        interpreter = new Interpreter();
        turtle = new Turtle;
    }

    private void initializeCommands(){
    }

    public void parse(String input){
        String formattedInput = interpreter.parse(input);
        commandMap.get(formattedInput).run();
    }
}
