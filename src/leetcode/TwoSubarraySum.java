package leetcode;

import java.util.Arrays;
import java.util.Comparator;

class TwoSubarraySum {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int max = -1;
        for (int i = 0; i < A.length; i++) {
            int leftL = maxSubArray(A, L, 0, i);
            int leftM = maxSubArray(A, M, 0, i);
            int rightL = maxSubArray(A, L, i+1, A.length - 1);
            int rightM = maxSubArray(A, M, i+1, A.length - 1);

            if (leftL != -1 && rightM != -1) {
                if (leftL + rightM > max) {
                    max = leftL + rightM;
                }
            }

            if (leftM != -1 && rightL != -1) {
                if (leftM + rightL > max) {
                    max = leftM + rightL;
                }
            }
        }
        return max;

    }

    public int maxSubArray(int[] A, int len, int i, int j) {
        if (j - i < len) {
            return -1;
        }
        int max = -1;
        int curSum = 0;
        for (int s = i; s <= j - len + 1; s++) {

            if (max == -1) {
                max = 0;
                for (int k = s; k < s + len; k++) {
                    curSum += A[k];
                }
                max = curSum;
            } else  {
                curSum = curSum - A[s-1] + A[s + len - 1];
                if (curSum > max) {
                    max = curSum;
                }
            }

        }
        return max;

    }

   /* public static void main(String[] args) {
        int[] A = {0,6,5,2,2,5,1,9,4};
        int L = 1;
        int M = 2;
        System.out.println(new TwoSubarraySum().maxSumTwoNoOverlap(A, L, M));
    }*/
}

