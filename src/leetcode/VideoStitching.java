package leetcode;

import java.util.*;

public class VideoStitching {
    public int videoStitching(int[][] clips, int T) {
        if (clips.length == 0) {
            return -1;
        }
        List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < clips.length; i++) {
            ranges.add(new Range(clips[i][0], clips[i][1]));
        }
        Collections.sort(ranges, (r1, r2) -> r1.i - r2.i);
        List<Integer> selected = new ArrayList<>();
        int lastEndpoint = 0;
        int lastSelectedIndex = 0;
        int count = 0;
        while(count < ranges.size()){
            count++;
            int lastCandidate = binSearch(ranges, lastEndpoint, 0, ranges.size()-1);
            //search all the ranges whose starting point <= lastEndpoint and find the one with the max endpoint
            int maxEndpoint = -1;
            int maxEndpointIndex = -1;
            boolean found = false;
            while ( lastCandidate >= lastSelectedIndex && lastCandidate >= 0 && ranges.get(lastCandidate).i <= lastEndpoint) {
                if (selected.contains(lastCandidate)) {
                    lastCandidate--;
                    continue;
                }
                if (ranges.get(lastCandidate).j > maxEndpoint) {
                    maxEndpoint = ranges.get(lastCandidate).j;
                    maxEndpointIndex = lastCandidate;
                    found = true;
                }
                lastCandidate--;
            }
            if (found) {
                lastEndpoint = maxEndpoint;
                lastSelectedIndex = maxEndpointIndex;
                selected.add(maxEndpointIndex);
                if (maxEndpoint >= T) break;
            }

        }
        if (selected.isEmpty()) {
            return -1;
        }
        if (ranges.get(selected.get(selected.size() - 1)).j < T) {
            return -1;
        }
        return selected.size();
    }

    private int binSearch(List<Range> ranges, int t, int s, int e) {
        if (s > e) {
            return e;
        }
        int mid = (s + e)/2;
        if (ranges.get(mid).i == t) {
            return mid;
        }
        if (t > ranges.get(mid).i) {
            return binSearch(ranges, t, mid + 1, e);
        } else {
            return binSearch(ranges, t, s, mid-1);
        }
    }

/*    public static void main (String[] args) {
        int[][] clips = {
                {0,2},
                {4,6},
                {8,10},
                {1,9},
                {1,5},
                {5,9}

        };

        int[][] clips2 = {
                {0,1},
                {1,2}
        };
        int[][]clips3 = {
                {5,7},{1,8},{0,0}, {2,3}, {4,5}, {0,6}, {5, 10}, {7,10}
        };
        //[[5,7],[1,8],[0,0],[2,3],[4,5],[0,6],[5,10],[7,10]]
        //5


        System.out.println(new VideoStitching().videoStitching(clips, 10));
    }*/

//[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]

}

class Range {
    int i, j;
    public Range(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "Range{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}