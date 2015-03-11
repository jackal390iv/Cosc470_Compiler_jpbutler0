/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

import java.util.ArrayList;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class GrammarNode {

    //isReserve means that the node is a reserve word, an unknown, or empty. This includes all bottom most elements in the tree
    //activeNode means that the node is a reserve word or an unknown word and that it is being used in the code. Any empty word nodes are automatically seen as activeNode = true
    private boolean isReserveWord, doesPointToSelf;
    private String grammarId;
    int parentCount, childBatchCount;
    private ArrayList<GrammarNode> parents;
    private ArrayList<ChildBatch> batchesOfChildren;

    public GrammarNode(String grammarId) {
        this.grammarId = grammarId;
        isReserveWord = false;
        doesPointToSelf = false;
        parents = new ArrayList<GrammarNode>();
        batchesOfChildren = new ArrayList<ChildBatch>();
        TheCollector.addGrammarNode(grammarId, this);
    }

    public String getGrammarId() {
        return grammarId;
    }

    public boolean doesPointsToSelf() {
        return doesPointToSelf;
    }

    public boolean isReserveOrUnknownWord() {
        return isReserveWord;
    }

    public int getParentCount() {
        return parents.size();
    }

    public int getChildBatchCount() {
        return batchesOfChildren.size();
    }

    public GrammarNode getParent(int nodeLocation) {
        return parents.get(nodeLocation);
    }

    public void addParent(GrammarNode parentId) {
        boolean exists = false;
        try {
            for (GrammarNode temp : parents) {
                if (temp.getGrammarId().equals(parentId.getGrammarId())) {
                    exists = true;
                }
            }
            if (exists == false) {
                parents.add(parentId);
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public void addToChildBatch(String childId) {
        try {
            if (childId.equals("symbol")) {
                TheCollector.addReserveWord(grammarId, false);
                isReserveWord = true;
            } else if (childId.equals("symbol_END_LINE")) {
                TheCollector.addReserveWord(grammarId, true);
                isReserveWord = true;
            } else if (childId.equals("unknown")) {
                TheCollector.addUnknown(grammarId);
                isReserveWord = true;
            } else {
                if (childId.equals(grammarId)) {
                    doesPointToSelf = true;
                }
                batchesOfChildren.get(batchesOfChildren.size() - 1).addChildToCurrentBatch(this, childId);
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }

    }

    public void newChildBatch() {
        try {
            batchesOfChildren.add(new ChildBatch());
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public void printParents() {
        int count = 1;
        try {
            System.out.printf("\t");
            for (GrammarNode temp : parents) {
                System.out.printf("Parent %-3d: %-30s", count, temp.getGrammarId());
                count++;
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public void printChildBatches() {
        int count = 1;
        try {
            for (ChildBatch temp : batchesOfChildren) {
                System.out.printf("\nChild Batch %d\n", count);
                temp.printChildren();
                count++;
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }
}
