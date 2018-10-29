package backend.Storage;

import backend.Commands.Node;

import java.util.*;

public class Storage {

    private Map<String, Variable> vMap;
    private Map<String, Instruction> iMap;
    private HashSet<String> varNames;

    public Storage() {
        vMap = new HashMap<>();
        iMap = new HashMap<>();
        varNames = new HashSet<>();
    }

    public Object getVar(String name) {
        if (vMap.containsKey(name)) {
            return vMap.get(name).getValue();
        }
        return null;
    }

    public boolean hasVar(String name) {

        return (vMap.containsKey(name) || varNames.contains(name));
    }

    public List<Node> getIns(String name) {
        if (iMap.containsKey(name)) {
            return iMap.get(name).getInstruction();
        }
        return null;
    }

    public void makeVar(String name, Object val) {
        varNames.add(name);
        vMap.put(name, new Variable(name, val));
    }

    public boolean makeIns(String name, List<Node> commands) {
        if (iMap.keySet().contains(name)) {
            return false;
        }
        iMap.put(name, new Instruction(name, commands));
        return true;
    }

    public void removeVar(String name) {
        vMap.remove(name);
        varNames.remove(name);
    }

    public void addVarName(String name) {
        varNames.add(name);
    }

    /**
     * return a string representation of variables
     *
     * @return a map <Name, Value>
     */
    public Map<String, String> getVarMap() {
        Map<String, String> res = new HashMap<>();
        for (String n : vMap.keySet()) {
            res.put(n, vMap.get(n).toString());
        }
        return res;
    }

    /**
     * return a list of existing instructions
     *
     * @return
     */
    public List<String> getInsList() {
        List<String> res = new ArrayList<>();
        for (String n : iMap.keySet()) {
            res.add(n);
        }
        return res;
    }
}
