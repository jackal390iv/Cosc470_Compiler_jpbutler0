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
public class TheChecker {

    private static ArrayList<String> elements;

    public TheChecker() {
        elements = new ArrayList<>();
    }

    public static void checkBasicSyntax(String code, String location) {
        String temp = "\\";
        try {
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
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public static void combineStringLiterals() {
        String temp = "", first = "";
        int counter = 0, checker = 0, spaces = 0;
        boolean found = false;
        for (int i = 0; i < ScanCode.getList().size(); i++) {
            found = false;
            if (ScanCode.getList().get(i).startsWith("'")) {
                counter = 0;
                while ((counter < ScanCode.getList().size()) && (!(ScanCode.getList().get(i + counter).endsWith("'")))) {
                    counter++;
                }
                if (ScanCode.getList().get(i + counter).endsWith("'")) {
                    found = true;
                    checker = 0;
                    first = ScanCode.getList().get(i);
                    while (checker <= counter) {
                        if (ScanCode.getList().get(i + checker).isEmpty()) {
                            temp = temp + " ";
                            spaces++;
                        } else {
                            temp = temp + ScanCode.getList().get(i + checker);
                        }
                        checker++;
                    }
                }
            }
            if (found == true) {
                for (int k = 0; k < ScanCode.getCode().size(); k++) {
                    if (ScanCode.getCode().get(k).equals(first)) {
                        checker = 0;
                        while (checker <= counter - spaces) {
                            ScanCode.getCode().remove(k);
                            checker++;
                        }
                        ScanCode.getCode().add(k, temp);
                        break;
                    }
                }
            }
        }
    }

    public static String defineUnknown(String code) {
        String grammarId = "identifier";
        try {
            if ((code.startsWith("'")) && (code.endsWith("'"))) {
                if (code.length() == 3) {
                    grammarId = "'c'";
                } else {
                    grammarId = "'string_literal'";
                }
            } else if (code.matches("-?\\d+(\\.\\d+)?")) {
                grammarId = "num";
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
        return grammarId;
    }

    public static void checkIdentifiers() {
        String testing;
        boolean declareBlock = false, found = false, alreadyInUse = false;
        try {
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
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public static void setSizeDefaults() {
        int defaultSize = 0;
        try {
            for (int i = 0; i < TheCollector.getTokens().size(); i++) {
                if ((TheCollector.getTokens().get(i).getGrammarId().equals("VARCHAR2")) || (TheCollector.getTokens().get(i).getGrammarId().equals("NUMBER")) || (TheCollector.getTokens().get(i).getGrammarId().equals("SMALLINT")) || (TheCollector.getTokens().get(i).getGrammarId().equals("POSITIVE"))) {
                    switch (TheCollector.getTokens().get(i).getGrammarId()) {
                        case "VARCHAR2":
                            defaultSize = 6;
                            break;
                        case "NUMBER":
                            defaultSize = 3;
                            break;
                        case "SMALLINT":
                            defaultSize = 3;
                            break;
                        case "POSITIVE":
                            defaultSize = 3;
                            break;
                    }
                    if (!(TheCollector.getTokens().get(i + 1).getGrammarId().equals("("))) {
                        if (TheCollector.getTokens().get(i + 1).getGrammarId().equals(":=")) {
                            if ((TheCollector.getTokens().get(i + 2).getGrammarId().equals("num")) || (TheCollector.getTokens().get(i + 2).getGrammarId().equals("'string_literal'"))) {
                                defaultSize = TheCollector.getTokens().get(i + 2).getValue().length();
                                //System.out.println("Default Size: " + defaultSize + " Value: " + TheCollector.getTokens().get(i + 2).getValue());
                                TheCollector.getTokens().add(i + 1, new Token(")", ")"));
                                TheCollector.getTokens().add(i + 1, new Token("num", Integer.toString(defaultSize)));
                                TheCollector.getTokens().add(i + 1, new Token("(", "("));
                            } else {
                                //System.out.println("Default Size: " + defaultSize + " Value: " + TheCollector.getTokens().get(i + 2).getValue());
                                TheCollector.getTokens().add(i + 1, new Token(")", ")"));
                                TheCollector.getTokens().add(i + 1, new Token("num", Integer.toString(defaultSize)));
                                TheCollector.getTokens().add(i + 1, new Token("(", "("));
                            }
                        } else {
                            //System.out.println("Default Size: " + defaultSize + " Value: " + TheCollector.getTokens().get(i + 1).getValue());
                            TheCollector.getTokens().add(i + 1, new Token(")", ")"));
                            TheCollector.getTokens().add(i + 1, new Token("num", Integer.toString(defaultSize)));
                            TheCollector.getTokens().add(i + 1, new Token("(", "("));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

}
