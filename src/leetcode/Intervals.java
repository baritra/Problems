package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Intervals {

    public int[][] merge(int[][] intervals) {
        List<Interval> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            list.add(new Interval(intervals[i][0], intervals[i][1]));
        }
        Collections.sort(list, (i1, i2) -> i1.left - i2.left);
        Stack<Interval> s = new Stack<>();
        for (Interval i : list) {
            if (s.isEmpty()) {
                s.push(i);
            } else {
                Interval top = s.peek();
                Interval merged = top.merge(i);
                if (merged != null) {
                    s.pop();
                    s.push(merged);
                } else {
                    s.push(i);
                }

            }
        }
        int[][]result = new int[s.size()][2];
        int index = s.size() - 1;
        for (Interval i : s) {
            result[index][0] = i.left;
            result[index][1] = i.right;
            index--;
        }
        return result;
    }

    /*public static void main(String[] args) {
        Intervals intervals = new Intervals();
        int[][] input = {{1,3},{2,6},{8,10},{15,18}};
        int[][] result = intervals.merge(input);
        System.out.println("done");
    }*/
}


class Interval {
    int left, right;
    public Interval(int i, int j) {
        this.left = i;
        this.right = j;
    }

    public Interval merge(Interval other) {
        Interval lower = this.left < other.left ? this : other;
        Interval higher = this.left >= other.left ? this : other;

        if (higher.left > lower.right) {
            return null;
        } else {
            return new Interval(lower.left, higher.right);
        }


    }
}
