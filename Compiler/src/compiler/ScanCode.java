/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

import java.io.File;
import java.util.Scanner;

/**
 * This class is responsible for reading in the code text file, separating the
 * code text according to the grammar, and placing the separated code elements
 * into into a static ArrayList.
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class ScanCode {

    private Scanner scanner;
    private final String codeTextLocation;

    public ScanCode(String codeTextLocation) {
        this.codeTextLocation = codeTextLocation;
        readCode();
        seperateSpaces();
        checkForBasicReserveWords();
        checkForCombinables();
        removeEmptyElements();
    }

    /**
     * This method reads the code text, who's location is designated in the
     * constructor, and adds its elements to the ArrayList 'list'. It should be
     * noted that until the 'fixSpecialCharacters()' method has been run this
     * list is not yet representative of the grammar.
     *
     */
    private void readCode() {
        String line;
        boolean multiLineComment = false;
        try {
            scanner = new Scanner(new File(codeTextLocation));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                //This section of this method checks for pre-existing multi-Line comments and removes them; designated in our current grammar as '/*' to '*/'
                if (multiLineComment == true) {
                    if (line.contains(TheCollector.getCommentBlockEndSymbol())) {
                        line = line.substring(line.indexOf(TheCollector.getCommentBlockEndSymbol()) + TheCollector.getCommentBlockEndSymbol().length(), line.length());
                        multiLineComment = false;
                    } else {
                        line = "";
                    }
                }
                //This section of this method checks for line comments and removes them from the end of the line; designated in our current grammar as '--'
                if (line.contains(TheCollector.getCommentSymbol())) {
                    line = line.substring(0, line.indexOf(TheCollector.getCommentSymbol()));
                }
                //This section of this method checks for the start of multi-line comments and removes them; designated in our current grammar as '/*' to '*/'
                if (line.contains(TheCollector.getCommentBlockStartSymbol())) {
                    line = line.substring(0, line.indexOf(TheCollector.getCommentBlockStartSymbol()));
                    multiLineComment = true;
                }
                TheCollector.getCodeText().add(line.trim());
            }
            scanner.close();
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    private void seperateSpaces() {
        String element;
        try {
            for (int i = 0; i < TheCollector.getCodeText().size(); i++) {
                while ((TheCollector.getCodeText().get(i).contains(" ")) && (!(TheCollector.getCodeText().get(i).isEmpty()))) {
                    element = TheCollector.getCodeText().get(i);
                    TheCollector.getCodeText().remove(i);
                    TheCollector.getCodeText().add(i, element.substring(element.indexOf(" ") + 1));
                    TheCollector.getCodeText().add(i, "");
                    TheCollector.getCodeText().add(i, element.substring(0, element.indexOf(" ")));
                }
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    private void checkForBasicReserveWords() {
        String[] holder;
        try {
            for (int i = 0; i < TheCollector.getCodeText().size(); i++) {
                for (int k = 0; k < TheCollector.getReserveWordsMinusCombinables().size(); k++) {
                    if ((TheCollector.getCodeText().get(i).contains(TheCollector.getReserveWordsMinusCombinables().get(k))) && (!(TheCollector.getCodeText().get(i).equals(TheCollector.getReserveWordsMinusCombinables().get(k))))) {
                        holder = new String[]{TheCollector.getCodeText().get(i).substring(0, TheCollector.getCodeText().get(i).indexOf(TheCollector.getReserveWordsMinusCombinables().get(k))), TheCollector.getReserveWordsMinusCombinables().get(k), TheCollector.getCodeText().get(i).substring(TheCollector.getCodeText().get(i).indexOf(TheCollector.getReserveWordsMinusCombinables().get(k)) + TheCollector.getReserveWordsMinusCombinables().get(k).length(), TheCollector.getCodeText().get(i).length())};
                        TheCollector.getCodeText().remove(i);
                        for (int j = 2; j >= 0; j--) {
                            if (!(holder[j].isEmpty())) {
                                TheCollector.getCodeText().add(i, holder[j]);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    private void checkForCombinables() {
        String element;
        int counter, clicker;
        try {
            for (int i = 0; i < TheCollector.getCodeText().size(); i++) {
                for (int k = 0; k < TheCollector.getCombinables().size(); k++) {
                    counter = 0;
                    clicker = 0;
                    element = TheCollector.getCodeText().get(i);
                    while ((TheCollector.getCombinables().get(k).contains(element)) && ((i + counter) < TheCollector.getCodeText().size()) && (!(TheCollector.getCombinables().get(k).equals(element)))) {
                        counter++;
                        if (TheCollector.getCodeText().get(i + counter).isEmpty()) {
                            element = element + " ";
                        } else {
                            element = element + TheCollector.getCodeText().get(i + counter);
                        }
                    }
                    if (element.equals(TheCollector.getCombinables().get(k))) {
                        clicker = 0;
                        while (clicker != counter + 1) {
                            TheCollector.getCodeText().remove(i);
                            clicker++;
                        }
                        TheCollector.getCodeText().add(i, element);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    private void removeEmptyElements() {
        try {
            for (String temp : TheCollector.getCodeText()) {
                if (!(temp.isEmpty())) {
                    TheCollector.getCodeList().add(temp);
                }
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }
}
