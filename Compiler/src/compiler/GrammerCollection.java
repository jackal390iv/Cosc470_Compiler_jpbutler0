/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

import java.util.HashMap;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 *
 */
public class GrammerCollection {

    private static HashMap<String, GrammerNode> grammerNodes;

    public GrammerCollection() {
        grammerNodes = new HashMap<String, GrammerNode>();
    }

    public static void addNode(String grammerId, GrammerNode node) {
        grammerNodes.put(grammerId, node);
    }

    public static GrammerNode getNode(String grammerId) {
        return grammerNodes.get(grammerId); //returns null if it doesn't exist
    }

    public static void printNodes() {
        for (String key : grammerNodes.keySet()) {
            System.out.printf("\n\n\nHashMap Key: %-50sGrammer Id: %-50sChild Batch Count: %-50dParent Count: %-50d\n", key, grammerNodes.get(key).getGrammerId(), grammerNodes.get(key).getChildBatchCount(), grammerNodes.get(key).getParentCount());
            grammerNodes.get(key).printParents();
           grammerNodes.get(key).printChildBatches();
        }
    }

}
