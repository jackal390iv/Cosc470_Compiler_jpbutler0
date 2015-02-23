/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

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
    private static ArrayList<String> reserveWords, reserveWordsMinusCombinables, spacedCombinables, regularCombinables, codeList, unknownList;
    private static String commentSymbol, commentBlockStartSymbol,commentBlockEndSymbol;
    private static boolean endLineExists;

    public TheCollector() {
        grammarNodes = new HashMap<String, GrammarNode>();
        tokens = new ArrayList<Token>();
        reserveWords = new ArrayList<String>();
        reserveWordsMinusCombinables = new ArrayList<String>();
        spacedCombinables = new ArrayList<String>();
        regularCombinables = new ArrayList<String>();
        codeList = new ArrayList<String>();
        unknownList = new ArrayList<String>();
        endLineExists = false;
    }

    public static void addNode(String grammarId, GrammarNode node) {
        grammarNodes.put(grammarId, node);
    }

    public static GrammarNode getNode(String grammarId) {
        return grammarNodes.get(grammarId); //returns null if it doesn't exist
    }

    public static void printNodes() {
        for (String key : grammarNodes.keySet()) {
            System.out.printf("\n\n\nHashMap Key: %-50sGrammar Id: %-50sChild Batch Count: %-50dParent Count: %-50d\n", key, grammarNodes.get(key).getGrammarId(), grammarNodes.get(key).getChildBatchCount(), grammarNodes.get(key).getParentCount());
            grammarNodes.get(key).printParents();
            grammarNodes.get(key).printChildBatches();
        }
    }
    
    public static void addToken(Token token){
        tokens.add(token);
    }
    
    public static void printTokens(){
        for(Token temp:tokens){
            temp.printIdAndValue();
        }
    }

    public static void printSelfPointerNodes() {
        for (String key : grammarNodes.keySet()) {
            if (grammarNodes.get(key).doesPointsToSelf()) {
                System.out.printf("\n\n\nHashMap Key: %-50sGrammar Id: %-50sChild Batch Count: %-50dParent Count: %-50d\n", key, grammarNodes.get(key).getGrammarId(), grammarNodes.get(key).getChildBatchCount(), grammarNodes.get(key).getParentCount());
                grammarNodes.get(key).printParents();
                grammarNodes.get(key).printChildBatches();
            }
        }
    }

    /**
     * This method adds a reserve word to the reserve word list
     *
     * @param reserveWord
     */
    public static void addReserveWord(String reserveWord, boolean endLineSymbol) {
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

    /**
     * This method prints all grammar reserve words that are held in the
     * 'reserve' ArrayList.
     *
     */
    public static void printReserveWords() {
        System.out.printf("\n\nReserve Word List:");
        for (String temp : reserveWords) {
            System.out.printf("\n%s", temp);
        }
    }
    
    public static void setCommentSymbol(String symbol){
        commentSymbol=symbol;
    }
    
    public static String getCommentSymbol(){
        return commentSymbol;
    }
    
    public static void setCommentBlockStartSymbol(String symbol){
        commentBlockStartSymbol=symbol;
    }
    
    public static String getCommentBlockStartSymbol(){
        return commentBlockStartSymbol;
    }
    
    public static void setCommentBlockEndSymbol(String symbol){
        commentBlockEndSymbol=symbol;
    }
    
    public static String getCommentBlockEndSymbol(){
        return commentBlockEndSymbol;
    }

    public static void addCode(String code) {
        codeList.add(code);
    }

    public static ArrayList<String> getCodeList() {
        return codeList;
    }

    public static void printCodeList() {
        for (String temp : codeList) {
            System.out.println(temp);
        }
    }

    public static void addUnknow(String unknown) {
        unknownList.add(unknown);
    }

    public static ArrayList<String> getUnknownList() {
        return unknownList;
    }

    public static void printUnknownList() {
        System.out.printf("\n\nUnknown Variables List:");
        for (String temp : unknownList) {
            System.out.printf("\n%s", temp);
        }
    }

    public static void addRegualarCombinable(String combinable) {
        if (regularCombinables.size() == 0) {
            regularCombinables.add(combinable);
        } else {
            for (int i = 0; i < regularCombinables.size(); i++) {
                if (regularCombinables.get(i).length() < combinable.length()) {
                    regularCombinables.add(i, combinable);
                    break;
                } else if (i == regularCombinables.size() - 1) {
                    regularCombinables.add(combinable);
                    break;
                }
            }
        }
    }

    public static ArrayList<String> getRegualarCombinables() {
        return regularCombinables;
    }

    public static void printRegularCombinables() {
        System.out.printf("\n\nRegular Combinables List:");
        for (String temp : regularCombinables) {
            System.out.printf("\n%s", temp);
        }
    }

    public static void addSpacedCombinable(String spacedCombinable) {
        if (spacedCombinables.size() == 0) {
            spacedCombinables.add(spacedCombinable);
        } else {
            for (int i = 0; i < spacedCombinables.size(); i++) {
                if (spacedCombinables.get(i).length() < spacedCombinable.length()) {
                    spacedCombinables.add(i, spacedCombinable);
                    break;
                } else if (i == spacedCombinables.size() - 1) {
                    spacedCombinables.add(spacedCombinable);
                    break;
                }
            }
        }
    }

    public static ArrayList<String> getSpacedCombinables() {
        return spacedCombinables;
    }

    public static void printSpacedCombinables() {
        System.out.printf("\n\nSpaced Cobinables List:");
        for (String temp : spacedCombinables) {
            System.out.printf("\n%s", temp);
        }
    }

    public static void createReserveWordsMinusCombinables() {
        boolean exists;
        for (String temp : reserveWords) {
            exists = false;
            for (String Rcombinables : regularCombinables) {
                if (temp.equals(Rcombinables)) {
                    exists = true;
                    break;
                }
            }
            for (String Scombinables : spacedCombinables) {
                if (temp.equals(Scombinables)) {
                    exists = true;
                    break;
                }
            }
            if (exists != true) {
                reserveWordsMinusCombinables.add(temp);
            }
        }
    }
    
    public static ArrayList<String> getReserveWordsMinusCombinables() {
        return reserveWordsMinusCombinables;
    }
    
    public static void printReserveWordsMinusCombinables() {
        System.out.printf("\n\nReserve Words Minus Cobinables List:");
        for (String temp : reserveWordsMinusCombinables) {
            System.out.printf("\n%s", temp);
        }
    }


}
