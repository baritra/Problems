package leetcode;

import java.util.HashSet;
import java.util.Set;

public class ArrayLoop {

    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> indicesVisited = new HashSet<Integer>();
            int direction = nums[i] > 0 ? 1 : 0;
            for (int j = i, count = 0; count <= nums.length; count++, j = rotate(j, nums[j], nums.length)) {
                if (direction == 1 && nums[j] < 0) break;
                if (direction == 0 && nums[j] > 0) break;
                if (indicesVisited.contains(j)) {
                    return true;
                }
                indicesVisited.add(j);
            }
        }
        return false;

    }

    private int rotate(int curIndex, int steps, int size) {
        int newStep = 0;
        if (steps >= 0) {
            return (curIndex + steps) % size;
        } else {
            newStep = curIndex + steps;
            if (newStep >= 0) {
                return newStep;
            }
            int remainder = Math.abs(curIndex + steps);
            if (remainder == size) {
                newStep = 0;
            }
            else {
                newStep = size - Math.abs(curIndex + steps) % size;
            }
        }

        return newStep;
    }

    public static void main(String[] args) {
        System.out.println(new ArrayLoop().circularArrayLoop(new int[] {-1}));
    }


}
