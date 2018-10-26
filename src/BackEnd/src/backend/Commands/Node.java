package backend.Commands;

public interface Node {

    void addChild(Node child);

    abstract public Object run();

}
