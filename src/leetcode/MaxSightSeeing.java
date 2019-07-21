package leetcode;

public class MaxSightSeeing {
    public int maxScoreSightseeingPair(int[] A) {
        return maxScoreSightseeingPair(A, 0, A.length - 1);

    }

    public int maxScoreSightseeingPair(int[] A, int start, int end) {
        System.out.println("Start = " + start + " End = " + end);
        if (start >= end) {
            return -1;
        }
        if (start == end - 1 || start == end + 1) {
            return A[start] + A[end] - 1;
        }

        int mid = (start + end)/2;
        int leftSolution =  maxScoreSightseeingPair(A, start, mid - 1);
        int rightSolution = maxScoreSightseeingPair(A, mid, end);
        // solution that spans across the divide
        //find the closest but biggest number in the left from mid
        int distance = 1;
        int maxleft = Integer.MIN_VALUE;
        for (int j = mid - 1; j >= start; j--) {
            if ((A[j] - distance) > maxleft) {
                maxleft = A[j] - distance;
            }
            distance++;
        }
        distance = 0;
        int maxRight = Integer.MIN_VALUE;
        for (int j = mid; j <= end; j++) {
            if ((A[j] - distance) > maxRight) {
                maxRight = A[j] - distance;
            }
            distance++;
        }

        int spreadSolution = maxleft + maxRight;
        return Math.max(Math.max(leftSolution, spreadSolution), rightSolution);

    }

    public static void main(String[]args) {
        int[] nums = {8,1,5,2,6};
        System.out.println(new MaxSightSeeing().maxScoreSightseeingPair(nums));
    }
}
