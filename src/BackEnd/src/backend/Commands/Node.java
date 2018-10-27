package backend.Commands;

public interface Node {

    void addChild(Node child);

    int getNumChildren();

    Object run();

}
