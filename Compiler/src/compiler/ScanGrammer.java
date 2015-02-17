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
                if (line.startsWith("<")) {
                    for (char c : line.toCharArray()) {
                        if (c == '|') {
                            childBatchCount++;
                        }
                    }
                    parentCount = Integer.parseInt(line.substring(1, line.indexOf(">")));
                    line = line.substring(line.indexOf(">") + 2);
                    element = (line.substring(0, line.indexOf(">")).trim());
                    if (element.contains("}")) {
                        element=element.replace("}", "<");
                    } if (element.contains("{")) {
                        element=element.replace("{", ">");
                    } if (element.contains("?")) {
                        element=element.replace("?", "-");
                    }
                    //System.out.println(element);
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
    }

    public void seperateGrammer() {
        GrammerNode placeHolder;
        String temp, element;
        try {
            for (String line : components) {
                placeHolder = GrammerCollection.getNode(line.substring(0, line.indexOf(">")).trim());
                if (line.contains("|")) {
                    line = line.substring(line.indexOf("|"));
                    while (line.contains("|")) {
                        placeHolder.newChildBatch();
                        temp = line.substring(1);
                        if (temp.contains("|")) {
                            temp = temp.substring(0, temp.indexOf("|"));
                        }
                        while (temp.contains(">")) {
                            element = temp.substring(temp.indexOf("<") + 1, temp.indexOf(">")).trim();
                            temp = temp.substring(temp.indexOf(">") + 1);
                            if (element.contains("}")) {
                                element=element.replace("}", "<");
                            } if (element.contains("{")) {
                                element=element.replace("{", ">");
                            } if (element.contains("?")) {
                                element=element.replace("?", "-");
                            }
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

    }
}
