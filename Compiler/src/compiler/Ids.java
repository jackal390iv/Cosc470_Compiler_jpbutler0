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
public class Ids {

    private ArrayList<String> combin;
    private String name;
    private boolean combinable;

    /**
     * This method creates an id object that contains a name, and a list of
     * possible combinable strings that can be added to it.
     *
     * @param name
     * @param val
     */
    public Ids(String name, boolean combinable) {
        this.name = name;
        this.combinable = combinable;
        if (combinable == true) {
            combin = new ArrayList<>();
        }
    }

    /**
     * This method changes the current name of this id.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the current name of this id.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * This method changes whether or not the current id is combinable with any
     * other string.
     *
     * @param combinable
     */
    public void setCombinable(boolean combinable) {
        this.combinable = combinable;
    }

    /**
     * This method returns true if the current id is combinable.
     *
     * @return
     */
    public boolean getCombinable() {
        return combinable;
    }

    /**
     * This method adds combinable strings that can be added to the current id.
     * For example: ! can have = added to itself.
     *
     * @param temp
     */
    public void addCombinable(String temp) {
        combin.add(temp);
    }

    /**
     * This method prints out all known combinable strings for this id.
     *
     */
    public void printCombinableList() {
        for (String item : combin) {
            System.out.print(item + ", ");
        }
    }

    /**
     * This method checks the next id name in the tokens list for combinability
     * with the current token id.
     *
     * @param temp
     * @return
     */
    public String checkNextid(String temp) {
        String val = null;
        for (String item : combin) {
            if (temp.equals(item)) {
                val = item;
            }
        }
        return val;
    }
}
