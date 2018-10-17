package backend;

import backend.Nodes.BasicNode;
import backend.Nodes.SingleCommandNode;
import backend.Nodes.argumentNode;

import java.util.ArrayList;
import java.util.List;

public class TreeFactory {

    public TreeFactory(){

    }

    //TODO implement the tree factory lol
    //Commands have children, while arguments don't (they are simply argumentNodes)
    public List<BasicNode> getRoots(List<String> commands) throws IllegalCommandException {
        List<BasicNode> myRoots = new ArrayList<>();
        while(commands.size() != 0){
            String command = commands.remove(0);
            BasicNode myRoot = createRoot(command);
            //If we haven't reached the max number of arguments required
            while (myRoot.getNumChildren() != myRoot.getRequiredArguments()) {
                BasicNode nextChild = createChild(commands);

                if(nextChild == null){
                    throw new IllegalCommandException();
                }
                myRoot.addChild(nextChild);
            }
            myRoots.add(myRoot);
        }

        return myRoots;
    }

    //TODO: Implement brackets and parenthesis
    private BasicNode createChild(List<String> commands){
        if(commands.size() == 0){
            return null;
        }
        BasicNode newChild;
        String nextChild = commands.remove(0);

        if(!isNumeric(nextChild)){
            newChild = createRoot(nextChild);
            //This child has its own arguments that we need to add. Use recursion!
            while (newChild.getNumChildren() != newChild.getRequiredArguments()) {
                newChild.addChild(createChild(commands));
            }
        }
        else{
            newChild = new argumentNode(nextChild);
        }

        return newChild;

    }

    //TODO make it generic for all commands, not just singleCommands
    private BasicNode createRoot(String command){
        BasicNode newNode;
        if(!isNumeric(command)){
            newNode = new SingleCommandNode(command);
        }else{
            newNode = new argumentNode(command);
        }
        return newNode;
    }


    private boolean isNumeric(String s){
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
}
