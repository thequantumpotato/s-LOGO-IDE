package backend.Storage;

import java.util.*;

/**
 * The storage class stores the custom variables, functions, and stackframe when a function is ran.
 * It also provides necessary variable and function information to the front-end.
 * Stackframe is used in recursion so that we can access variables with the same name but are inside different functions.
 * This is a well-designed class because it is extensible: if we want to implement additional data strcutures, we
 *   can easily store them here as well.
 *
 * @author Harry Xie
 */
public class Storage {

    private Map<String, Variable> vMap;
    private Map<String, Function> fMap;
    private Stack<Function> stackFrame;
    private Set<String> varSet;

    public Storage(){
        vMap = new HashMap<>();
        stackFrame = new Stack<>();
        fMap = new HashMap<>();
        varSet = new HashSet<>();
    }

    public Object getVar(String name){
        System.out.println("stackFrame isempty:" + stackFrame.isEmpty());
        System.out.println("fMap:" + fMap);
        if(fMap.containsKey("sumrecurse")){
            System.out.println("fmap:"+fMap.get("sumrecurse").getName());
        }
        if(!stackFrame.isEmpty()){
            System.out.println("stackFrame peek():" + stackFrame.peek());
            System.out.println("function variable map:" + stackFrame.peek().getArgsMap());
            System.out.println("function variable list:" + stackFrame.peek().getArgsList());
            System.out.println("function name:" + stackFrame.peek().getName());
            System.out.println("function nodeList:" + stackFrame.peek().getNodeList());
        }
        System.out.println("variable Map - expected empty: "+vMap);
        if(!stackFrame.isEmpty()){
            Map<String, Object> tmpMap = stackFrame.peek().getArgsMap();
            if(tmpMap.containsKey(name)){
                return tmpMap.get(name);
            }
        }
        if(vMap.containsKey(name)){
            return vMap.get(name).getValue();
        }
        return null;
    }

    public void addVarName(String name){
        varSet.add(name);
    }

    public boolean hasVar(String name){

        return (vMap.containsKey(name)) || varSet.contains(name);
    }

    public void makeVar(String name, Object val){
        if(!stackFrame.isEmpty()){
            stackFrame.peek().getArgsMap().put(name, val);
        }
        varSet.add(name);
        vMap.put(name, new Variable(name, val));
    }

    public void removeVar(String name){
        vMap.remove(name);
    }

    /**
     * return a string representation of variables
     * @return a map <Name, Value>
     */
    public Map<String, String> getVarMap(){
        Map<String, String> res = new HashMap<>();
        for(String n: vMap.keySet()){
            res.put(n, vMap.get(n).toString());
        }
        return res;
    }

    /**
     * return a list of existing instructions
     * @return
     */
    public List<String> getInsList(){
        List<String> res = new ArrayList<>();
        for(String n: fMap.keySet()){
            res.add(n);
        }
        return res;
    }

    public void makeFunction(String name, Function f){
        fMap.put(name, f);
    }

    public Function getFunction(String name){
        return fMap.get(name);
    }

    public void pushToStack(Function f){
        stackFrame.push(f);
    }

    public void popFromStack(){
        stackFrame.pop();
    }
}
