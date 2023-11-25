package compiler.Node;

import compiler.Token.Token;
import compiler.Token.TokenVar;

public class ExprInt implements ExprVar {
    public Token INT;

    public ExprInt(TokenVar tk) {
        INT = tk;
    }
}
