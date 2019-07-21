package arrays;

public class MaxSubArraySum {
    public int maxSubArraySum(int[] a) {
        int lastMaxSum = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > lastMaxSum) {
                lastMaxSum = a[i];
            }
        }
        int subarraySum = Integer.MIN_VALUE;
        int start = -1, end = -1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] >= 0) {
                if (start == -1) {
                    start = i;
                    end = i;
                } else {
                    end = i;
                }
                subarraySum += a[i];
            } else {
                //that's the end of the subarray.
                if (start != -1 && subarraySum > lastMaxSum) {
                    lastMaxSum = subarraySum;
                }
                start = -1;
                end = -1;
                subarraySum = Integer.MIN_VALUE;

            }
        }
        if (subarraySum > lastMaxSum) {
            lastMaxSum = subarraySum;
        }
        return lastMaxSum;
    }

    public static void main(String[] args) {
        int[]a = {-2,-3,10,10,-4,5,5,12,-3,40,40};
        int[]a2 = {-2,-40};
        System.out.println(new MaxSubArraySum().maxSubArraySum(a2));
    }

}
