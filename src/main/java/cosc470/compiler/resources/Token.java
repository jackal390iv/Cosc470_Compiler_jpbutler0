/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package cosc470.compiler.resources;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class Token {
    
    private String grammarId, value;
    
    public Token(String grammarId, String value){
        this.grammarId=grammarId;
        this.value=value;
    }
    
    public String getGrammarId(){
        return grammarId;
    }
    
    public String getValue(){
        return value;
    }
    
    public void printIdAndValue(){
        System.out.printf("\nValue: %-20sGrammar Id: %s", value,grammarId);
    }
}
