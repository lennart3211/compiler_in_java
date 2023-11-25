package compiler.Token;

public class TokenVar implements Token {
    private String value;
    private TokenType type;

    public TokenVar(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String value() {
        return value;
    }

    public TokenType type() {
        return type;
    }
}
