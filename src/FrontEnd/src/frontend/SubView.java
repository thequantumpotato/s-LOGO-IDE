package frontend;

import javafx.scene.Node;

/**
 * An interface to make sure the sub-components of ViewAPI all return a Node to be displayed
 *
 * @author Vincent Liu
 */

public interface SubView {

    /**
     * Returns the Node to be added in the ViewAPI
     *
     * @return Node
     */
    Node getView();
}
