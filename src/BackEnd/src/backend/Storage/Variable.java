package backend.Storage;

public class Variable {

    private Object value;
    private String name;

    public Variable(String name, Object object){
        this.value = object;
        this.name = name;
    }

    public Object getValue() {
        return value;
    }
}
