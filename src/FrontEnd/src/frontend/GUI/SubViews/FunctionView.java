package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.View;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * A panel (using TableView) to contain and display all the functions that the user defines.
 * The functions are editable, and the new values entered by the use will be updated in the backend.
 *
 * @author Vincent Liu
 */
public class FunctionView implements SubView {

    private VBox functionView;
    private TableView functionList;
    private TableColumn name;
    private TableColumn value;
    private View myView;

    public FunctionView(View myView_) {
        myView = myView_;
        functionList = new TableView();
        setUpTable();
        functionList.getColumns().addAll(name, value);
    }

    private void setUpTable() {
        Label title = new Label("Functions");
        functionView = new VBox();
        functionView.getChildren().addAll(title, functionList);
        functionView.getStyleClass().add("functionView");

        name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<Function, String>("funcName"));
        value = new TableColumn("Value");
        value.setCellValueFactory(new PropertyValueFactory<Function, String>("funcVal"));
    }

    public void updateFunction(Map<String, String> func) {
        String key = func.keySet().toArray()[0].toString();
        functionList.getItems().add(new Function(key, func.get(key)));
    }

    @Override
    public Node getView() {
        return functionView;
    }

    // A Function class to represent data for each row
    private static class Function {
        private final SimpleStringProperty funcName;
        private final SimpleStringProperty funcVal;

        public Function(String funcName, String funcVal) {
            this.funcName = new SimpleStringProperty(funcName);
            this.funcVal = new SimpleStringProperty(funcVal);
        }

        public String getFuncName() {
            return funcName.get();
        }

        public String getFuncVal() {
            return funcVal.get();
        }
    }
}
