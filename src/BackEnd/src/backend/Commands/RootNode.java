package backend.Commands;

import backend.Turtle;
import backend.Storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * super class for all commands.
 * This super class implements the node interface and provides a myriad of protected helper methods that
 *   reduces repetitive code in its subclasses.
 * This is well-designed because it is very flexible.
 * All the commands benefit from this design more or less, and it works well for both math operations and
 *   turtle operations.
 * Unfortunately, it has to take in turtle and storage as argument even if the command doesn't them due to
 *   the reflection mechanism.
 *
 * @author Harry Xie
 */
abstract public class RootNode implements Node{

    protected List<Node> myChildren;
    protected Turtle myTurtle;
    protected Storage myStorage;

    public RootNode(Storage storage, Turtle turtle, List<Node> children){
        this.myChildren = children;
        this.myTurtle = turtle;
        this.myStorage = storage;
    }

    public List<Node> getChildren(){
        return myChildren;
    }

    @Override
    public void addChild(Node child) {
        myChildren.add(child);
    }

    @Override
    public int getNumChildren() {
        return myChildren.size();
    }

    @Override
    public abstract Object run();

    protected List<Object> runChildren(){
        List<Object> oList = new ArrayList<>();
        for(Node c: myChildren){
            oList.add(c.run());
        }
        return oList;
    }

    protected List<Double> parseDoubles(List<Object> l){
        List<Double> res = new ArrayList<>();
        for(Object o: l){
            if(o instanceof Integer){
                res.add(((Integer) o).doubleValue());
            }
            else{
                res.add((Double) o);
            }
        }
        return res;
    }

    protected List<Integer> parseIntegers(List<Double> doubles){
        List<Integer> res = new ArrayList<>();
        for(Double d: doubles){
            res.add(d.intValue());
        }
        return res;
    }

    protected Object runNodeList(List<Node> l){
        for(int i = 0; i < l.size(); i++){
            Object result = l.get(i).run();
            if(i == l.size() - 1 || l.get(i) instanceof Return){
                return result;
            }
        }
        return null;
    }

}
