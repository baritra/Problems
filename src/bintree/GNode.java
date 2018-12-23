package bintree;

import java.util.List;

public class GNode {

    private int value;
    private List<GNode> children;

    public GNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public List<GNode> getChildren() {
        return children;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setChildren(List<GNode> children) {
        this.children = children;
    }
}
