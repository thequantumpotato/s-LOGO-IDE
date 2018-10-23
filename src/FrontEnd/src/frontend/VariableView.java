package frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * A panel (using TableView) to display all the variables that the user defines.
 * The variables are editable, and the new values entered by the use will be updated in the backend.
 *
 * @author Vincent Liu
 */

public class VariableView implements SubView {

    private VBox variableView;
    private TableView variableList;
    private TableColumn name;
    private TableColumn value;

    public VariableView() {
        variableList = new TableView();
        setUpTable();
        variableList.getColumns().addAll(name, value);
    }

    private void setUpTable() {
        Label title = new Label("Variables");
        variableView = new VBox();
        variableView.getChildren().addAll(title, variableList);
        variableView.getStyleClass().add("variableView");
        name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<Variable, String>("varName"));
        value = new TableColumn("Value");
        value.setCellValueFactory(new PropertyValueFactory<Variable, String>("varVal"));
    }

    public void updateVariable(Map<String, String> var) {
        String key = var.keySet().toArray()[0].toString();
        variableList.getItems().add(new Variable(key, var.get(key)));
    }

    @Override
    public Node getView() {
        return variableView;
    }

    // A Variable class to represent data for each row
    public class Variable {
        private final SimpleStringProperty varName;
        private final SimpleStringProperty varVal;

        public Variable(String varName, String varVal) {
            this.varName = new SimpleStringProperty(varName);
            this.varVal = new SimpleStringProperty(varVal);
        }

        public String getVarName() {
            return varName.get();
        }

        public String getVarVal() {
            return varVal.get();
        }
    }
}