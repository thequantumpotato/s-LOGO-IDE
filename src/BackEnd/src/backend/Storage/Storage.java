package backend.Storage;

import backend.Command;

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

    public List<Command> getIns(String name){
        return iMap.get(name).getInstruction();
    }

    public boolean makeVar(String name, Object val){
        if(vMap.keySet().contains(name)){
            return false;
        }
        vMap.put(name, new Variable(name, val));
        return true;
    }

    public boolean makeIns(String name, List<Command> commands){
        if(iMap.keySet().contains(name)){
            return false;
        }
        iMap.put(name, new Instruction(name, commands));
        return true;
    }
}
