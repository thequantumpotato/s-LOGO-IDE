package backend.Storage;

import backend.Commands.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    private Map<String, Variable> vMap;
    private Map<String, Instruction> iMap;

    public Storage(){
        vMap = new HashMap<>();
        iMap = new HashMap<>();
    }

    public Object getVar(String name){
        return vMap.get(name).getValue();
    }

    public List<Node> getIns(String name){
        return iMap.get(name).getInstruction();
    }

    public boolean makeVar(String name, Object val){
        if(vMap.keySet().contains(name)){
            return false;
        }
        vMap.put(name, new Variable(name, val));
        return true;
    }

    public boolean makeIns(String name, List<Node> commands){
        if(iMap.keySet().contains(name)){
            return false;
        }
        iMap.put(name, new Instruction(name, commands));
        return true;
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
        for(String n: iMap.keySet()){
            res.add(n);
        }
        return res;
    }
}
