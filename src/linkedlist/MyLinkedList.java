package linkedlist;

public class MyLinkedList {
    LNode head;
    LNode tail;


    public void addLast(LNode n) {
        if (head == null) {
            head = tail = n;
        } else {
            tail.setNexNode(n);
        }

    }

    public void addFirst(LNode n) {
        if (head == null) {
            head = tail = n;
        } else {
            n.setNexNode(head);
            head = n;
        }

    }
}
