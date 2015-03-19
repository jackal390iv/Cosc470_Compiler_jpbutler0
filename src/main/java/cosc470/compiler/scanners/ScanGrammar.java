/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package cosc470.compiler.scanners;

import cosc470.compiler.resources.GrammarNode;
import cosc470.compiler.database.TheCollector;
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
public class ScanGrammar {

    private Scanner scanner;
    private String grammarTextLocation;
    private ArrayList<String> grammarLines;

    public ScanGrammar(String grammarTextLocation) {
        this.grammarTextLocation = grammarTextLocation;
        grammarLines = new ArrayList<String>();
        readGrammar();
        seperateGrammar();
        buildCombinableWordList();
        buildReserveWordsMinusCombinables();
    }

    /**
     * This method reads in a grammar text file and creates a 'GrammarNode' for
     * each grammar element within 'GrammarCollection'. While doing so this
     * method add each grammar element line temporarily into the 'reserve'
     * ArrayList. The temporary 'reserve' list created will then be run through
     * the 'separateGrammar' method.
     *
     */
    private void readGrammar() {
        String line, temp;
        try {
            scanner = new Scanner(new File(grammarTextLocation));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.startsWith("{")) {
                    temp = line.substring(1, line.indexOf("}")).trim();
                    if (temp.equals("COMMENT_SYMBOL")) {
                        temp = line.substring(line.indexOf("|") + 2);
                        temp = temp.substring(0, temp.indexOf("}")).trim();
                        TheCollector.setCommentSymbol(temp);
                    } else if (temp.equals("COMMENT_BLOCK_START_SYMBOL")) {
                        temp = line.substring(line.indexOf("|") + 2);
                        temp = temp.substring(0, temp.indexOf("}")).trim();
                        TheCollector.setCommentBlockStartSymbol(temp);
                    } else if (temp.equals("COMMENT_BLOCK_END_SYMBOL")) {
                        temp = line.substring(line.indexOf("|") + 2);
                        temp = temp.substring(0, temp.indexOf("}")).trim();
                        TheCollector.setCommentBlockEndSymbol(temp);
                    } else {
                        new GrammarNode(temp);
                        grammarLines.add(line);
                    }
                }
            }
            scanner.close();
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * This method breaks down the each grammar element line, creating grammar
     * node connections to all possible parents and children for each grammar
     * element.
     *
     */
    private void seperateGrammar() {
        GrammarNode placeHolder;
        String temp, element;
        try {
            for (String line : grammarLines) {
                placeHolder = TheCollector.getGrammarNode(line.substring(1, line.indexOf("}")).trim());
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
            grammarLines.clear();
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    private void buildCombinableWordList() {
        try {
            for (String tester : TheCollector.getReserveWords()) {
                for (String checker : TheCollector.getReserveWords()) {
                    if (((tester.contains(checker)) && (!(tester.equals(checker)))) || (tester.contains(" "))) {
                        TheCollector.addCombinable(tester);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public void buildReserveWordsMinusCombinables() {
        boolean exists;
        try {
            for (String temp : TheCollector.getReserveWords()) {
                exists = false;
                for (String Rcombinables : TheCollector.getCombinables()) {
                    if (temp.equals(Rcombinables)) {
                        exists = true;
                        break;
                    }
                }
                for (String Scombinables : TheCollector.getCombinables()) {
                    if (temp.equals(Scombinables)) {
                        exists = true;
                        break;
                    }
                }
                if (exists != true) {
                    TheCollector.addReserveWordMinusCombinables(temp);
                }
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }
}
