package backend.Commands;

import java.util.List;

public interface Node {

    void addChild(Node child);

    Object run();

    int getNumChildren();

    List<Node> getChildren();

}
