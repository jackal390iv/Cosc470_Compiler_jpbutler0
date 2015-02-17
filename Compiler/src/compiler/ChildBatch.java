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
public class ChildBatch {

    private ArrayList<GrammerNode> children;

    public ChildBatch() {
        children = new ArrayList<GrammerNode>();
    }

    public void addChildToCurrentBatch(GrammerNode currentId, String childId) {
        try {
            children.add(GrammerCollection.getNode(childId));
            GrammerCollection.getNode(childId).addParent(currentId);
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

    public void printChildren() {
        int count = 1;
        for (GrammerNode temp : children) {
            System.out.printf("Child %d: %-50s", count, temp.getGrammerId());
            count++;
        }
    }

}
