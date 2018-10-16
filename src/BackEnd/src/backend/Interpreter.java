package backend;


import java.util.*;
import java.util.regex.Pattern;

/**
 * An interpreter based on regular expressions that matches program strings to
 * kinds of language features. Returns an arrayList of Strings that have been parsed,
 * if and only if they match the syntax properties
 *
 * @author José San Martin
 */
public class Interpreter {

    private List<Map.Entry<String, Pattern>> mySymbols;
    private List<String> myCommands;


    /**
     * Create an empty parser.
     */
    public Interpreter() {
        mySymbols = new ArrayList<>();
        myCommands = new ArrayList<>();
        addPatterns("backend/resources/languages/English");
        addPatterns("backend/resources/languages/Syntax");
    }


    /**
     * Return the arrayList<String></String> back to the Controller of all of the commands
     */
    public List<String> parse(String[] text) {
        for (var t : text) {
            if (t.trim().length() > 0) {
                myCommands.add(getSymbol(t));
                System.out.println(getSymbol(t));
            }
        }

        return myCommands;

    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    private void addPatterns(String syntax) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    private String getSymbol(String text) {
        final var ERROR = "NO MATCH";
        for (var e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        // FIXME: perhaps throw an exception instead
        return ERROR;
    }

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match(String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }


    public static void main(String[] args) {
        //TESTING
        var parser = new Interpreter();
        String WHITESPACE = "\\s+";
        String[] examples = {
                "",
                "# foo",
                "foo #",
                "#",
                "fd",
                "FD",
                "forwardd",
                "equalp",
                "equal?",
                "equal??",
                "+",
                "SuM",
                "-",
                "*",
                "/",
                "%",
                "~",
                "+not",
                "not+",
                "++",
                "+*+",
                "or",
                "FOR",
                "allOrNothing",
                "all_or_nothing",
                "allOr_nothing?",
                "allOr?nothing_",
                ":allornothing",
                "PI",
                "90",
                "9.09",
                "9.0.0",
                "[",
                "]",
                "(",
                ")"
        };

        // try against different inputs
        // note, this simple "algorithm" will not handle SLogo comments
        //parser.parse(examples);
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        parser.parse(userInput.split(WHITESPACE));
    }
}