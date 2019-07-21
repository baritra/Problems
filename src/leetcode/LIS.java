package leetcode;

import java.util.Objects;

public class LIS {

    public int lengthOfLIS(int[] nums) {
        Subsequence[] subsequences = new Subsequence[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                subsequences[i] = new Subsequence(1, 0);
            } else {
                int maxLength = 1;
                Subsequence maxSubsequence = new Subsequence(1, i);
                for (int k = i-1; k >=0; k--) {
                    Subsequence extendedSequnece = subsequences[k].extend(nums, i);
                    if (extendedSequnece != null && extendedSequnece.length > maxLength) {
                        maxSubsequence = extendedSequnece;
                        maxLength = extendedSequnece.length;
                    }
                }
                subsequences[i] = maxSubsequence;

            }
        }

        int maxLength = 0;
        for (Subsequence subsequence : subsequences) {
            if (subsequence.length > maxLength) {
                maxLength = subsequence.length;
            }
        }
        return maxLength;

    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(new LIS().lengthOfLIS(nums));
    }
}

class Subsequence {
    int length;
    int endIndex;

    public Subsequence(int length, int endIndex) {
        this.length = length;
        this.endIndex = endIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subsequence)) return false;
        Subsequence that = (Subsequence) o;
        return length == that.length &&
                endIndex == that.endIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, endIndex);
    }

    public Subsequence extend(int[] nums, int index) {
        if (nums[index] >= nums[endIndex]) {
            return new Subsequence(length + 1, index);
        }
        return null;
    }
}
