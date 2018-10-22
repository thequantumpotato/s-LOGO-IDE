package frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * A panel to display all the functions that the user defines.
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
        Label title = new Label("Functions");
        functionView = new VBox();
        functionView.getChildren().addAll(title, functionList);
        functionView.getStyleClass().add("functionView");

        name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<VariableView.Variable, String>("funcName"));
        value = new TableColumn("Value");
        value.setCellValueFactory(new PropertyValueFactory<VariableView.Variable, String>("funcVal"));
        functionList.getColumns().addAll(name, value);
    }

    public void updateFunction(Map<String, String> func) {
        String key = func.keySet().toArray()[0].toString();
        functionList.getItems().add(new Function(key, func.get(key)));
    }

    @Override
    public Node getView() {
        return functionView;
    }

    public static class Function{
        private final SimpleStringProperty funcName;
        private final SimpleStringProperty funcVal;

        private Function(String funcName, String funcVal) {
            this.funcName = new SimpleStringProperty(funcName);
            this.funcVal = new SimpleStringProperty(funcVal);
        }

        public String getVarName() {
            return funcName.get();
        }

        public String getVarVal() {
            return funcVal.get();
        }
    }
}
