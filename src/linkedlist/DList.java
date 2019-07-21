package linkedlist;

public class DList<T> {
    DNode<T> head;
    DNode<T> tail;

    public DNode<T> append(T val) {
        DNode node = new DNode(val);
        if (head == null  && tail == null) {
            head = node;
            tail = node;
            return node;
        }

        tail.insertAfter(node);
        tail = node;
        return node;
    }

    public DNode<T> removeHead() {
        DNode oldhead = head;
        head = head != null ? head.next : null;
        return oldhead;

    }

    public void moveToTail(DNode<T> node) {
        if (head == node) {
            head = head.next;
        }
        DNode oldNext = node.next;
        DNode oldPrev = node.prev;
        if (oldNext != null) {
            oldNext.prev = oldPrev;
        }
        if (oldPrev != null) {
            oldPrev.next = oldNext;
        }
        append(node.data);
    }


    public void print() {
        DNode n = head;
        while(n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }




}

class DNode<T> {
    T data;
    DNode prev = null;
    DNode next = null;

    public DNode(T data) {
        this.data = data;
    }
    public void insertAfter(DNode node) {
        DNode lastNext = this.next;
        this.next = node;
        node.prev = this;
        node.next = lastNext;
        if (lastNext != null) lastNext.prev = node;
    }

    public void delete() {
        DNode prevNode = this.prev;
        DNode nextNode = this.next;
        if (prevNode != null) prevNode.next = nextNode;
        if (nextNode != null) nextNode.prev = prevNode;
    }

    @Override
    public String toString() {
        return "DNode{" +
                "data=" + data +
                '}';
    }
}


