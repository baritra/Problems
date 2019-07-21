package leetcode;

import java.util.*;

public class SpecialBinSearch {
    public int sparseBinSearchSmallerOrEqual(List<Integer> plist, int s, int e, int target) {
        int start = s;
        int end = e;
        while (start < plist.size() && plist.get(start++) != -1);
        while (end >= 0 &&plist.get(end--) != -1);
        if (start == -1 || end == plist.size()) {
            return -1;
        }
        start++;
        end--;

        int mid = (start + end)/2;
        int midRight = mid;
        int midLeft = mid;
        while(midRight < plist.size() && plist.get(midRight++) != -1);
        while(midLeft >= 0 && plist.get(midLeft--) != -1);
        if (midRight == plist.size() || midLeft == -1) {
            return -1;
        }
        midRight--;
        midLeft++;
        if (Math.abs(mid - midRight) > Math.abs(mid - midLeft)) {
            mid = midLeft;
        } else {
            mid = midRight;
        }
        int midVal = plist.get(mid);
        if (start == end) {
            return start;
        }
        if (start > end) {
            return -1;
        }
        if (plist.get(mid) == target) {
            return mid;
        }
        if (plist.get(mid) == -1) {
            return Math.max(sparseBinSearchSmallerOrEqual(plist, mid+1, end, target),
                    sparseBinSearchSmallerOrEqual(plist, start, mid-1, target));
        }
        if (target > plist.get(mid)) {
            return sparseBinSearchSmallerOrEqual(plist, mid, end, target);
        } else {
            return sparseBinSearchSmallerOrEqual(plist, start, mid-1, target);
        }
    }

    public int binSearchSmallerOrEqual(List<Integer> plist, int i, int j, int target) {
        int mid = (i+j)/2;
        if (j == i + 1) {
            if (plist.get(j) > target) {
                return i;
            }
            return j;
        }
        if (i == j) {
            return i;
        }
        if (i > j) {
            return -1;
        }
        if (plist.get(mid) == target) {
            return mid;
        }

        if (target > plist.get(mid)) {
            return binSearchSmallerOrEqual(plist, mid, j, target);
        } else {
            return binSearchSmallerOrEqual(plist, i, mid-1, target);
        }
    }

    public int numRescueBoats(int[] people, int limit) {
        Set<Integer> boated = new HashSet<>();
        List<Integer> plist = new ArrayList<>();
        for (int i = 0 ; i < people.length; i++) {
            plist.add(people[i]);
        }

        Collections.sort(plist);
        int boarded = 0;
        int remainingBoatCapacity = limit;
        int boatsDispatched = 0;
        while(boarded < people.length) {
            int index = binSearchSmallerOrEqual(plist, 0, plist.size() - 1, remainingBoatCapacity);
            if (index == -1) {
                //nobody else can fit in the boat, get a new one
                remainingBoatCapacity = limit;
                boatsDispatched++;
            }
            int weight = plist.get(index);
            if (weight <= remainingBoatCapacity) {
                remainingBoatCapacity -= weight;
                boarded++;
                plist.remove(index);
            } else {
                //cant fit
            }
            if (remainingBoatCapacity == 0 || boarded == people.length) {
                remainingBoatCapacity = limit;
                boatsDispatched++;
            }

        }

        return boatsDispatched;
    }


    public static void main(String[] args) {
        SpecialBinSearch s = new SpecialBinSearch();
        /*int[]a = {2,3,5};
        List<Integer> l = new ArrayList<>();
        Arrays.stream(a).forEach(val -> l.add(val));

        SpecialBinSearch s = new SpecialBinSearch();
        System.out.println(s.binSearchSmallerOrEqual(l, 0, a.length - 1, 4));
*/
        int[]people = {3,2,2,1};

        int limit = 3;
        System.out.println(s.numRescueBoats(people,limit));
    }
}
