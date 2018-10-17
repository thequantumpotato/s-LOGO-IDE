package backend.Nodes;

import java.util.List;

public class argumentNode implements BasicNode {
    private int argument;
    private int numArguments = 0;

    public argumentNode(String value){
        argument = Integer.parseInt(value);
    }

    public void addChild(BasicNode node){
        return;
    }

    public int getNumChildren(){
        return 0;
    }

    public int getRequiredArguments(){
        return numArguments;
    }

    public String getCommandName(){
        return Integer.toString(argument);
    }

    public List<BasicNode> getChildren(){
        return null;
    }




}
