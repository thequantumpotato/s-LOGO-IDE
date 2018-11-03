package backend;

public class IllegalCommandException extends Exception {

    /**
     * @Author Jose San Martin (js665)
     * Create an exception based on an issue in our code.
     */
    public IllegalCommandException(String message, Object... values) {
        super(String.format(message, values));
    }

    public IllegalCommandException(Object... values) {

        super(String.format("ERROR", values));
    }
}
