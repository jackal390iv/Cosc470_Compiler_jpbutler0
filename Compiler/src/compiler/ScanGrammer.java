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
    private ArrayList<String> components = new ArrayList<String>();

    public ScanGrammer() {
        readGrammer();
        seperateGrammer();
        buildReserveWordArrayList();
        GrammerCollection.printNodes();
    }

    public void readGrammer() {
        try {
            scanner = new Scanner(new File("./editedFiles/grammer.txt"));
            String line, element;
            int childBatchCount = 0;
            int parentCount = 0;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.startsWith("{")) {
                    for (char c : line.toCharArray()) {
                        if (c == '|') {
                            childBatchCount++;
                        }
                    }
                    parentCount = Integer.parseInt(line.substring(1, line.indexOf("}")));
                    line = line.substring(line.indexOf("}") + 2);
                    element = (line.substring(0, line.indexOf("}")).trim());
                    new GrammerNode(element, parentCount, childBatchCount);
                    components.add(line);
                }
            }
        } catch (Exception ex) {
            System.out.println("\n" + "ERROR");
            System.out.println("Type: " + ex.getClass().getName());
            System.out.println("Location: " + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage() + "\n");
            //ex.printStackTrace();
        }
        scanner.close();
    }

    public void seperateGrammer() {
        GrammerNode placeHolder;
        String temp, element;
        try {
            for (String line : components) {
                placeHolder = GrammerCollection.getNode(line.substring(0, line.indexOf("}")).trim());
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

        } catch (Exception ex) {
            System.out.println("\n" + "ERROR");
            System.out.println("Type: " + ex.getClass().getName());
            System.out.println("Location: " + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage() + "\n");
            //ex.printStackTrace();
        }
        components.clear();
    }

    public void buildReserveWordArrayList() {
        GrammerNode placeHolder = GrammerCollection.getNode("symbol");
        for (int i = 0; i < placeHolder.getParentCount(); i++) {
            components.add(placeHolder.getParent(i).getGrammerId());
        }
        components.remove(";");
        components.add(";");
    }
}
