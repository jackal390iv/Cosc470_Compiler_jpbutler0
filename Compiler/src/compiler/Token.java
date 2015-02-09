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

    public Token(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public int getScope() {
        return scope;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String printToken() {
        return "\n Name: " + name + "\t Type: " + type + "\t Level: " + level + "\t Scope: " + scope + "\t";
    }
}
