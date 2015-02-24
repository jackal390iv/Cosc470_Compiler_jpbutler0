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

    private static ArrayList<String> elements;

    public Changer() {
        elements = new ArrayList<String>();
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

    public static void checkIdentifiers() {
        String testing;
        boolean declareBlock = false, found = false, alreadyInUse = false;
        for (int i = 0; i < TheCollector.getTokens().size(); i++) {
            if (TheCollector.getTokens().get(i).getGrammarId().equals("DECLARE")) {
                declareBlock = true;
            } else if (TheCollector.getTokens().get(i).getGrammarId().equals("BEGIN")) {
                declareBlock = false;
            } else if (TheCollector.getTokens().get(i).getGrammarId().equals("identifier")) {
                //System.out.println("Declare Block Active? " + declareBlock+"\n"+TheCollector.getTokens().get(i).getValue()+"\n");
                if (!((TheCollector.getTokens().get(i).getValue().charAt(0) >= 'a') && (TheCollector.getTokens().get(i).getValue().charAt(0) <= 'z'))) {
                    System.out.printf("\nERROR\nThe Following Identifier Was Declared Inappropriately: %s", TheCollector.getTokens().get(i).getValue());
                    System.exit(0);
                } else if (TheCollector.getTokens().get(i).getValue().length() > 30) {
                    System.out.printf("\nERROR\nThe Following Identifier Was Too Large: %s", TheCollector.getTokens().get(i).getValue());
                    System.exit(0);
                }
                testing = TheCollector.getTokens().get(i).getValue().replaceAll("[^0-9a-z$_#]", "").trim();
                if (testing.length() != TheCollector.getTokens().get(i).getValue().length()) {
                    System.out.printf("\nERROR\nThe Following Identifier Was Declared With Inappropriate Characters: %s", TheCollector.getTokens().get(i).getValue());
                    System.exit(0);
                }
                if (declareBlock == true) {
                    for (int k = 0; k < elements.size(); k++) {
                        if (elements.get(k).equals(TheCollector.getTokens().get(i).getValue())) {
                            alreadyInUse = true;
                        }
                    }
                    if (alreadyInUse == true) {
                        System.out.printf("\nERROR\nThe Following Identifier Was Declared More Than Once: %s", TheCollector.getTokens().get(i).getValue());
                        System.exit(0);
                    } else {
                        elements.add(TheCollector.getTokens().get(i).getValue());
                    }
                }
                if (declareBlock == false) {
                    for (int k = 0; k < elements.size(); k++) {
                        if (elements.get(k).equals(TheCollector.getTokens().get(i).getValue())) {
                            //System.out.println(elements.get(k) + "\n" + TheCollector.getTokens().get(i).getValue() + "\n");
                            found = true;
                        }
                    }
                    if (found == false) {
                        System.out.printf("\nERROR\nThe Following Identifier Was Not Declared: %s", TheCollector.getTokens().get(i).getValue());
                        System.exit(0);
                    }
                }

            }
        }
    }

    public static void checkNumVariableTypes() {

    }

}
