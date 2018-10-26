package main;

import backend.IllegalCommandException;
import backend.ModelController;
import backend.Turtle;
import backend.TurtleLeaf;
import frontend.ExternalAPI.ViewAPI;
import frontend.GUI.View;
import javafx.stage.Stage;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Controller mediates the communications between the ViewAPI and the Model. <br>
 * Controller is initialized when the main.Main.txt starts running. It will contain an instance of ViewAPI and ModelController.
 *
 * @author Vincent Liu
 */
public class Controller {
    public static final String commandError = "Errors";
    public static final String LANG_PATH = "languages/";
    public static final String SYNTAX = "languages/Syntax";
    private ViewAPI myView;
    private Turtle myTurtle;
    private ViewControl viewControl;
    private ModelController modelController;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private ResourceBundle myErrors;

    public Controller(Stage primaryStage, String language) {
        mySymbols = new ArrayList<>();
        addPatterns(LANG_PATH + language); // language syntax
        addPatterns(SYNTAX);
        myTurtle = new TurtleLeaf();
        myView = new View(primaryStage, this, myTurtle, language);
        myView.registerDisplay(myTurtle);
//        viewControl = new ViewControl(myView.getMyDisplayView());
        modelController = new ModelController(myTurtle, mySymbols);
        myErrors = ResourceBundle.getBundle(commandError);
    }

    // TODO: 10/25/18 Figure out how to render different error types for user command
    public void runCommand(String input) {
        try {
            modelController.parseCommand(input);
            myView.updateHistory(input);
            checkBackEndVarUpdate();
            checkBackEndFuncUpdate();
        } catch (Exception e) {
            throwErrorByType(e);
        }
    }

    private void throwErrorByType(Exception e) {
        if (e instanceof IllegalCommandException) {
            myView.displayErrors(myErrors.getString("commandError"));
        } else myView.displayErrors(e.toString());
    }

    /**
     * Check function in the backend and display new one in the functionView
     */
    private void checkBackEndFuncUpdate() {
        Map<String, String> newFunc = modelController.updateFunc();
        if (!newFunc.isEmpty()) myView.addFunc(newFunc);
    }

    /**
     * Check variable in the backend and display new one in the VariableView
     */
    private void checkBackEndVarUpdate() {
        Map<String, String> newVar = modelController.updateVar();
        if (!newVar.isEmpty()) myView.addVar(newVar);
    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    // TODO move this stuff to a utility class
    private void addPatterns(String syntax) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Run the command to update variable name and value entered by the user in the VariableView
     * @param var
     */
    public void updateVar(Map<String, String> var) {
        String key = var.keySet().toArray()[0].toString();
        try {
            modelController.parseCommand("make " + "\"" + key + " " + var.get(key));
        } catch (Exception e) {
            throwErrorByType(e);
        }
    }
}
