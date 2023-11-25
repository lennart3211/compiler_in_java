package compiler;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Wrong usage\nCorrect usage:\njava Compiler <file.l>");
            return;
        }
        Lexer lex = new Lexer();
        if (!lex.loadFile(args[0])) {
            System.out.println("Failed to load file");
            return;
        }
        if (!lex.tokenize()) {
            System.out.println("Failed to tokenize");
            return;
        }
        Parser parser = new Parser(lex.getTokens());
        Generator generator = new Generator(parser.parse());
        String output = generator.generate();
        System.out.println(output);
        try {
            FileWriter out = new FileWriter("out.asm");
            out.write(output);
        } catch (IOException e) {
            System.err.println(e);
        }

    }

}