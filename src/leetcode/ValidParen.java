package leetcode;

import java.util.Objects;
import java.util.Set;
import java.util.*;

public class ValidParen {
    public List<String> removeInvalidParentheses(String s) {
        Map<Pair, Set<String>> cache = new HashMap<Pair, Set<String>> ();
        Set<String> result = removeInvalid(s, 0, s.length()-1, cache);

        return new ArrayList<>(result);
    }

    public Set<String> removeInvalid(String s, int start, int end, Map<Pair, Set<String>> cache) {
        Pair p = new Pair(start, end);
        if (cache.containsKey(p)) {
            return cache.get(p);
        }
        Set<String> result = new HashSet<>();
        result.add("");
        if (start >= end) {
            return result;
        }
        int i = start;
        for (i = start; i <= end; i++) {
            if (s.charAt(i) == '(') break;
        }
        if (i > end) {
            return result;
        }

        int openParen = i;

        List<Integer> closingParenIndices = new ArrayList<>();
        for (int k = i+1; k <= end; k++) {
            if (s.charAt(k) == ')') {
                closingParenIndices.add(k);
            }
        }
        if (closingParenIndices.isEmpty()) {
            return result;
        }
        Map<Integer, Set<String>> resultsByLength = new HashMap<>();
        int maxResultLen = Integer.MIN_VALUE;
        for (int closingIndex : closingParenIndices) {
            // force openParen and closingIndex to match
            Set<String> nestedResult = removeInvalid(s, openParen + 1, closingIndex - 1, cache);
            Set<String> remainderResult = removeInvalid(s, closingIndex + 1, end, cache);
            for (String nested : nestedResult) {
                for (String remainder : remainderResult) {
                    String thisResult = "("+nested+")"+remainder;
                    int len = thisResult.length();
                    if (len >  maxResultLen) {
                        maxResultLen = len;
                    }
                    if (!resultsByLength.containsKey(len)) {
                        resultsByLength.put(len, new HashSet<String>());
                    }
                    resultsByLength.get(len).add(thisResult);
                }
            }
        }
        cache.put(p, resultsByLength.get(maxResultLen));
        return resultsByLength.get(maxResultLen);

    }

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof util.Pair)) return false;
            leetcode.ValidParen.Pair pair = (leetcode.ValidParen.Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
/*
public static void main(String[] args) {
        System.out.println(new ValidParen().removeInvalidParentheses("()())()"));
}
*/


}
