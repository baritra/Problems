package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MaxParen {
    public int longestValidParentheses(String s) {
        Stack<SData> stack = new Stack<>();
        List<Interval2> interval2s = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(new SData(i, '('));
            } else {
                if ( !stack.isEmpty() && stack.peek().b == '(') {
                    SData open = stack.pop();
                    interval2s.add(new Interval2(open.i, i));
                }
            }
        }

        int maxLen = Integer.MIN_VALUE;
        Stack<Interval2> mergedInterval2s = new Stack<>();
        for (Interval2 interval2 : interval2s) {
            if (mergedInterval2s.isEmpty()) {
                mergedInterval2s.push(interval2);
                if (interval2.len() > maxLen) {
                    maxLen = interval2.len();
                }
            } else {
                Interval2 prev = mergedInterval2s.peek();
                Interval2 merged = prev.mergeAdjacent(interval2);
                if (merged == null) {
                    mergedInterval2s.push(interval2);
                } else {
                    mergedInterval2s.push(merged);
                    if (maxLen < merged.len()) {
                        maxLen = merged.len();
                    }
                }
            }


        }
        return maxLen;
    }

    public static void main(String[]args) {
        System.out.println(new MaxParen().longestValidParentheses(")()()"));
    }
}


class SData {
    int i;
    char b;

    public SData(int i, char b) {
        this.i = i;
        this.b = b;
    }
}

class Interval2 {
    int s;
    int e;
    public Interval2(int i, int j) {
        this.s = i;
        this.e = j;
    }
    public int len() {
        return e - s + 1;
    }

    public Interval2 mergeAdjacent(Interval2 next) {
        if (next.e == this.s + 1) {
            return new Interval2(this.s, next.e);
        }
        return null;
    }
}