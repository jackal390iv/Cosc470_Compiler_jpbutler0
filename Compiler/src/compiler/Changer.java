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
}
