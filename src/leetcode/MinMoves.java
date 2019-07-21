package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMoves {
    public int minMoves(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        Collections.sort(list);
        int steps = 0;
        while(true) {
            int diff = list.get(list.size() - 1) - list.get(0);
            if (diff == 0) {
                break;
            }
            steps += diff;
            int max = Integer.MIN_VALUE;
            int maxIndex = -1;
            for (int j = 0; j < list.size() - 1; j++) {
                int newVal = list.get(j)+diff;
                if (newVal > max) {
                    max = newVal;
                    maxIndex = j;
                }
                list.set(j, newVal);
            }

            if (max > list.get(list.size() - 1)) {
                int temp = list.get(list.size() - 1);
                list.set(list.size() - 1, max);
                list.set(maxIndex, temp);
            }

        }
        return steps;

    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(new MinMoves().minMoves(nums));
    }
}
