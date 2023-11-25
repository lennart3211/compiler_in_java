package compiler;

import compiler.Token.TokenVar;
import compiler.Node.Exit;
import compiler.Node.ExprInt;

public class Generator {
    private final Exit root;

    public Generator(Exit root) {
        if (root == null) {
            System.err.println("what");
        }
        this.root = root;
    }

    public String generate() {
        String output = "global _start\n_start:\n";

        output += "\tmov rax, 60\n";
        output += "\tmov rdi, " + ((TokenVar) ((ExprInt) root.expr.var).INT).value() + "\n";
        output += "\tsyscall";
        return output;
    }
}
