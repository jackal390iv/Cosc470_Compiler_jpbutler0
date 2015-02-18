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
public class ScanCode {

    private Scanner scanner;
    private static ArrayList<String> list;

    public ScanCode() {
        list = new ArrayList<String>();
        readCode();
        fixSpecialCharacters();
    }

    private void readCode() {
        try {
            scanner = new Scanner(new File("./editedFiles/codeV1.txt"));
            String holder, line;
            boolean multiLineComment = false;
            String[] checker;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (multiLineComment == true) {
                    if (line.contains("*/")) {
                        line = line.substring(line.indexOf("*/") + 2, line.length());
                        multiLineComment = false;
                    } else {
                        line = "";
                    }
                }
                if (line.contains("--")) {
                    line = line.substring(0, line.indexOf("--"));
                }
                if (line.contains("/*")) {
                    line = line.substring(0, line.indexOf("/*"));
                    multiLineComment = true;
                }
                checker = line.split(" ");
                for (String element : checker) {
                    element = element.trim();
                    if (!(element.equals(""))) {
                        holder = element;
                        for (int i = 0; i < element.length(); i++) {
                            for (String temp : ScanGrammer.getReserveWords()) {
                                if (holder.contains(temp)) {
                                    if (!(holder.substring(0, holder.indexOf(temp)).isEmpty())) {
                                        //System.out.println("Before Cut: "+holder.substring(0, holder.indexOf(temp)));
                                        list.add(holder.substring(0, holder.indexOf(temp)));
                                    }
                                    //System.out.println("After Cut: "+temp);
                                    list.add(temp);
                                    holder = holder.substring(holder.indexOf(temp) + temp.length());
                                }
                            }
                        }
                        if (holder.length() > 0) {
                            //System.out.println("Holder: "+holder);
                            list.add(holder);
                        }
                    }
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

    private void fixSpecialCharacters() {
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 2) {
                if (list.get(i).equals("VAR")) {
                    if (list.get(i + 1).equals("CHAR")) {
                        if (list.get(i + 2).equals("2")) {
                            list.remove(i);
                            list.remove(i);
                            list.remove(i);
                            list.add(i, "VARCHAR2");
                        }
                    }
                }
                if (list.get(i).equals("END")) {
                    if (list.get(i + 1).equals("IF")) {
                        if (list.get(i + 2).equals(";")) {
                            list.remove(i);
                            list.remove(i);
                            list.remove(i);
                            list.add(i, "END IF;");
                        }
                    }
                }
                if (list.get(i).equals("END")) {
                    if (list.get(i + 1).equals("LOOP")) {
                        if (list.get(i + 2).equals(";")) {
                            list.remove(i);
                            list.remove(i);
                            list.remove(i);
                            list.add(i, "END LOOP;");
                        }
                    }
                }
            }

            if (i < list.size() - 1) {
                if (list.get(i).equals("DBMS_OUTPUT.NEW_LINE")) {
                    if (list.get(i + 1).equals(";")) {
                        list.remove(i);
                        list.remove(i);
                        list.add(i, "DBMS_OUTPUT.NEW_LINE;");
                    }
                }
                if (list.get(i).equals("IF")) {
                    if (list.get(i + 1).equals("BEGIN")) {
                        list.remove(i);
                        list.remove(i);
                        list.add(i, "IF BEGIN");
                    }
                }
                if (list.get(i).equals(">")) {
                    if (list.get(i + 1).equals("=")) {
                        list.remove(i);
                        list.remove(i);
                        list.add(i, ">=");
                    }
                }
                if (list.get(i).equals("<")) {
                    if (list.get(i + 1).equals("=")) {
                        list.remove(i);
                        list.remove(i);
                        list.add(i, "<=");
                    }
                }
                if (list.get(i).equals("<")) {
                    if (list.get(i + 1).equals(">")) {
                        list.remove(i);
                        list.remove(i);
                        list.add(i, "<>");
                    }
                }
            }
        }
    }

    public static void printCodeList() {
        for (String temp : list) {
            System.out.println(temp);
        }
    }

    public static ArrayList<String> getCodeList() {
        return list;
    }
}
