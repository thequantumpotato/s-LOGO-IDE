package backend.Commands;

import backend.Turtle;

import java.util.ArrayList;
import java.util.List;

abstract public class RootNode implements Node{

    protected List<Node> children;
    protected Turtle myTurtle;

    public RootNode(Turtle turtle){
        children = new ArrayList<>();
        //this.children = children;
        this.myTurtle = turtle;
    }

    @Override
    public int getNumChildren() {
        return children.size();
    }

    @Override
    public void addChild(Node child) {
        children.add(child);
    }

    @Override
    public abstract Object run();

    protected List<Object> runChildren(){
        List<Object> oList = new ArrayList<>();
        for(Node c: children){
            oList.add(c.run());
        }
        return oList;
    }
}
