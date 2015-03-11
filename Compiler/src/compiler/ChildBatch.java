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
        children = new ArrayList<>();
    }

    public void addChildToCurrentBatch(GrammarNode currentId, String childId) {
        try {
            children.add(TheCollector.getGrammarNode(childId));
            TheCollector.getGrammarNode(childId).addParent(currentId);
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }

    public void printChildren() {
        int count = 1;
        try {
            System.out.printf("\t");
            for (GrammarNode temp : children) {
                System.out.printf("Child %-3d: %-30s", count, temp.getGrammarId());
                count++;
            }
        } catch (Exception ex) {
            System.out.printf("\n\nERROR\nType: %s\nLocation: %s\nThrown Exception: %s\nMessage: %s\nLocalMessage: %s\n", ex.getClass().getName(), ex.getStackTrace()[2], ex.getCause(), ex.getMessage(), ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }
}
