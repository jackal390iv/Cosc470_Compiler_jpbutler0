/*
 * Class: COSC 470-001 
 * Project: Compiler
 */
package cosc470.compiler.main;

import cosc470.compiler.syntaxcheckers.TheChecker;
import cosc470.compiler.database.TheCollector;
import cosc470.compiler.parser.ParseCode;
import cosc470.compiler.scanners.ScanGrammar;
import cosc470.compiler.scanners.ScanCode;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class Tester {

    public static void main(String[] args) {
        try {
            
        	
        	
        	/*TheCollector theCollector = new TheCollector();
            ScanGrammar scanGrammar = new ScanGrammar("./src/main/resources/grammar/grammarV1.txt");
            ScanCode scanCode = new ScanCode("./src/main/resources/code/codeV1.txt");
            TheChecker checker = new TheChecker();
            ParseCode parseCode = new ParseCode(); //*/

        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }
}
