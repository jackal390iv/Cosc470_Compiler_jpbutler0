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
public class Changer {

    public Changer() {

    }

    public static String defineUnknown(String code) {
        String grammarId = "ERROR";
        if ((code.startsWith("'")) && (code.endsWith("'"))) {
            if (code.length() == 3) {
                grammarId = "'c'";
            } else {
                grammarId = "'string_literal'";
            }
        } else if (code.matches("-?\\d+(\\.\\d+)?")) {
            grammarId = "num";
        } else if (code.equals(code.toLowerCase())) {
            grammarId = "identifier";
        }
        return grammarId;
    }

    public static void checkBasicSyntax(String code, String location) {
        String temp = "\\";
        if (location.equals("START")) {
            if (!((code.equals("DECLARE")) || (code.equals("BEGIN")))) {
                System.out.printf("\nERROR\nThe Following Syntax Errors Have Occured: Program Must Start With Either 'DECLARE' or 'BEGIN'");
                System.exit(0);
            }
        } else if (location.equals("END_Minus_1")) {
            if (!(code.equals("END;"))) {
                System.out.printf("\nERROR\nThe Following Syntax Errors Have Occured: Program Must End With 'END;' followed by '%s'", temp.charAt(0));
                System.exit(0);
            }
        } else if (location.equals("END")) {
            if (!(code.equals("\\"))) {
                System.out.printf("\nERROR\nThe Following Syntax Errors Have Occured: Program Must End With 'END;' followed by '%s'", temp.charAt(0));
                System.exit(0);
            }
        }
    }
}
