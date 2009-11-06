package annabot;

import java.io.FileInputStream;
import java.io.PrintStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.stringtemplate.StringTemplate;

import annabot.antlr.GrammarLexer;
import annabot.antlr.GrammarParser;
import annabot.antlr.TreeWalker;

/** The AnnaBot Compiler - read a .claim file
 * and generate a compiled Java program (either
 * .java - a preprocessor - or .class, a true compiler).
 * At present it reads .claim files but doesn't output
 * the tree.
 */
public class AnnaBotC {
	
    private static boolean debug = false;

	public static void main(String[] args) throws Exception {
        // Build the tree.
    	ANTLRInputStream input;
    	if (args.length == 0) {
    		System.out.println("Reading stdin...");
			input = new ANTLRInputStream(System.in);
    	} else {
    		System.out.println("Process: " + args[0]);
			input = new ANTLRInputStream(new FileInputStream(args[0]));
    	}
    	GrammarLexer lexer = new GrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        GrammarParser.program_return r = parser.program();

        CommonTree t = (CommonTree)r.getTree();
        if (debug) {
        	DOTTreeGenerator gen = new DOTTreeGenerator();
        	StringTemplate st = gen.toDOT(t);
        	Process p = Runtime.getRuntime().exec("dot -T ps -o /tmp/dot.ps");
        	new PrintStream(p.getOutputStream()).print(st);
        	//final int result = p.waitFor();
        	//System.out.println("Debug printer return status " + result);
        }
        
        // Walk resulting tree once, for now.
        // XXX Don't walk Tree until TreeWalker is updated
        // and Parser generates a usable tree once more.
        // XXX have to walk once per class being tested.        
        // CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
        // TreeWalker walker = new TreeWalker(nodes);
        // walker.program();
    }
}

