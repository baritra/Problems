package bintree;

import java.util.Objects;

public class BNode {
    private BNode left;
    private BNode right;

    private int value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BNode)) return false;
        BNode bNode = (BNode) o;
        return value == bNode.value &&
                Objects.equals(left, bNode.left) &&
                Objects.equals(right, bNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, value);
    }

    public BNode getLeft() {
        return left;
    }

    public void setLeft(BNode left) {
        this.left = left;
    }

    public BNode getRight() {
        return right;
    }

    public void setRight(BNode right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BNode(int value) {
        this.value = value;
    }
}
