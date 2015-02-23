/*
 * Class: COSC 470-001 
 * Project: Compiler
 */
package compiler;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class Tester {

    public static void main(String[] args) {
        TheCollector grammarCollection = new TheCollector();
        Changer changer= new Changer();
        ScanGrammar scanGrammar = new ScanGrammar("./editedFiles/grammar.txt"); 
        ScanCode scanCode = new ScanCode("./editedFiles/codeV1.txt");        
        ParseCode parseCode = new ParseCode();
    }
}
