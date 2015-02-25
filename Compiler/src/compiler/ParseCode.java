/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

import java.util.ArrayList;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class ParseCode {
    
    private ArrayList<Token> tokens;
    
    public ParseCode(ArrayList<Token> tokens) {
        this.tokens = tokens;        
        //traverseGrammar();
    }
       
    private void traverseGrammar() {        
        int counter=0;
        GrammarNode grammarNode;
        
        grammarNode = TheCollector.getGrammarNode(tokens.get(0+counter).getGrammarId());
        
        
    }
}
