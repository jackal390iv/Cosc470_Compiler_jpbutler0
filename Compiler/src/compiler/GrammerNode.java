/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class GrammerNode {

    private String grammerId;
    private int parentCount = 0, childBatchCount = 0;
    private GrammerNode[] parents;
    private ChildBatch[] batchesOfChildren;

    public GrammerNode(String grammerId, int parentNum, int childNum) {
        this.grammerId = grammerId;
        parents = new GrammerNode[parentNum];
        batchesOfChildren = new ChildBatch[childNum];
    }

    public String getGrammerId() {
        return grammerId;
    }

    public void addParent(GrammerNode parentId) {
        parents[parentCount] = parentId;
        parentCount++;
    }

    public void addToChildBatch(String childId) {
        batchesOfChildren[childBatchCount].addChildToCurrentBatch(this, childId);
    }

    public void newChildBatch() {
        childBatchCount++;
    }

}
