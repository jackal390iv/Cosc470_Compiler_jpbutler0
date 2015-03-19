/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package cosc470.compiler.database;

import cosc470.compiler.resources.Token;
import cosc470.compiler.resources.GrammarNode;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 *
 */
public class TheCollector {

    private static HashMap<String, GrammarNode> grammarNodes;
    private static ArrayList<Token> tokens;
    private static ArrayList<String> reserveWords, unknownList, reserveWordsMinusCombinables, combinables, codeText, codeList;
    private static String commentSymbol, commentBlockStartSymbol, commentBlockEndSymbol;
    private static boolean endLineExists;

    public TheCollector() {
        grammarNodes = new HashMap<>();
        reserveWords = new ArrayList<>();
        unknownList = new ArrayList<>();
        reserveWordsMinusCombinables = new ArrayList<>();
        combinables = new ArrayList<>();
        codeText = new ArrayList<>();
        codeList = new ArrayList<>();
        tokens = new ArrayList<>();
        endLineExists = false;
    }

    /**
     * This method returns the ArrayList 'reserve' which contains all reserve
     * words as designated by the grammar.
     *
     * @return
     */
    public static ArrayList<String> getReserveWords() {
        return reserveWords;
    }

    public static ArrayList<String> getUnknownList() {
        return unknownList;
    }

    public static ArrayList<String> getReserveWordsMinusCombinables() {
        return reserveWordsMinusCombinables;
    }

    public static ArrayList<String> getCombinables() {
        return combinables;
    }

    public static ArrayList<String> getCodeText() {
        return codeText;
    }

    public static ArrayList<String> getCodeList() {
        return codeList;
    }

    public static ArrayList<Token> getTokens() {
        return tokens;
    }

    public static GrammarNode getGrammarNode(String grammarId) {
        return grammarNodes.get(grammarId); //returns null if it doesn't exist
    }

    public static String getCommentSymbol() {
        return commentSymbol;
    }

    public static String getCommentBlockStartSymbol() {
        return commentBlockStartSymbol;
    }

    public static String getCommentBlockEndSymbol() {
        return commentBlockEndSymbol;
    }

    public static void printGrammarNodes() {
        System.out.printf("\n\nGrammar Nodes:");
        for (String key : grammarNodes.keySet()) {
            System.out.printf("\n\n\n----------------------------------------------------------------------------------------------------------------------------------------------------------------\nHashMap Key: %-30sGrammar Id: %-30sParent Count: %-10dChild Batch Count: %d\n", key, grammarNodes.get(key).getGrammarId(), grammarNodes.get(key).getParentCount(), grammarNodes.get(key).getChildBatchCount());
            grammarNodes.get(key).printParents();
            grammarNodes.get(key).printChildBatches();
        }
        System.out.printf("\n");
    }

    public static void printGrammarSelfPointerNodes() {
        System.out.printf("\n\nGrammar Nodes That Point To Themselves:");
        for (String key : grammarNodes.keySet()) {
            if (grammarNodes.get(key).doesPointsToSelf()) {
                System.out.printf("\n\n\n\n----------------------------------------------------------------------------------------------------------------------------------------------------------------\nHashMap Key: %-30sGrammar Id: %-30sParent Count: %-10dChild Batch Count: %d\n", key, grammarNodes.get(key).getGrammarId(), grammarNodes.get(key).getParentCount(), grammarNodes.get(key).getChildBatchCount());
                grammarNodes.get(key).printParents();
                grammarNodes.get(key).printChildBatches();
            }
        }
        System.out.printf("\n");
    }

    public static void printReserveWordsCompact() {
        int counter = 0, holder = 0;
        System.out.printf("\n\nReserve Words List:\n");
        for (String temp : reserveWords) {
            System.out.printf("%-3d: %-30s", holder, temp);
            if (counter == 2) {
                System.out.printf("\n");
                counter = -1;
            }
            counter++;
            holder++;
        }
        System.out.printf("\n");
    }

    public static void printReserveWords() {
        System.out.printf("\n\nReserve Words List:");
        for (String temp : reserveWords) {
            System.out.printf("\n%s", temp);
        }
        System.out.printf("\n");
    }

    public static void printUnknownListCompact() {
        int counter = 0, holder = 0;
        System.out.printf("\n\nUnknown Variables List:\n");
        for (String temp : unknownList) {
            System.out.printf("%-3d: %-30s", holder, temp);
            if (counter == 2) {
                System.out.printf("\n");
                counter = -1;
            }
            counter++;
            holder++;
        }
        System.out.printf("\n");
    }

    public static void printUnknownList() {
        System.out.printf("\n\nUnknown Variables List:");
        for (String temp : unknownList) {
            System.out.printf("\n%s", temp);
        }
        System.out.printf("\n");
    }

