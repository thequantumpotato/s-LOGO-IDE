package frontend;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Enumeration;
import java.util.ResourceBundle;

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
        commandHelpBundle = ResourceBundle.getBundle(HELP_PATH);
        commandHelpKeys = commandHelpBundle.getKeys();

        while (commandHelpKeys.hasMoreElements()) {
            Hyperlink commandClickable = new Hyperlink();
            String commandName = commandHelpKeys.nextElement().toString().replaceAll("\\\\", "");
            commandClickable.setText(commandName);
            commandClickable.setOnAction(e -> {
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
