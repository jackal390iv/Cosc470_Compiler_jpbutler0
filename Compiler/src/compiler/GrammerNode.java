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

    private String grammerId;
    private ArrayList<GrammerNode> parents;
    private ArrayList<ChildBatch> batchesOfChildren;

    public GrammerNode(String grammerId, int parentCount, int childBatchCount) {
        this.grammerId = grammerId;
        parents = new ArrayList<GrammerNode>();
        batchesOfChildren = new ArrayList<ChildBatch>();
        GrammerCollection.addNode(grammerId, this);
    }

    public String getGrammerId() {
        return grammerId;
    }
    
    public int getParentCount() {
        return parents.size();
    }

    public int getChildBatchCount() {
        return batchesOfChildren.size();
    }

    public void addParent(GrammerNode parentId) {
        boolean exists=false;
        for(GrammerNode temp:parents){
            if(temp.getGrammerId().equals(parentId.getGrammerId())){
                exists=true;
            }
        }
        if(exists==false){
        parents.add(parentId);}
    }

    public void addToChildBatch(String childId) {
        try {
            batchesOfChildren.get(batchesOfChildren.size() - 1).addChildToCurrentBatch(this, childId);
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
    
    public GrammerNode getParent(int nodeLocation){
        return parents.get(nodeLocation);
    }

}
