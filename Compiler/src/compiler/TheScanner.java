/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

import java.io.FileReader;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class TheScanner {

    private FileReader reader = null;

    public TheScanner() {

    }

    public void scanCode() {
        try {
            reader = new FileReader("./editedFiles/code.txt");
            int val;
            while ((val = reader.read()) != -1) {

                System.out.println((char) val);
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
}
