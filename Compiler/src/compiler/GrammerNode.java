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
public class GrammerNode {

    //isReserve means that the node is a reserve word, an unknown, or empty. This includes all bottom most elements in the tree
    //activeNode means that the node is a reserve word or an unknown word and that it is being used in the code. Any empty word nodes are automatically seen as activeNode = true
    private boolean activeNode, isReserve, pointsToSelf;
    private String grammerId, nodeValue;
    int parentCount, childBatchCount;
    private ArrayList<GrammerNode> parents;
    private ArrayList<ChildBatch> batchesOfChildren;

    public GrammerNode(String grammerId) {
        this.grammerId = grammerId;
        this.nodeValue = null;    //node values are originally set to null for all nodes
        activeNode = false;
        isReserve = false;
        pointsToSelf = false;
        parents = new ArrayList<GrammerNode>();
        batchesOfChildren = new ArrayList<ChildBatch>();
        GrammerCollection.addNode(grammerId, this);
    }

    public String getGrammerId() {
        return grammerId;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public boolean doesPointsToSelf() {
        return pointsToSelf;
    }

    public void setNodeToActive() {
        activeNode = true;
    }

    public void setNodeToDead() {
        activeNode = false;
    }

    public boolean isNodeActive() {
        return activeNode;
    }

    public boolean isReserveOrUnknownWord() {
        return isReserve;
    }

    public int getParentCount() {
        return parents.size();
    }

    public int getChildBatchCount() {
        return batchesOfChildren.size();
    }

    public void addParent(GrammerNode parentId) {
        boolean exists = false;
        for (GrammerNode temp : parents) {
            if (temp.getGrammerId().equals(parentId.getGrammerId())) {
                exists = true;
            }
        }
        if (exists == false) {
            parents.add(parentId);
        }
    }

    public void addToChildBatch(String childId) {
        try {
            batchesOfChildren.get(batchesOfChildren.size() - 1).addChildToCurrentBatch(this, childId);
            if ((childId.equals("symbol")) | (childId.equals("unknown")) | (childId.equals("empty"))) {
                if (childId.equals("empty")) {
                    activeNode = true;
                }
                isReserve = true;
            }
            if (childId.equals(grammerId)) {
                pointsToSelf = true;
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
        for (GrammerNode temp : parents) {
            System.out.printf("Parent %d: %-50s", count, temp.getGrammerId());
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

    public GrammerNode getParent(int nodeLocation) {
        return parents.get(nodeLocation);
    }

}
