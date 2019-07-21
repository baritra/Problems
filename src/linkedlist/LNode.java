package linkedlist;


class LNode {
    int val;
    LNode nextNode;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public LNode getNexNode() {
        return nextNode;
    }

    public void setNexNode(LNode nexNode) {
        this.nextNode = nexNode;
    }

    public LNode(int val, LNode nexNode) {
        this.val = val;
        this.nextNode = nexNode;
    }

    public LNode(int val) {
        this.val = val;
    }
}
