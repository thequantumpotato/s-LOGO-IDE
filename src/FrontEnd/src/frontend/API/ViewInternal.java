package frontend.API;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Map;

 public interface ViewInternal {

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
     * Change the image of the turtle
     *
     * @param newTurtleImg
     */
     void changeTurtleImg(Image newTurtleImg);

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
     * Called by Controller
     * Add a valid command to the HistoryView
     *
     * @param validInput
     */
     void updateHistory(String validInput);

    /**
     * Called by Controller
     * Add a valid variable to the VariableView
     *
     * @param variable
     */
     void updateVar(Map<String, String> variable);

    /**
     * Called by Controller
     * Add a valid variable to the VariableView
     *
     * @param function
     */
     void updateFunction(Map<String, String> function);



}
