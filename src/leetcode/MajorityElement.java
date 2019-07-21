package leetcode;

import java.util.*;

public class MajorityElement {

    private Set<Integer> mooreVoting(int[] nums, int start, int end) {
        Set<Integer> candidates = new HashSet<>();
        if (start > end || nums.length == 0) {
            return candidates;
        }
        int candidate = nums[start];
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            if (candidate == nums[i]) {
                count ++;
            } else {
                count --;
            }
        }

        int frequency = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] == candidate) {
                frequency ++;
            }
        }
        if (frequency >= (end-start + 1)/2) {
            candidates.add(candidate);
        }

        candidate = nums[end];
        frequency = 0;
        count = 0;
        for (int i = end ; i >= start; i--) {
            if (count == 0) {
                candidate = nums[i];
            }
            if (candidate == nums[i]) {
                count ++;
            } else {
                count --;
            }
        }

        for (int i = start; i <= end; i++) {
            if (nums[i] == candidate) {
                frequency ++;
            }
        }
        if (frequency >= (end-start + 1)/2) {
            candidates.add(candidate);
        }

        return candidates;


    }



    public List<Integer> majorityElement(int[] nums) {
        Set<Integer> candidates = new HashSet<>();
        Set<Integer> result = new HashSet<>();

        candidates.addAll(mooreVoting(nums, 0, nums.length/3));
        candidates.addAll(mooreVoting(nums, nums.length/3 + 1, (2*nums.length)/3));
        candidates.addAll(mooreVoting(nums, (2*nums.length)/3 + 1, nums.length - 1));
        if (candidates.isEmpty()) {
            return new ArrayList<>();
        }

        for (int candidate : candidates) {
            if (freq(nums, candidate) > nums.length/3) {
                result.add(candidate);
            }
        }
        return new ArrayList<>(result);

    }

    private int freq(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                count++;
            }
        }
        return count;
    }




    class Pair {
        int candidate;
        double count;
        public Pair(int candidate, double count) {
            this.candidate = candidate;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        int[] nums = {8,9,8,9,8};
        System.out.println(new MajorityElement().majorityElement(nums));
    }
}
