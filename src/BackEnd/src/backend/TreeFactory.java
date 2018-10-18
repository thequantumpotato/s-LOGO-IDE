package backend;

import backend.Nodes.*;

import java.util.*;
import java.util.regex.Pattern;

public class TreeFactory {

    private static final String commandProps = "backend/resources/Command";
    private List<Map.Entry<String, Pattern>> mySymbols;

    public TreeFactory(){
        mySymbols = new ArrayList<>();
        addPatterns(commandProps);
    }


    /**
     * Returns a list of Nodes when given a List of string commands. Each Node is the root of a tree
     * of commands. with the children being the arguments
     */
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
    private BasicNode createChild(List<String> commands) throws IllegalCommandException {
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

    //TODO make the other types of nodes, the one that take no arguments
    private BasicNode createRoot(String command) throws IllegalCommandException {
        BasicNode newNode;
        if(!isNumeric(command)){
            String numArgs = getArgNum(command);
            if(numArgs.equals("Single")){
                newNode = new SingleCommandNode(command);
            }
            else if(numArgs.equals("Double")){
                newNode = new DoubleCommandNode(command);
            }
            else{
                newNode = new ZeroCommandNode(command);
            }


        }else{
            newNode = new argumentNode(command);
        }
        return newNode;
    }


    private boolean isNumeric(String s){
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    private void addPatterns(String syntax) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns whether a command requires one, two three, or more arguments
     */
    private String getArgNum(String text) throws IllegalCommandException {
        for (var e : mySymbols) {
            if (e.getValue().matcher(text).matches()) {
                    return e.getKey();
            }
        }
        //This will never get thrown, because we would have thrown it in a previous matching method
        throw new IllegalCommandException();
    }
}
