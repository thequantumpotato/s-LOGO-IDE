package backend.Commands;

public interface Node {

    void addChild(Node child);

    Object run();

}
