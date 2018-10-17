package backend.Nodes;

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




}
