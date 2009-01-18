package annabot;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import annabot.antlr.GrammarLexer;
import annabot.antlr.GrammarParser;
import annabot.antlr.TreeWalker;

public class AnnaBot {
	
    public static void main(String[] args) throws Exception {
        // Build the tree.
    	ANTLRInputStream input = new ANTLRInputStream(System.in);
        GrammarLexer lexer = new GrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.program_return r = parser.program();

        // Walk resulting tree once, for now.
        // XXX have to walk once per class being tested.
        CommonTree t = (CommonTree)r.getTree();
        CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
        TreeWalker walker = new TreeWalker(nodes);
        walker.program();
    }
}

