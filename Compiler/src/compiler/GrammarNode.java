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
    private String grammarId, nodeValue;
    int parentCount, childBatchCount;
    private ArrayList<GrammarNode> parents;
    private ArrayList<ChildBatch> batchesOfChildren;

    public GrammarNode(String grammarId) {
        this.grammarId = grammarId;
        this.nodeValue = null;    //node values are originally set to null for all nodes
        isReserveWord = false;
        doesPointToSelf = false;
        parents = new ArrayList<GrammarNode>();
        batchesOfChildren = new ArrayList<ChildBatch>();
        TheCollector.addNode(grammarId, this);
    }

    public String getGrammarId() {
        return grammarId;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getNodeValue() {
        return nodeValue;
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

    public void addParent(GrammarNode parentId) {
        boolean exists = false;
        for (GrammarNode temp : parents) {
            if (temp.getGrammarId().equals(parentId.getGrammarId())) {
                exists = true;
            }
        }
        if (exists == false) {
            parents.add(parentId);
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
                TheCollector.addUnknow(grammarId);
            } else {
                if (childId.equals(grammarId)) {
                    doesPointToSelf = true;
                }
                batchesOfChildren.get(batchesOfChildren.size() - 1).addChildToCurrentBatch(this, childId);
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

    public void newChildBatch() {
        batchesOfChildren.add(new ChildBatch());
    }

    public void printParents() {
        int count = 1;
        for (GrammarNode temp : parents) {
            System.out.printf("Parent %d: %-50s", count, temp.getGrammarId());
            count++;
        }
    }

    public void printChildBatches() {
        int count = 1;
        for (ChildBatch temp : batchesOfChildren) {
            System.out.printf("\nChild Batch %-50d ", count);
            temp.printChildren();
            count++;
        }
    }

    public GrammarNode getParent(int nodeLocation) {
        return parents.get(nodeLocation);
    }

}
