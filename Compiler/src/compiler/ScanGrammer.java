/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class ScanGrammer {

    private Scanner scanner;
    private static ArrayList<String> reserve;

    public ScanGrammer() {
        reserve = new ArrayList<String>();
        readGrammer();
        seperateGrammer();
        buildReserveWordList();
    }

    private void readGrammer() {
        try {
            scanner = new Scanner(new File("./editedFiles/grammer.txt"));
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.startsWith("{")) {
                    new GrammerNode(line.substring(1, line.indexOf("}")).trim());
                    reserve.add(line);
                }
            }
            scanner.close();
        } catch (Exception ex) {
            System.out.println("\n" + "ERROR");
            System.out.println("Type: " + ex.getClass().getName());
            System.out.println("Location: " + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage() + "\n");
            //ex.printStackTrace();
        }
    }

    private void seperateGrammer() {
        GrammerNode placeHolder;
        String temp, element;
        try {
            for (String line : reserve) {
                placeHolder = GrammerCollection.getNode(line.substring(1, line.indexOf("}")).trim());
                if (line.contains("|")) {
                    line = line.substring(line.indexOf("|"));
                    while (line.contains("|")) {
                        temp = line.substring(1);
                        placeHolder.newChildBatch();
                        if (temp.contains("|")) {
                            temp = temp.substring(0, temp.indexOf("|"));
                        }
                        while (temp.contains("}")) {
                            element = temp.substring(temp.indexOf("{") + 1, temp.indexOf("}")).trim();
                            temp = temp.substring(temp.indexOf("}") + 1);
                            placeHolder.addToChildBatch(element);
                        }
                        line = line.substring(1);
                        if (line.contains("|")) {
                            line = line.substring(line.indexOf("|"));
                        }

                    }
                }
            }
            reserve.clear();
        } catch (Exception ex) {
            System.out.println("\n" + "ERROR");
            System.out.println("Type: " + ex.getClass().getName());
            System.out.println("Location: " + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage() + "\n");
            //ex.printStackTrace();
        }
    }

    private void buildReserveWordList() {
        GrammerNode placeHolder = GrammerCollection.getNode("symbol");
        for (int i = 0; i < placeHolder.getParentCount(); i++) {
            reserve.add(placeHolder.getParent(i).getGrammerId());
        }
    }

    public static void printReserveWords() {
        for (String temp : reserve) {
            System.out.println(temp);
        }
    }

    public static ArrayList<String> getReserveWords() {
        return reserve;
    }
}
