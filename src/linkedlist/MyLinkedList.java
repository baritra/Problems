package linkedlist;

public class MyLinkedList {
    LNode head;
    LNode tail;

    public MyLinkedList append(int val) {
        LNode node = new LNode(val);
        if (head == null) {
            //tail must be null
            head = node;
            tail = node;
        } else {
            tail.nextNode = node;
            tail = node;
        }

        return this;
   }

   public void print() {
        LNode n = head;
        while (n != null) {
            System.out.print(n.val + ",");
            n = n.nextNode;
        }
   }

    public LNode lastButKth(int k) {
        if (head == null) {
            return null;
        }
        int count = 0;
        LNode n = head;
        LNode answer = null;

        while (n != null) {
            if (count == k) {
                answer = head;
            }
            count++;
            n = n.nextNode;
            if (n != null && answer != null) {
                answer = answer.nextNode;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        MyLinkedList l = new MyLinkedList();
        l.append(2).append(3).append(100).append(50).append(32).append(11).append(4).append(2);
        System.out.println(l.lastButKth(3).val);
    }
}
