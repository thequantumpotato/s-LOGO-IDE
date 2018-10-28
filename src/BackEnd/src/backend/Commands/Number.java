package backend.Commands;

public class Number extends LeafNode {

    private double value;

    public Number(String val) {
        this.value = Double.parseDouble(val);
        System.out.println("Got you");
    }

    @Override
    public Object run() {
        return value;
    }
}