    public static void printReserveWordsMinusCombinablesCompact() {
        int counter = 0, holder = 0;
        System.out.printf("\n\nReserve Words Minus Combinables List:\n");
        for (String temp : reserveWordsMinusCombinables) {
            System.out.printf("%-3d: %-30s", holder, temp);
            if (counter == 2) {
                System.out.printf("\n");
                counter = -1;
            }
            counter++;
            holder++;
        }
        System.out.printf("\n");
    }

    public static void printReserveWordsMinusCombinables() {
        System.out.printf("\n\nReserve Words Minus Cobinables List:");
        for (String temp : reserveWordsMinusCombinables) {
            System.out.printf("\n%s", temp);
        }
        System.out.printf("\n");
    }

    public static void printCombinablesCompact() {
        int counter = 0, holder = 0;
        System.out.printf("\n\nCombinables List:\n");
        for (String temp : combinables) {
            System.out.printf("%-3d: %-30s", holder, temp);
            if (counter == 2) {
                System.out.printf("\n");
                counter = -1;
            }
            counter++;
            holder++;
        }
        System.out.printf("\n");
    }

    public static void printCombinables() {
        System.out.printf("\n\nCombinables List:");
        for (String temp : combinables) {
            System.out.printf("\n%s", temp);
        }
        System.out.printf("\n");
    }

    public static void printCodeTextCompact() {
        int counter = 0, holder = 0;
        System.out.printf("\n\nCode Text:\n");
        for (String temp : codeText) {
            if (temp.isEmpty()) {
                System.out.printf("%-3d: %-30s", holder, "__");
            } else {
                System.out.printf("%-3d: %-30s", holder, temp);
            }
            if (counter == 2) {
                System.out.printf("\n");
                counter = -1;
            }
            counter++;
            holder++;
        }
        System.out.printf("\n");
    }

    public static void printCodeText() {
        System.out.printf("\n\nCode Text:");
        for (String temp : codeText) {
            System.out.printf("\n%s", temp);
        }
        System.out.printf("\n");
    }

    public static void printCodeListCompact() {
        int counter = 0, holder = 0;
        System.out.printf("\n\nCode List:\n");
        for (String temp : codeList) {
            System.out.printf("%-3d: %-30s", holder, temp);
            if (counter == 2) {
                System.out.printf("\n");
                counter = -1;
            }
            counter++;
            holder++;
        }
        System.out.printf("\n");
    }

    public static void printCodeList() {
        System.out.printf("\n\nCode List:");
        for (String temp : codeList) {
            System.out.printf("\n%s", temp);
        }
        System.out.printf("\n");
    }

    public static void printTokens() {
        System.out.printf("\n\nTokens:");
        for (Token temp : tokens) {
            temp.printIdAndValue();
        }
        System.out.println("\n");
    }

    public static void printDatabaseAndProcesses() {
        System.out.printf("\n\nDatabase And Processes:");
        printGrammarNodes();
        System.out.printf("\n\n\n\n\n");
        printGrammarSelfPointerNodes();
        System.out.printf("\n\n\n\n\n");
        printReserveWordsCompact();
        printUnknownListCompact();
        printReserveWordsMinusCombinablesCompact();
        printCombinablesCompact();
        printCodeTextCompact();
        printCodeListCompact();
        printTokens();
    }

    public static void setCommentSymbol(String symbol) {
        try {
            commentSymbol = symbol;
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public static void setCommentBlockStartSymbol(String symbol) {
        try {
            commentBlockStartSymbol = symbol;
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public static void setCommentBlockEndSymbol(String symbol) {
        try {
            commentBlockEndSymbol = symbol;
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public static void addGrammarNode(String grammarId, GrammarNode node) {
        try {
            grammarNodes.put(grammarId, node);
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    /**
     * This method adds a reserve word to the reserve word list
     *
     * @param reserveWord
     */
    public static void addReserveWord(String reserveWord, boolean endLineSymbol) {
        try {
            if (endLineSymbol) {
                reserveWords.add(reserveWord);
                endLineExists = true;
            } else {
                if (endLineExists) {
                    reserveWords.add(reserveWords.size() - 1, reserveWord);
                } else {
                    reserveWords.add(reserveWord);
                }
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public static void addUnknown(String unknown) {
        try {
            unknownList.add(unknown);
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public static void addReserveWordMinusCombinables(String reserveWordMinusCombinables) {
        try {
            reserveWordsMinusCombinables.add(reserveWordMinusCombinables);
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public static void addCombinable(String combinable) {
        try {
            if (combinables.isEmpty()) {
                combinables.add(combinable);
            } else {
                for (int i = 0; i < combinables.size(); i++) {
                    if (combinables.get(i).length() < combinable.length()) {
                        combinables.add(i, combinable);
                        break;
                    } else if (i == combinables.size() - 1) {
                        combinables.add(combinable);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    /*public static void addCode(String code) {
     try {
     codeList.add(code);
     } catch (Exception ex) {
     System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
     //ex.printStackTrace();
     }
     }//*/
    public static void addToken(Token token) {
        try {
            tokens.add(token);
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }
}
