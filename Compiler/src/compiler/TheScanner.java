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
    private ArrayList<String> ids = new ArrayList<>();

    public TheScanner() {

    }

    public void runScanner() {
        ids.add("if");

        scanCode();
        printTokens();
    }

    /**
     *
     */
    private void scanCode() {
        try {
            reader = new FileReader("./editedFiles/code.txt");
            int element;
            String temp = "", tok = null;
            while ((element = reader.read()) != -1) {
                temp = temp + (char) element;
                tok = searchSymbolsList(temp);
                if (tok != null) {
                    tokens.add(new Token(temp.substring(0, temp.indexOf(tok)), "str"));
                    tokens.add(new Token(tok, "id"));
                    temp = "";
                }
            }
            tokens.add(new Token(temp, "str"));
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

    /**
     *
     * @param temp
     * @return
     */
    private String searchSymbolsList(String temp) {
        String tok = null;
        for (String item : ids) {
            if (temp.contains(item)) {
                tok = item;
            }
        }
        return tok;
    }

    /**
     *
     */
    private void printTokens() {
        for (Token coin : tokens) {
            System.out.print(coin.printToken());
        }
    }
}
