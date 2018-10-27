package frontend.API;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Map;

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
 }
