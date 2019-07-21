package leetcode;

import java.util.*;

public class CoinDistribution {
    private List<Node> richNodes = new ArrayList<>();

    public int distributeCoins(TreeNode root) {
        List<Node> graphNodes = buildGraph(root, null);
        int totalMoves = 0;
        for (Node richNode : richNodes) {
            totalMoves += distributeWithBFS(richNode);
        }
        return totalMoves;
    }

    public int distributeWithBFS(Node richNode) {
        int extra = richNode.val - 1;
        LinkedList<Node> queue = new LinkedList<Node>();
        Set<Node> visited = new HashSet<>();
        int hops = 0;
        int totalMoves = 0;
        queue.addLast(richNode);
        while (extra > 0 && !queue.isEmpty()) {
            hops++;
            Node n = queue.removeFirst();
            visited.add(n);
            for (Node child : n.children) {
                if (!visited.contains(child)) {
                    queue.addLast(child);
                    if (child.val == 0 && extra > 0) {
                        child.val = child.val++;
                        extra--;
                        totalMoves += hops;
                    } else {
                        break;
                    }
                }
            }
        }
        return totalMoves;

    }

    private List<Node> buildGraph(TreeNode root, Node gnode) {
        if (root == null) return new ArrayList<Node>();
        List<Node> graphNodes = new ArrayList<>();
        Node n = new Node(root.val);
        if (n.val > 1) {
            this.richNodes.add(n);
        }
        if (gnode != null) {
            n.addChild(gnode);
            gnode.addChild(n);
        }
        graphNodes.addAll(buildGraph(root.left, n));
        graphNodes.addAll(buildGraph(root.right, n));
        return graphNodes;
    }
}

class Node {
    List<Node> children = new ArrayList<>();
    int val;
    public Node(int val) {
        this.val = val;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }