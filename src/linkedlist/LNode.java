package linkedlist;

public class LNode {
    int val;
    LNode nexNode;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public LNode getNexNode() {
        return nexNode;
    }

    public void setNexNode(LNode nexNode) {
        this.nexNode = nexNode;
    }

    public LNode(int val, LNode nexNode) {
        this.val = val;
        this.nexNode = nexNode;
    }
}
