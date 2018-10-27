package backend.Storage;

import backend.Command;

import java.util.List;

public class Instruction {

    private List<Command> ins;
    private String name;

    public Instruction(String name, List<Command> commands){
        this.name = name;
        this.ins = commands;
    }

    public List<Command> getInstruction() {
        return ins;
    }
}
