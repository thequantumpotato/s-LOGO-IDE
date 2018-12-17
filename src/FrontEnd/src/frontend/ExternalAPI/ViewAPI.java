package frontend.ExternalAPI;

import backend.Turtle;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;

/**
 * Interface for external API of View.
 *
 * When initializing View in the Controller, its type would be ViewAPI but not simply View.
 * In this way, Controller could only call the following external methods carried in View.
 */

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
    void displayVar(Map<String, String> variable);

    /**
     * Called by Controller
     * Add a valid variable to the VariableView
     *
     * @param function
     */
    void displayFunc(List<String> function);

    /**
     * Called by Controller
     * Add a valid command to the HistoryView
     *
     * @param validInput
     */
    void updateHistory(String validInput);

    /**
     * Called by TabView to let Controller pass the GridPane from View
     *
     * @return GridPane
     */
    GridPane getMyGridPane();

    /**
     * Return the results to the View by the Controller after it completes the command
     */
    void returnValues(List<String> ret);
}
