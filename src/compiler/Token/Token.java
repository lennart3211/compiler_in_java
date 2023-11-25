package compiler.Token;

public class Token {
    protected TokenType type;

    public Token(TokenType type) {
        this.type = type;
    }

    public TokenType type() {
        return type;
    }
}
