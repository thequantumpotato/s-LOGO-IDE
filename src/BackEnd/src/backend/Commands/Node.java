package backend.Commands;

/**
 * interface for Node classes. Implemented by rootNode and leafNode
 * @author Harry Xie
 */
public interface Node {

    void addChild(Node child);

    int getNumChildren();

    Object run();

}
