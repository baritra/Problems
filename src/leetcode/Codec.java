package leetcode;

import java.util.*;


public class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuffer b = new StringBuffer();
        serialize(root, b);
        return b.toString();
    }

    private void serialize(Node root, StringBuffer b) {
        if (root == null) {
            return;
        }
        b.append(root.val + "[");
        if (root.children != null) {
            Iterator<Node> it = root.children.iterator();
            while(it.hasNext()) {
                serialize(it.next(), b);
                if (it.hasNext()) b.append(",");
            }
        }
        b.append("]");
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        Stack<SEntry> stack = new Stack<SEntry>();
        int i = 0;
        while (i < data.length()) {
            char c = data.charAt(i);
            if (Character.isDigit(c)) {
                StringBuffer b = new StringBuffer();
                while(Character.isDigit(data.charAt(i))) {
                    b.append(data.charAt(i));
                    i++;
                }
                int value = Integer.parseInt(b.toString());
                stack.push(new SEntry(new Node(value)));
            } else if (c == '[') {
                stack.push(new SEntry(c));
                i++;
            } else if (c == ']') {
                SEntry e = stack.pop();
                LinkedList<Node> children = new LinkedList<>();
                while(e.del == null) {
                    children.addFirst(e.n);
                    e = stack.pop();
                }
                Node root = stack.pop().n;
                if (root == null) System.out.println("Error");
                if (!children.isEmpty()) root.children = children;
                if (stack.isEmpty()) return root;
                stack.push(new SEntry(root));
                i++;
            } else {
                i++;
            }
        }
        System.out.println("error");
        return null;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class SEntry {
        Node n;
        Character del;
        public SEntry(Node n) {
            this.n = n;
        }
        public SEntry(char del) {
            this.del = del;
        }
        public String toString() {

            return n == null ? ""+del : n.val + "";
        }
    }

    public static void main(String[] args) {
        Codec c = new Codec();
        Node n = c.deserialize("1[2[],3[6[],7[11[14[]]]],4[8[12[]]],5[9[13[]],10[]]]");
        System.out.println(c.serialize(n));
    }
}