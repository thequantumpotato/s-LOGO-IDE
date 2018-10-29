package backend.Commands;

public class Text extends LeafNode {

    private String value;

    public Text(String value) {
        this.value = value;
    }

    @Override
    public Object run() {
        return value;
    }
}
