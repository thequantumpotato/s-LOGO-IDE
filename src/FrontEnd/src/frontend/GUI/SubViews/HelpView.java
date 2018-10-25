package frontend.GUI.SubViews;

import frontend.API.SubView;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * A help page that displays all available command as hyperlinks in a ListView.
 * After being clicked, an information window will pop up to display the description of the command.
 *
 * @author Vincent Liu
 */

public class HelpView implements SubView {
    public static final String HELP_PATH = "CommandHelp";
    private VBox helpView;
    private ListView helpList;
    private ResourceBundle commandHelpBundle;
    private Enumeration commandHelpKeys;

    public HelpView() {
        helpView = new VBox();
        Label title = new Label("Command help: ");
        helpList = new ListView();
        helpView.getChildren().addAll(title, helpList);
        helpView.getStyleClass().add("helpView");
        addCommandAndDesc();
        helpView.setVgrow(helpList, Priority.ALWAYS);
    }

    private void addCommandAndDesc() {
        commandHelpBundle = ResourceBundle.getBundle(HELP_PATH);
        commandHelpKeys = commandHelpBundle.getKeys();
        while (commandHelpKeys.hasMoreElements()) {
            Hyperlink commandClickable = new Hyperlink();
            String commandName = commandHelpKeys.nextElement().toString().replaceAll("\\\\", "");
            commandClickable.setText(commandName);
            commandClickable.setOnMouseClicked(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(commandName);
                alert.setHeaderText("Description: ");
                alert.setContentText(commandHelpBundle.getString(commandName));
                alert.showAndWait();
            });
            helpList.getItems().add(commandClickable);
        }
    }

    @Override
    public Node getView() {
        return helpView;
    }
}
