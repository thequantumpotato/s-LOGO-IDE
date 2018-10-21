package frontend;

import backend.IllegalCommandException;
import backend.ModelController;
import backend.Turtle;
import javafx.stage.Stage;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Controller mediates the communications between the View and the Model. <br>
 * Controller is initialized when the Main.java starts running. It will contain an instance of View and Model.
 *
 * @author Vincent Liu
 */
public class Controller {
    private View myView;
    private Turtle myTurtle;
    private ModelController modelController;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private ResourceBundle myResources;
    public ResourceBundle myErrors;
    private static final String commandError = "Errors";

    /**
     * TO DO: Attach myResource onto GUI and modelController to deal with different languages
     */
    public Controller(Stage primaryStage, Turtle myTurtle_, String language, String syntax) {
        mySymbols = new ArrayList<>();
        addPatterns(language);
        addPatterns(syntax);
        myResources = ResourceBundle.getBundle(language);
        myTurtle = myTurtle_;
        myView = new View(primaryStage, this, myTurtle);
        myView.registerDisplay(myTurtle);
        modelController = new ModelController(myTurtle, mySymbols);
        myErrors = ResourceBundle.getBundle(commandError);
    }

    public void runCommand(String input) {
        try {
            modelController.parseCommand(input);
        } catch (Exception e) {
            if (e instanceof IllegalCommandException) {
                myView.displayErrors(myErrors.getString("commandError"));
            }
//            else if
            else myView.displayErrors(e.toString());
        }
    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    //TODO move this stuff to a utility class
    private void addPatterns(String syntax) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    private void initializeCommands() {

    }

    public void parse(String input) {

    }
}
