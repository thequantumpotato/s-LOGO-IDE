package backend;


import backend.Commands.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * An interpreter based on regular expressions that matches program strings to
 * kinds of language features. Returns an arrayList of Strings that have been parsed,
 * if and only if they match the syntax properties
 *
 * @author José San Martin
 */
public class Interpreter {

    private static final String WHITESPACE = "\\s+";
    private static final String commandError = "backend/resources/Errors";
    public ResourceBundle myErrors;
    private List<Map.Entry<String, Pattern>> mySymbols;
    private List<String> myCommands;

    /**
     * Create an empty parser.
     */
    public Interpreter(List<Map.Entry<String, Pattern>> symbolList) {
        mySymbols = symbolList;
        myCommands = new ArrayList<>();
        myErrors = ResourceBundle.getBundle(commandError);
    }


    /**
     * Return the arrayList<String></String> back to the ModelController of all of the commands
     */
    public List<String> parse(String text) throws Exception {

        String[] textArr = text.split(WHITESPACE);
        for (var t : textArr) {
            if (t.trim().length() > 0) {
                myCommands.add(getSymbol(t));
            }
        }
        //reflection();
        //commands are in an arraylist, now create our tree structure
        return myCommands;

    }


    /**
     * Returns language's type associated with the given text if one exists
     */
    private String getSymbol(String text) //throws IllegalCommandException
    {
        for (var e : mySymbols) {
            if (match(text, e.getValue())) {
                if (e.getKey().equals("Constant") | e.getKey().equals("Variable")) {
                    return text;
                } else if (e.getKey().equals("commandName")) {
                    return "commandName";
                } else {
                    return e.getKey();
                }

            }
        }
        return "";
    }

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match(String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }


}