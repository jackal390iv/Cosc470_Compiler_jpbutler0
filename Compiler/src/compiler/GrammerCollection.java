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

    private static HashMap<String, GrammerNode> grammerNodes;   // = new HashMap<String,GrammerNode>();

    /*public GrammerCollection(String grammerId){
     grammerNodes.put(grammerId,new GrammerNode(grammerId));
     }//*/
    public GrammerCollection() {
        grammerNodes = new HashMap<String, GrammerNode>();
    }

    public static void addNode(String grammerId, GrammerNode node) {
        grammerNodes.put(grammerId, node);
    }

    public static GrammerNode getNode(String grammerId) {
        return grammerNodes.get(grammerId); //returns null if it doesn't exist
    }

}
