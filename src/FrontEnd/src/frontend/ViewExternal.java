package frontend;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Map;

/*
Ben
1. Turtle Movement (note, the turtle starts in the center of the display which is considered (0, 0).)
   - enter commands to the turtle interactively by entering text commands
   - see the results of the turtle executing commands displayed visually.
2. UI settings
   - set a background color for the turtle's display area
   - set an image to use for the turtle
   - set a color to use for the pen
3. Choose the language in which SLogo commands are understood (here are a few translations)
   - take advantage of Resource Bundle
4. Access help about available commands. it could just be a "command reference page", like the assignment web page, or you could integrate it into the GUI like IntelliJ does, using these reference text files
   - Create a new page for help.

Vincent
1. See commands previously run in the environment
    - History Panel, display all commands in scrollPane
2. User-defined Panels
    see variables currently available in the environment
        - VariablePanel, need to know the data structure of the info passed back
    see user-defined commands currently available in the environment
        - FunctionPanel, need to know the data structure of the info passed back
3. See errors that may result from entered commands in a user friendly way (i.e., not just printed to the console)
   - error types need to be defined in the Parse class in the method of parsing

 */

/**
 * A playground for adding external APIs in View
 *
 * @author Vincent Liu
 */

public class ViewExternal extends View{

    public ViewExternal(Stage primaryStage, Controller myController_) {
        super(primaryStage, myController_);
    }

    public void displayVars(Map<String, String> variables) {

    }

    public void displayFunctions(Map<String, String> functions) {

    }

    public void displayErrors(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Erroneous Command: ");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    /**
     * The following code is for View Internal
     */
    public void changeBgColor(Color bgColor) {
        // myDisplayView.changeBgColor();
    }

    public void changePenColor(Color penColor) {
        // What should be the relationship between turtle and pen?
    }

    public void changeTurtleImg(Image newTurtleImg) {

    }
}