package compiler;

import compiler.Token.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lexer {
    private File file;
    private String contents;
    private String buf;
    private int index;
    private ArrayList<Token> tokens;

    public Lexer() {
        tokens = new ArrayList<>();
    }

    public boolean loadFile(String fileName) {
        if (fileName == null) {
            return false;
        }
        file = new File(fileName);

        try {
            Scanner scan = new Scanner(file);
            StringBuilder sb = new StringBuilder(scan.nextLine());
            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
            }
            contents = sb.toString();
            scan.close();
            return true;
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean tokenize() {
        if (contents == null)
        {
            return false;
        }
        StringBuilder buf = new StringBuilder();
        while (index < contents.length()) {
            if (Character.isAlphabetic((char) peek())) {
                buf.append((char) consume());
                while (peek() != -1 && (Character.isAlphabetic((char) peek()) || Character.isDigit((char) peek()))) {
                    buf.append((char) consume());
                }
                switch (buf.toString()) {
                    case "fun":
                        tokens.add(new TokenGen(TokenType.FUN));
                        break;
                    case "return":
                        tokens.add(new TokenGen(TokenType.RETURN));
                        break;
                    case "let":
                        tokens.add(new TokenGen(TokenType.LET));
                        break;
                    case "exit":
                        tokens.add(new TokenGen(TokenType.EXIT));
                        break;
                    default:
                        tokens.add(new TokenVar(TokenType.NAME, buf.toString()));
                }
                buf.setLength(0);
            } else if (Character.isDigit((char) peek())) {
                buf.append((char) consume());
                while (peek() != -1 && Character.isDigit((char) peek())) {
                    buf.append((char) consume());
                }
                tokens.add(new TokenVar(TokenType.INT, buf.toString()));
            } else if ((char) peek() == '(') {
                tokens.add(new TokenGen(TokenType.OPEN_PAREN));
                consume();
            } else if ((char) peek() == ')') {
                tokens.add(new TokenGen(TokenType.CLOSE_PARAN));
                consume();
            } else if ((char) peek() == '{') {
                tokens.add(new TokenGen(TokenType.OPEN_CURLY));
                consume();
            } else if ((char) peek() == '}') {
                tokens.add(new TokenGen(TokenType.CLOSE_CURLY));
                consume();
            } else if ((char) peek() == '+') {
                tokens.add(new TokenGen(TokenType.PLUS));
                consume();
            } else if ((char) peek() == '-') {
                tokens.add(new TokenGen(TokenType.MINUS));
                consume();
            } else if ((char) peek() == '*') {
                tokens.add(new TokenGen(TokenType.ASTERISK));
                consume();
            } else if ((char) peek() == '/') {
                tokens.add(new TokenGen(TokenType.F_SLASH));
                consume();
            } else if ((char) peek() == '=') {
                tokens.add(new TokenGen(TokenType.EQUALS));
                consume();
            } else if ((char) peek() == '!') {
                tokens.add(new TokenGen(TokenType.NOT));
                consume();
            } else if ((char) peek() == ';') {
                tokens.add(new TokenGen(TokenType.SEMI));
                consume();
            } else {
                consume();
            }
            buf.setLength(0);
        }

        return true;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    private int consume() {
        if (index >= contents.length()) {
            return -1;
        }
        return contents.charAt(index++);
    }

    private int peek() {
        if (index >= contents.length()) {
            return - 1;
        }
        return contents.charAt(index);
    }
}
