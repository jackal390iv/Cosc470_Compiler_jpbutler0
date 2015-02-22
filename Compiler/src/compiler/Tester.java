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
        ScanGrammar scanGrammar = new ScanGrammar("./editedFiles/grammar.txt");       
        ScanCode scanCode = new ScanCode("./editedFiles/codeReadTester.txt");
        //ParseCode parseCode = new ParseCode();
    }
}
