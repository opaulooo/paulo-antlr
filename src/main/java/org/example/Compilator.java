package org.example;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class Compilator {
    private String entrada;

    public Compilator(String entrada) {
        this.entrada = entrada;
    }

    public void compile() {
        ANTLRInputStream input = new ANTLRInputStream(this.entrada);
        GrammarLexer lexer = new GrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        List<Token> tokenList = tokens.getTokens();
        for (Token token : tokenList) {
            int tokenType = token.getType();
            String tokenName = lexer.getVocabulary().getSymbolicName(tokenType);
            String tokenText = token.getText();
            int line = token.getLine();
            int column = token.getCharPositionInLine();
            System.out.println( "Token: " + tokenName +
                                "\nTexto: " + tokenText +
                                "\nl: " + line +
                                "\nc: " + column +
                                "\n\n");
        }
        GrammarParser parser = new GrammarParser(tokens);
        ParseTree tree = parser.program();
    }
}