package leetcode;

public class ShipWithDays {

    public int shipWithinDays(int[] weights, int D) {
        int[][]indexAndParitionLengthToMaxWeight = new int[weights.length][D+1];
        int runningSum = 0;
        for (int end = 0; end < weights.length; end++) {
            runningSum += weights[end];
            indexAndParitionLengthToMaxWeight[end][1] = runningSum;
        }
        for (int numPartition = 2; numPartition <= D; numPartition++) {
            for (int endIndex = numPartition - 1; endIndex < weights.length; endIndex++ ) {
                //recursively find out indexAndParitionLengthToMaxWeight[endIndex][numPartition]
                int previousPartition = numPartition - 1;
                int min = Integer.MAX_VALUE;
                int rightSum = weights[endIndex];
                for (int j = endIndex - 1; j >= (previousPartition - 1); j--) {
                    if (Math.max(indexAndParitionLengthToMaxWeight[j][previousPartition], rightSum) < min) {
                        min = Math.max(indexAndParitionLengthToMaxWeight[j][previousPartition], rightSum);
                    }
                    rightSum += weights[j];
                }
                indexAndParitionLengthToMaxWeight[endIndex][numPartition] = min;
            }
        }
        return indexAndParitionLengthToMaxWeight[weights.length - 1][D];

    }

    public static void main(String[] args) {
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(new ShipWithDays().shipWithinDays(weights, 5));
    }
}
