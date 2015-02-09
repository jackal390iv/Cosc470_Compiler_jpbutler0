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

    private int level, scope;
    private String name, type;
    private boolean combinable;

    /**
     * This method creates a token object that contains a name, type, and
     * whether or not it is combinable with other, specific tokens; along with
     * some other optional data values.
     *
     * @param name
     * @param type
     * @param combinable
     */
    public Token(String name, String type, boolean combinable) {
        this.name = name;
        this.type = type;
        this.combinable = combinable;
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
     * This method sets whether or not the current token is combinable with
     * other, specific tokens.
     *
     * @param combinable
     */
    public void setCombinable(boolean combinable) {
        this.combinable = combinable;
    }

    /**
     * This method returns whether or not the current token is combinable with
     * other, specific tokens.
     *
     * @return
     */
    public boolean getCombinable() {
        return combinable;
    }

    /**
     * This method prints the current tokens data.
     *
     */
    public void printToken() {
        System.out.print("\n Name: " + name + "\t Type: " + type + "\t Combinable: " + combinable + "\t Level: " + level + "\t Scope: " + scope + "\t");
    }
}
