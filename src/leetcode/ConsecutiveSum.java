package leetcode;

import java.util.Arrays;

public class ConsecutiveSum {

    public int mergeStonesInternal(int[] stones, int K) {
        if (stones.length < K) {
            return -1;
        } else if (stones.length == K) {
            int sum = 0;
            for (int stone : stones) {
                sum += stone;
            }
            return sum;
        }

        int minstart = 0;
        int minend = K - 1;
        int minsum = 0;
        int i,j;
        for (i = minstart; i <= minend; i++) {
            minsum += stones[i];
        }
        int lastSum = minsum;

        for (i = minstart + 1, j = minend+1; j < stones.length; j++, i++) {
            lastSum = lastSum - stones[i-1] + stones[j];

            if (lastSum < minsum) {
                minstart = i;
                minend = j;
                minsum = lastSum;
            }
        }

        int[] reducedStones = new int[stones.length - K + 1];
        for (i = 0; i < minstart; i++) {
            reducedStones[i] = stones[i];
        }
        reducedStones[i] = minsum;
        for (i = minstart + 1, j = minend+1; j < stones.length; j++) {
            reducedStones[i++] = stones[j];
        }

        int reducedSum = mergeStonesInternal(reducedStones, K);
        if (reducedSum != -1) {
            return minsum+reducedSum;
        }
        else return -1;

    }

    public static void main(String[] args) {
        System.out.println(new ConsecutiveSum().mergeStonesInternal(new int[]{3,2,4,1}, 2));
    }
}
