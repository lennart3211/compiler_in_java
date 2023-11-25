package compiler.Token;

public class TokenGen implements Token {
    private TokenType type;

    public TokenGen(TokenType type) {
        this.type = type;
    }

    public TokenType type() {
        return type;
    }
}
