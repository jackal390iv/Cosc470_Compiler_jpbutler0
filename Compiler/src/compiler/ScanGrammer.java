/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads in a grammar text file that has been built around very
 * specific rules that are as followed. 1- All grammar elements are separated as
 * much as possible in order to avoid unnecessary and confusing repetition 2-
 * All grammar elements are listed on separate lines in the following format:
 * {grammar_element}-}|{child_1 of child_batch_1}{child_2 of
 * child_batch_1}|{child_1 of child_batch_2} To elaborate, the line must start
 * with the '{grammar_element}' which holds the grammar Id (such as
 * 'BOOLEAN','identifier',etc.). The '|' designate optional child batches for
 * example a 'factor' can be either 'TRUE','FALSE','NOT', or 'NULL'. The
 * elements inside of the child batches, such as '{child_1 of child_batch_1}',
 * are all of the elements required by the child batch in order for that batch
 * to exist. For example, 'term' requires a 'mulop' (being '%','/', or '*')
 * followed by a 'factor' (being 'TRUE','FALSE','NOT', or 'NULL'); both 'mulop'
 * and 'factor' must exist and in the correct order in order for 'term' to
 * exist.
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class ScanGrammer {

    private Scanner scanner;
    private String grammarTextLocation;
    private static ArrayList<String> reserve;

    public ScanGrammer(String grammarTextLocation) {
        reserve = new ArrayList<String>();
        this.grammarTextLocation = grammarTextLocation;
        readGrammer();
        seperateGrammer();
        buildReserveWordList();
    }

    /**
     * This method reads in a grammar text file and creates a 'GrammerNode' for
     * each grammar element within 'GrammerCollection'. While doing so this
     * method add each grammar element line temporarily into the 'reserve'
     * ArrayList. The temporary 'reserve' list created will then be run through
     * the 'separateGrammer' method.
     *
     */
    private void readGrammer() {
        try {
            scanner = new Scanner(new File(grammarTextLocation));
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

    /**
     * This method breaks down the each grammar element line, creating grammar
     * node connections to all possible parents and children for each grammar
     * element.
     *
     */
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

    /**
     * This method adds all grammar reserve words to the 'reserve' ArrayList.
     *
     */
    private void buildReserveWordList() {
        GrammerNode placeHolder = GrammerCollection.getNode("symbol");
        for (int i = 0; i < placeHolder.getParentCount(); i++) {
            reserve.add(placeHolder.getParent(i).getGrammerId());
        }
        //End-line symbol must be placed at the bottom of the reserve word list; 
        //along with rearanging any reserve words that require presedence of other reserve words
        reserve.remove(";");
        reserve.add(";");
    }

    /**
     * This method prints all grammar reserve words that are held in the
     * 'reserve' ArrayList.
     *
     */
    public static void printReserveWords() {
        for (String temp : reserve) {
            System.out.println(temp);
        }
    }

    /**
     * This method returns the ArrayList 'reserve' which contains all reserve
     * words as designated by the grammar.
     *
     * @return
     */
    public static ArrayList<String> getReserveWords() {
        return reserve;
    }
}
