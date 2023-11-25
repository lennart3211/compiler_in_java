package compiler.Token;

public class TokenVar extends Token {
    protected String value;

    public TokenVar(TokenType type, String value) {
        super(type);
        this.value = value;
    }

    public String value() {
        return value;
    }
}
