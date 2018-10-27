package frontend.ExternalAPI;

import backend.Turtle;

import java.util.Map;

public interface ViewAPI {

    /**
     * Called by CommandView
     * Delegate the command string to the Controller to be processed
     *
     * @param input Input String
     */
    void passCommand(String input);

    /**
     * Called by Controller
     * Display an error message when an error in the command is detected
     * Pass a customized error message from the Errors.properties
     *
     * @param errorMessage String of error message
     */
    void displayErrors(String errorMessage);

    /**
     * Called by Controller in its initialization
     * Add an observer to the turtle to dynamically update its coordinate
     *
     * @param turtle Turtle object
     */
    void registerDisplay(Turtle turtle);

    /**
     * Called by Controller
     * Add a valid variable to the VariableView
     *
     * @param variable
     */
    void addVar(Map<String, String> variable);

    /**
     * Called by Controller
     * Add a valid variable to the VariableView
     *
     * @param function
     */
    void addFunc(Map<String, String> function);

    /**
     * Called by Controller
     * Add a valid command to the HistoryView
     *
     * @param validInput
     */
    void updateHistory(String validInput);




}
