package frontend.GUI;

import backend.Turtle;
import frontend.API.ViewInternalAPI;
import frontend.ExternalAPI.ViewAPI;
import frontend.GUI.Display.DisplayView;
import frontend.GUI.SubViews.*;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Controller;

import java.util.List;
import java.util.Map;

/**
 * View is an aggregate of all the Nodes and is one level lower than Controller. It puts all the sub-components
 * together while specifying all the APIs needed for them to interact with each other or to the external world.
 *
 * Multiple such gridPanes can be initialized inside TabView to be the content of a single tab and they will
 * be independent of each other.
 *
 * @author Vincent Liu
 */
// TODO: 10/25/18 Make the windows expand and contract --- Accordion and TitlePane
// TODO: 10/28/18 Add Palette
public class View implements ViewInternalAPI, ViewAPI {
    public static final double DEFAULT_PEN_SIZE = 1;
    public final int DEFAULT_PEN_TIME = 10;
    private final Turtle myTurtle;
    private DisplayView myDisplayView;
    private CommandView myCommandView;
    private VariableView myVariableView;
    private FunctionView myFunctionView;
    private HistoryView myHistoryView;
    private SettingView mySettingView;
    private HelpView myHelpView;
    private Controller myController;
    private Stage myStage;
    private GridPane myGridPane;

    public View(Stage primaryStage, Controller myController_, Turtle turtle, String initLang) {
        myStage = primaryStage;
        myController = myController_;
        myTurtle = turtle;
        myGridPane = createGridPane(initLang);
    }

    /**
     * Getter Methods
     */

    public Stage getMyStage() {
        return myStage;
    }

    /**
     * Helper methods
     **/

    private GridPane createGridPane(String initLang) {
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("gridPane");
        setUpGridPaneProportion(gridPane);
        initAndAddElements(gridPane, initLang);
        return gridPane;
    }

    private void setUpGridPaneProportion(GridPane gridPane) {
        var column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        var column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        var column3 = new ColumnConstraints();
        column3.setPercentWidth(20);
        var row1 = new RowConstraints();
        row1.setPercentHeight(10);
        var row2 = new RowConstraints();
        row2.setPercentHeight(40);
        var row3 = new RowConstraints();
        row3.setPercentHeight(40);
        var row4 = new RowConstraints();
        row4.setPercentHeight(10);
        gridPane.getColumnConstraints().addAll(column1, column2, column3);
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4);
    }

    private void initAndAddElements(GridPane gridPane, String initLang) {
        initializeElements(initLang);
        addElements(gridPane);
    }

    private void initializeElements(String initLang) {
        myDisplayView = new DisplayView(this, myTurtle);
        myCommandView = new CommandView(this);
        myVariableView = new VariableView(this);
        myFunctionView = new FunctionView(this);
        myHistoryView = new HistoryView(this);
        myHelpView = new HelpView();
        mySettingView = new SettingView(this, initLang);
    }

    private void addElements(GridPane gridPane) {
        gridPane.add(mySettingView.getView(), 0, 0, 3, 1);
        gridPane.add(myHelpView.getView(), 0, 1, 1, 1);
        gridPane.add(myHistoryView.getView(), 0, 2, 1, 1);
        gridPane.add(myDisplayView.getView(), 1, 1, 1, 2);
        gridPane.add(myVariableView.getView(), 2, 1, 1, 1);
        gridPane.add(myFunctionView.getView(), 2, 2, 1, 1);
        gridPane.add(myCommandView.getView(), 0, 3, 3, 1);
    }

    /**
     * Internal ExternalAPI
     **/

    @Override
    public void changeBgColor(Color bgColor) { myDisplayView.changeBgColor(bgColor); }

    @Override
    public void changePenColor(Color penColor) {
        myDisplayView.changePenColor(penColor);
    }

    @Override
    public void changePenSize(double penSize) {
        myDisplayView.changePenSize(penSize);
    }

    @Override
    public void changePenDown(boolean penDown) {
        myDisplayView.setPenDown(penDown);
    }

    @Override
    public void changeTurtleImg(Image image) {
        myDisplayView.changeTurtleImg(image);
    }

    @Override
    public void changeAnimationSpeed(Double time) {
        myDisplayView.changeAnimationSpeed(time);
    }

    @Override
    public void changeLanguage(String language) {
        myController.setUpBackEnd(language);
    }

    @Override
    public void updateVar(Map<String, String> variable) {
        myController.updateVar(variable);
    }

    /**
     * External APIs
     **/

    @Override
    public void passCommand(String input) {
        myController.runCommand(input);
    }

    @Override
    public GridPane getMyGridPane() { return myGridPane; }

    @Override
    public void displayErrors(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Erroneous Command: ");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @Override
    public void registerDisplay(Turtle turtle) {
        turtle.addAnObserver(myDisplayView);
    }

    @Override
    public void displayVar(Map<String, String> variable) {
        myVariableView.updateVariable(variable);
    }

    @Override
    public void displayFunc(List<String> function) {
        myFunctionView.updateFunction(function);
    }

    @Override
    public void updateHistory(String validInput) {
        myHistoryView.updateHistory(validInput);
    }

}
