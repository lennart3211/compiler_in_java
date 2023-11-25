package compiler;

import compiler.Token.Token;
import compiler.Node.Exit;
import compiler.Node.Expr;
import compiler.Node.ExprInt;
import compiler.Token.TokenType;
import compiler.Token.TokenVar;

import java.util.ArrayList;

public class Parser {
    private final ArrayList<Token> tokens;
    private int index;
    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Expr parse_expr() {
        if (peek() != null && peek().type() == TokenType.INT) {
            return new Expr(new ExprInt((TokenVar) consume()));
        }
        return null;
    }

    public Exit parse() {
        Exit exit_node = null;

        // Go through tokens
        while (peek() != null) {
            if (peek().type() == TokenType.EXIT) {

                // Consume "exit"
                consume();

                // Consume '('
                if (peek() == null || peek().type() != TokenType.OPEN_PAREN) {
                    System.err.println("Expected '(");
                    return null;
                } else {
                    consume();
                }

                // parse expression
                Expr node_expr = parse_expr();

                // Create exit node
                if (node_expr != null) {
                    exit_node = new Exit(node_expr);
                } else {
                    System.err.println("Invalid expression");
                    return null;
                }

                // Consume ')'
                if (peek() == null || peek().type() != TokenType.CLOSE_PARAN) {
                    System.err.println("Expected ')");
                    return null;
                } else {
                    consume();
                }

                // Consume ';'
                if (peek() == null || peek().type() != TokenType.SEMI) {
                    System.err.println("Expected ';");
                    return null;
                } else {
                    consume();
                }
            }
        }
        index = 0;
        return exit_node;
    }

    private Token peek() {
        if (index == tokens.size()) {
            return null;
        }
        return tokens.get(index);
    }

    private Token consume() {
        if (index == tokens.size()) {
            return null;
        }
        return tokens.get(index++);
    }
}
