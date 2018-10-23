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
 * A panel to display all the variables that the user defines.
 *
 * @author Vincent Liu
 */

public class VariableView implements SubView {

    private VBox variableView;
    private TableView variableList;
    private View myView;
    private TableColumn name;
    private TableColumn value;

    public VariableView(View myView_) {
        myView = myView_;
        variableList = new TableView();
        Label title = new Label("Variables");
        variableView = new VBox();
        variableView.getChildren().addAll(title, variableList);
        variableView.getStyleClass().add("variableView");

        name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<Variable, String>("varName"));
        value = new TableColumn("Value");
        value.setCellValueFactory(new PropertyValueFactory<Variable, String>("varVal"));
        variableList.getColumns().addAll(name, value);
    }

    public void updateVariable(Map<String, String> var) {
        String key = var.keySet().toArray()[0].toString();
        variableList.getItems().add(new Variable(key, var.get(key)));
    }

    @Override
    public Node getView() {
        return variableView;
    }

    public static class Variable {
        private final SimpleStringProperty varName;
        private final SimpleStringProperty varVal;

        private Variable(String varName, String varVal) {
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
