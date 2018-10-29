package backend.Storage;

import backend.Commands.Node;

import java.util.List;

public class Instruction {

    private List<Node> ins;
    private String name;

    public Instruction(String name, List<Node> commands) {
        this.name = name;
        this.ins = commands;
    }

    public List<Node> getInstruction() {
        return ins;
    }
}
