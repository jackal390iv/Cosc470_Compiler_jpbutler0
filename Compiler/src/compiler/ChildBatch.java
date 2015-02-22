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

    private ArrayList<GrammarNode> children;

    public ChildBatch() {
        children = new ArrayList<GrammarNode>();
    }

    public void addChildToCurrentBatch(GrammarNode currentId, String childId) {
        try {
            children.add(TheCollector.getNode(childId));
            TheCollector.getNode(childId).addParent(currentId);
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
        for (GrammarNode temp : children) {
            System.out.printf("Child %d: %-50s", count, temp.getGrammarId());
            count++;
        }
    }

}
