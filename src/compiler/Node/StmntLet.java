package compiler.Node;

import compiler.Token.Token;

public class StmntLet implements Stmnt {
    public Token IDENT;
    public Expr expr;
}
