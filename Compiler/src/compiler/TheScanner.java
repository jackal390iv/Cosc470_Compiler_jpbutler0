/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class TheScanner {

    private FileReader reader = null;
    private ArrayList<Token> tokens = new ArrayList<Token>();
    private ArrayList<Ids> ids = new ArrayList<Ids>();

    public TheScanner() {

    }

    public void runScanner() {
        scanIds();
        scanCode();
        printTokens();
    }

    private void scanIds() {
        ids.add(new Ids("if", true));
        ids.add(new Ids("(", false));
        ids.add(new Ids("3", true));
    }

    private void scanCode() {
        try {
            reader = new FileReader("./editedFiles/code.txt");
            int element;
            String temp = "", tok = null;
            while ((element = reader.read()) != -1) {
                temp = temp + (char) element;
                tok = searchSymbolsList(temp);
                if (tok != null) {
                    temp = "";
                }
            }
            if (!(temp.equals(""))) {
                tokens.add(new Token(temp, "str", true));
            }
            reader.close();

        } catch (Exception ex) {
            System.out.println("\n" + "ERROR");
            System.out.println("Type: " + ex.getClass().getName());
            System.out.println("Location: " + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage() + "\n");
            //ex.printStackTrace();*/
        }
    }

    private String searchSymbolsList(String temp) {
        String tok = null;
        boolean val = false;
        for (Ids item : ids) {
            if (temp.contains(item.getName())) {
                tok = item.getName();
                val = item.getCombinable();
                tokens.add(new Token(temp.substring(0, temp.indexOf(tok)), "str", true));
                if ((tok.equals("0")) || (tok.equals("1")) || (tok.equals("2")) 
                        || (tok.equals("3")) || (tok.equals("4")) || (tok.equals("5")) 
                        || (tok.equals("6")) || (tok.equals("7")) || (tok.equals("8")) 
                        || (tok.equals("9"))) {
                    tokens.add(new Token(tok, "num", true));
                } else {
                    tokens.add(new Token(tok, "id", val));
                }
                break;
            }
        }

        return tok;
    }

    private void printTokens() {
        for (Token coin : tokens) {
            coin.printToken();
        }
    }
}
