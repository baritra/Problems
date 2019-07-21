package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MinRange {

    MyRange minRange = new MyRange(Integer.MIN_VALUE,Integer.MAX_VALUE);

    public int[] smallestRange(List<List<Integer>> nums) {
        int[] answer = new int[2];
        answer[0] = Math.min(minRange.i, minRange.j);
        answer[1] = Math.max(minRange.i, minRange.j);
        return answer;

    }

    public void findRange(MyRange incoming, int target, List<List<Integer>> nums, int curListIndex) {
        if (curListIndex == nums.size() - 1) {
            if (incoming.len() < minRange.len()) {
                minRange = incoming;
            }
            return;
        }
        List<Integer> thisList = nums.get(curListIndex);
        if (incoming == null) {
            for (int i = 0; i < thisList.size(); i++) {
                MyRange r = new MyRange(thisList.get(i), thisList.get(i));
                findRange(r, thisList.get(i), nums, curListIndex+1);
            }
        } else {
            Set<Integer> closests = closestValues(thisList, target);
            for (int val : closests) {
                MyRange r = incoming.expand(val);
                findRange(r, val, nums, curListIndex+1);
            }
        }
    }

    public Set<Integer> closestValues(List<Integer> nums, int target) {
        Set<Integer> answer = new HashSet<>();
        int floorIndex = binSearchFloor(nums, target, 0, nums.size() - 1);
        if (floorIndex != -1) {
            answer.add(nums.get(floorIndex));
        }
        if (floorIndex != nums.size() - 1) {
            answer.add(nums.get(floorIndex + 1));
        }
        return answer;

    }


    public int binSearchFloor(List<Integer> nums, int target, int s, int e) {
        if (s > e) {
            return -1;
        } else if (s == e) {
            return s;
        } else if (e == s+1) {
            return s;
        }
        int mid = (s+e)/2;
        if (target == nums.get(mid)) {
            return mid;
        }
        if (target > nums.get(mid)) {
            return binSearchFloor(nums, target, mid, e);
        } else {
            return binSearchFloor(nums, target, s, mid-1);
        }

    }
}

class MyRange {
    int i, j;
    public MyRange(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int len() {
        return Math.abs(i - j);
    }

    public MyRange expand(int newVal) {
        return new MyRange(Math.min(Math.min(i, j), newVal), Math.max(Math.max(i,j), newVal));
    }
}
