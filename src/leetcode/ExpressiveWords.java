package leetcode;

import java.util.Objects;

public class ExpressiveWords {
    public int expressiveWords(String S, String[] words) {
        if (S == null || S.isEmpty() ) {
            return 0;
        }
        if (words == null || words.length == 0) {
            return 0;
        }
        int count = 0;
        for (String w : words) {
            if (isStretchy(S, w)) {
                count++;
            }
        }
        return count;

    }

    public boolean isStretchy(String stretch, String root) { // heeellooo, hello
        int i = 0, j = 0;

        while (i < root.length() && j < stretch.length()) {
            char curRootChar = root.charAt(i); // h e
            char curStretchChar = stretch.charAt(j); //h e
            int numCurRootChar = 0;
            int numStretchChar = 0;
            if (curRootChar != curStretchChar) {
                return false;
            }
            while(i < root.length() && root.charAt(i) == curRootChar) {
                numCurRootChar++; //1
                i++; //1
            }

            while(j < stretch.length() && stretch.charAt(j) == curStretchChar) {
                numStretchChar++; //1
                j++; //2
            }


            if (numStretchChar < 3) {
                if (numStretchChar != numCurRootChar) {
                    return false;
                }
            }

        }
        if (i == root.length() && j == stretch.length()) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        System.out.println(new ExpressiveWords().isStretchy("heeellooo", "helloo"));
    }
}

class Stop {
    int node;
    int len;

    public Stop(int node, int len) {
        this.node = node;
        this.len = len;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stop)) return false;
        Stop stop = (Stop) o;
        return node == stop.node &&
                len == stop.len;
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, len);
    }

    @Override
    public String toString() {
        return "Stop{" +
                "node=" + node +
                ", len=" + len +
                '}';
    }
}
