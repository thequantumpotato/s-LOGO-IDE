package backend.Commands;

public class Number extends LeafNode {

    private Double value;

    public Number(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public Object run() {
        return value;
    }
}
