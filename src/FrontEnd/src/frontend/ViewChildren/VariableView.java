package frontend.ViewChildren;

import frontend.API.SubView;
import frontend.View;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

import java.util.HashMap;
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
    private TableColumn<Variable, String> name;
    private TableColumn<Variable, String> value;
    private View myView;

    public VariableView(View myView_) {
        myView = myView_;
        variableList = new TableView();
        variableList.setEditable(true);
        setUpTable();
        variableList.getColumns().addAll(name, value);
    }

    private void setUpTable() {
        Label title = new Label("Variables");
        setUpBox(title);
        setUpNameCol();
        setUpValCol();
    }

    public void updateVarName(TableColumn.CellEditEvent<Variable, String> t) {
        String newName = t.getNewValue();
        var thisRow = t.getTableView().getItems().get(t.getTablePosition().getRow());
        thisRow.setVarName(newName);
        String val = thisRow.getVarVal();
        Map<String, String> res = new HashMap<>();
        res.put(newName, val);
        myView.updateVar(res);
    }

    public void updateVarVal(TableColumn.CellEditEvent<Variable, String> t) {
        String newVal = t.getNewValue();
        var thisRow = t.getTableView().getItems().get(t.getTablePosition().getRow());
        thisRow.setVarVal(newVal);
        String name = thisRow.getVarName();
        Map<String, String> res = new HashMap<>();
        res.put(name, newVal);
        myView.updateVar(res);
    }

    private void setUpValCol() {
        value = new TableColumn("Value");
        value.setCellValueFactory(new PropertyValueFactory<>("varVal"));
        value.setCellFactory(TextFieldTableCell.forTableColumn());
        value.setOnEditCommit((TableColumn.CellEditEvent<Variable, String> t) -> updateVarVal(t));
    }

    private void setUpNameCol() {
        name = new TableColumn("Name");
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellValueFactory(new PropertyValueFactory<>("varName"));
        name.setOnEditCommit((TableColumn.CellEditEvent<Variable, String> t) -> updateVarName(t));
    }

    private void setUpBox(Label title) {
        variableView = new VBox();
        variableView.getChildren().addAll(title, variableList);
        variableView.getStyleClass().add("variableView");
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

        public void setVarName(String varName) {
            this.varName.set(varName);
        }

        public void setVarVal(String varVal) {
            this.varVal.set(varVal);
        }
    }
}