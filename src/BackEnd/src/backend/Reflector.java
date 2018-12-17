package backend;


import backend.Commands.Node;
import backend.Storage.Storage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
/**
 * Method created for my masterpiece. Allows the abstraction of the Reflection logic so that it isn't
 * within the TreeFactory class. I created this class, because I wanted to move the Reflection logic out of the TreeFactory
 * and into a class that will handle it independent. This class returns a Node if the reflection is successful, and otherwise it
 * throws an error.
 * @author: Jose San Martin (js665)
 */
public class Reflector {

    private static final String pathToNode = "backend.Commands.";
    private Storage myStorage;
    private Turtle myTurtle;
    public Reflector(Turtle turtle, Storage storage){
        myStorage = storage;
        myTurtle = turtle;
    }
    /**
     * Take in a string a return the class that represents that command,
     * via reflection
     */
    public Node reflect(String command) throws IllegalCommandException {
        Class myClass;
        System.out.println(pathToNode + command);
        try {
            myClass = Class.forName(pathToNode + command);
        } catch (ClassNotFoundException e) {
            throw new IllegalCommandException(e);
        }
        Class[] types = {Storage.class, Turtle.class, List.class};
        Constructor constructor;
        try {
            constructor = myClass.getConstructor(types);
        } catch (NoSuchMethodException e) {
            throw new IllegalCommandException(e);
        }
        Object[] parameters = {myStorage, myTurtle, new ArrayList<>()};
        Object newInstance = null;
        try {
            newInstance = constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return (Node) newInstance;
    }
}
