import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * http://codeforces.com/problemset/problem/1437/
 */
public class MinHeightTree {

    static class Node {
        int val;
        List<Node> children = new ArrayList<>();
        public Node(int val) {
            this.val = val;
        }
    }

    public int solve(int[] a) {
        Node root = buildTree(a);
        return height(root);
    }

    private int height(Node root) {
        if (root.children.isEmpty()) return 0;
        int maxHeight = Integer.MIN_VALUE;
        for (Node child : root.children) {
            maxHeight = Math.max(maxHeight, height(child)+1);
        }
        return maxHeight;
    }

    public Node buildTree(int[] a) {
        Node root = new Node(a[0]);
        Queue<Node> q = new LinkedBlockingDeque<>();
        q.offer(root);
        for (int i = 1; i < a.length; i++) {
            Node child = new Node(a[i]);
            if (!q.isEmpty()) {
                Node potentialParent = q.element();
                if (potentialParent.children.isEmpty()
                        || potentialParent.children.get(potentialParent.children.size() - 1).val <= child.val) {
                    potentialParent.children.add(child);
                } else {
                    q.remove();
                    q.element().children.add(child);
                }
            }
            q.offer(child);

        }
        return root;
    }

    public int numNonDecreasingSequences(int s, int[]a) {
         if (s >= a.length) return 0;
         int count = 1;
         for (int i = s+1; i < a.length; i++) {
             if (a[i] < a[i-1]) count++;
         }
         return count;
    }

    public static void main(String[] args) {
        //System.out.println(new MinHeightTree().numNonDecreasingSequences(0, new int[] {4, 3, 2}));
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine());
        int[] answers = new int[t];
        int ai = 0;
        MinHeightTree soln = new MinHeightTree();
        for (int i = 0; i < t; i++) {
            String nstring = in.nextLine();
            int n = Integer.parseInt(nstring);
            String nodes = in.nextLine();
            Scanner stringscan = new Scanner(nodes);
            int[] bfs = new int[n];
            for (int j = 0; j < n; j++) {
                bfs[j] = stringscan.nextInt();
            }
            answers[ai++] = soln.solve(bfs);
        }
        for (int i = 0; i < answers.length; i++) {
            System.out.print(answers[i]);
            if (i < answers.length - 1) System.out.println();
        }
    }

}
