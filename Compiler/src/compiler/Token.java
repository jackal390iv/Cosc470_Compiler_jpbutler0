/*
 * Class: COSC 470-001
 * Project: Compiler
 */
package compiler;

/**
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class Token {

    private int typeId, level, scope, lineNum, size, counter;
    private String name, type;
    private int[] combines;
    private int[] last;

    public Token(String name, String type, int typeId, int sizeLast) {
        this.name = name;
        this.type = type;
        this.typeId = typeId;
        this.size = 0;
        /*if (sizeCombines != -1) {
            combines = new int[sizeCombines];
        }//*/
        if (sizeLast>0) {
            last = new int[sizeLast];
        }
    }

    /**
     * This method sets the type-id of the current token.
     *
     * @param typeId
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    /**
     * This method returns the type-id of the current token.
     *
     * @return
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * This method sets the scope of the current token.
     *
     * @param scope
     */
    public void setScope(int scope) {
        this.scope = scope;
    }

    /**
     * This method returns the scope of the current token.
     *
     * @return
     */
    public int getScope() {
        return scope;
    }

    /**
     * This method sets the level of the current token.
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * This method returns the level of the current token.
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * This method sets the line number of the current token.
     *
     * @param lineNum
     */
    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * This method returns the line number of the current token.
     *
     * @return
     */
    public int getLineNum() {
        return lineNum;
    }

    /**
     * This method sets the size of this token.
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * This method returns the size of this token.
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * This method sets counter.
     *
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * This method sets the counter to zero.
     *
     */
    public void resetCounter() {
        counter = 0;
    }

    /**
     * This method returns counter.
     *
     * @return
     */
    public int getCounter() {
        return counter;
    }

    /**
     * This method sets the name of the current token.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the name of the current token.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the type of the current token.
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method returns the type of the current token.
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * This method adds element to combines.
     *
     * @param combinable
     */
    public void addCombines(int combinable) {
        combines[counter] = combinable;
        counter++;
    }

    /**
     * This method searches combines for element given, and returns element if
     * it is found, or returns -1 if it is not found.
     *
     * @param combinable
     * @return
     */
    public int searchCombines(int combinable) {
        int temp = -1;
        for (int i = 0; i < combines.length; i++) {
            if (combines[i] == combinable) {
                temp = combinable;
                break;
            }
        }
        return temp;
    }

    /**
     * This method adds element to last.
     * 
     * @param previous 
     */
    public void addLast(int previous) {
        last[counter] = previous;
        counter++;
    }

    /**
     * This method searches last for element given, and returns element if it is
     * found, or returns -1 if it is not found.
     *
     * @param previous
     * @return
     */
    public int searchLast(int previous) {
        int temp = -1;
        for (int i = 0; i < last.length; i++) {
            if (last[i] == previous) {
                temp = previous;
                break;
            }
        }
        return temp;
    }

    /**
     * This method prints the current tokens data.
     *
     */
    public void printToken() {
        System.out.print("\n Name: " + name + "\t Type: " + type + "\t Type-Id: " + typeId + "\t Combinable: " + combines + "\t Size: " + size + "\t Scope: " + scope + "\t Level: " + level + "\t Line Number: " + lineNum + "\t");
    }
}
