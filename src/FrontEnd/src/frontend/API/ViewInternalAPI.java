package frontend.API;

import frontend.GUI.Display.PathManager;
import frontend.Util.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Map;

/**
 * All the internal methods of View that will be called by SubViews or other components.
 *
 * @author Vincent Liu
 */
public interface ViewInternalAPI {

    /**
     * Called by SettingView
     * Change the background color of the display
     *
     * @param bgColor
     */
    void changeBgColor(Color bgColor);

    /**
     * Called by SettingView
     * Change the pen color of the display
     *
     * @param penColor
     */
    void changePenColor(Color penColor);

    /**
     * Called by SettingView
     * Change the pen size of the display
     *
     * @param penSize
     */
    void changePenSize(double penSize);

    /**
     * Called by SettingView
     * Change the pen down status of the display
     *
     * @param penDown
     */
    void changePenDown(boolean penDown);

    /**
     * Called by SettingView
     * Change the image of the turtle
     *
     * @param image
     */
    void changeTurtleImg(Image image);

    /**
     * Called by SettingView
     * Change the animation speed
     *
     * @param time
     */
    void changeAnimationSpeed(Double time);

    /**
     * Called by SettingView
     * Change the language of the Command input and the interpreter
     *
     * @param language
     */
    void changeLanguage(String language);

    /**
     * Called by VariableView
     * Update the variable value after the user changes variable name/value through GUI
     */
    void updateVar(Map<String, String> variable);

    /**
     * Called by TurtleManager to let View call StateView and show the turtle state
     *
     * @param id
     * @param sprite
     * @param pathManager
     */
    void showState(String id, Sprite sprite, PathManager pathManager);

    /**
     * Called by TurtleManager when the mouse is not hovering above the turtle, thus showing nothing
     */
    void noShow();

    /**
     * When clicking save button, it saves the latest command, which is stored in the HistoryView.
     *
     * @return latest command as a string
     */
    String retrieveHistory();

    /**
     * Reset the turtle's speed and the path speed
     */
    void resetTurtle();
}
