package backend.Storage;

import backend.Commands.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This is the function class that stores the definition of a function.
 * It contains its name, a list of commands, a list of argument names, and an argument map.
 * It is well designed because it is encapsulated, flexible, and very readable.
 * It has a newinstance() method that returns a new instance of itself so an instance of the function can be run
 *   without modifying the original copy of the function.
 * The function is used in storage class to implement recursion and user defined functions.
 *
 * @author Harry Xie
 */

public class Function {
    private String name;
    private List<Node> nodeList;
    private List<String> argsList;
    private Map<String, Object> argsMap;

    public Function(String name){
        this.name = name;
        this.nodeList = new ArrayList<>();
        argsMap = new HashMap<>();
        argsList = new ArrayList<>();
    }

    public Function(String name, List<String> argsList, List<Node> nodeList){
        this.name = name;
        this.argsList = argsList;
        this.nodeList = nodeList;
        argsMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void addArgument(String name){
        argsMap.put(name, 0);
        argsList.add(name);
    }

    public void setArguments(List<Object> o){
        for(int i = 0; i < o.size(); i++){
            argsMap.put(argsList.get(i), o.get(i));
        }
    }

    public void setBody(List<Node> l){
        this.nodeList = l;
    }

    // this method returns a new separate function object; essentially a deep copy
    public Function newInstance(){
        return new Function(this.name, this.argsList, this.nodeList);
    }

    public List<String> getArgsList() {
        return argsList;
    }

    public Map<String, Object> getArgsMap() {
        return argsMap;
    }
}
