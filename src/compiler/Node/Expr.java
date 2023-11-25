package compiler.Node;

public class Expr implements Node {
    public ExprVar var;

    public Expr (ExprVar var) {
        this.var = var;
    }
}
