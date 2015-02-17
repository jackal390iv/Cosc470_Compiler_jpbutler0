/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class ChildBatch {

    private int childCount = 0;
    private GrammerNode[] children;

    public ChildBatch(int childNum) {
        children = new GrammerNode[childNum];
    }

    public void addChildToCurrentBatch(GrammerNode currentId, String childId) {
        children[childCount] =  GrammerCollection.getNode(childId);        
        GrammerCollection.getNode(childId).addParent(currentId);
        childCount++;
    }
    
    
    
}
