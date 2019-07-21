package bintree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GNode {

    private int value;
    private List<GNode> children;

    public GNode(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public int getValue() {
        return value;
    }

    public List<GNode> getChildren() {
        return children;
    }
    public GNode addChild(GNode child) {
        this.children.add(child);
        return this;

    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setChildren(List<GNode> children) {
        this.children = children;
    }

    public static String serialize(GNode node) {
        String serialized = "(" + node.value;
        for (GNode child : node.children) {
            serialized += serialize(child);
        }
        serialized += ")";
        return serialized;


    }

    public static GNode deserialize(String serialized) {
        Stack<GNode> parseStack = new Stack<>();
        for (int i = 0; i < serialized.length(); i++) {
            if (serialized.charAt(i) == '(') {
                if (i == serialized.length() - 1) {
                    throw new IllegalStateException("Invalid");
                }
                parseStack.push(new GNode(serialized.charAt(i+1) - '0'));
                i++;
            } else if (serialized.charAt(i) == ')') {
                GNode child = parseStack.pop();
                if (parseStack.isEmpty()) {
                    return child;
                } else {
                    parseStack.peek().addChild(child);
                }
            } else {
                throw new IllegalStateException("Invalid");
            }
        }

        throw new IllegalStateException("Invalid");

    }
    // 1,2,3,4,5,6,7 - > 7,6,5,4,3,1,2

    public static int binSearchRotatedArry(int[]a, int target, int low, int high) {
        int mid = (low + high)/2;
        if (low > high) {
            return -1;
        }
        if (a[mid] == target) {
            return a[mid];
        }
        if (target > a[mid]) {
            if (target <= a[high]) {
                return binSearchRotatedArry(a, target, mid+1, high);
            } else {
                return binSearchRotatedArry(a, target, low, mid-1);
            }
        } else {
            //target < a[mid]
            if (target >= a[low]) {
                return binSearchRotatedArry(a, target, low, mid-1);
            } else {
                return binSearchRotatedArry(a, target, mid+1, high);
            }
        }


    }

    public static void main(String[] args) {
        /*GNode n1 = new GNode(1);
        GNode n2 = new GNode(2);
        n1.addChild(new GNode(3)).addChild(new GNode(4));
        n2.addChild(new GNode(5)).addChild(new GNode(6));
        GNode parent = new GNode(0);
        parent.addChild(n1).addChild(n2);
        String s = serialize(n2);
        GNode root = deserialize(s);
        System.out.println(serialize(root));
        deserialize("1(");*/
        int[] a = {7,6,5,4,3,1,2};
        System.out.println(binSearchRotatedArry(a, 9, 0, a.length-1));
    }
}
