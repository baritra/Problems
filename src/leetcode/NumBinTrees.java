package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumBinTrees {
    public int numTrees(int n) {
        return count(n, new HashMap<>());
       /* int count = 0;
        for (int i = 1; i <= n; i++) {
            int trees = count(n-i, i-1);
            System.out.println("Trees with root " + i + " = " + trees);
            count += trees;
        }
        return count;*/
    }

    public int count(int n, Map<Integer, Integer> cache) {
        if (n <= 1) {
            return 1;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int numTrees = 0;
        for (int i = 1; i <= n; i++) {
            int numSmaller = i - 1;
            int numGreater = n-i;
            numTrees += count(numSmaller, cache) * count(numGreater, cache);
        }
        cache.put(n, numTrees);
        return numTrees;
    }

    public int count(int numGreaterThanRoot, int numSmallerThanRoot) {
        if (numGreaterThanRoot == 0 && numSmallerThanRoot == 0) {
            return 1;
        }
        //numsmaller is a monotonically increasing sequence
        int leftCount = 1;
        int rightCount = 1;
        if (numSmallerThanRoot <= 1) {
            leftCount = 1;
        }
        else {
            for (int leftRootIndex = 0; leftRootIndex < numSmallerThanRoot; leftRootIndex++) {
                // for the i-th index there are i smaller, and numSmallerThanRoot - i - 1 bigger
                leftCount += count(numSmallerThanRoot - 1 - leftRootIndex, leftRootIndex);
            }
        }
        if (numGreaterThanRoot <= 1) {
            rightCount = 1;
        } else {
            for (int rightRootIndex = 0; rightRootIndex < numGreaterThanRoot; rightRootIndex++) {
                // for the i-th index there are i smaller, and numSmallerThanRoot - i - 1 bigger
                leftCount += count(numGreaterThanRoot - 1 - rightRootIndex, rightRootIndex);
            }
        }

        return leftCount * rightCount;
    }

    public static void main(String[] args) {
        System.out.println(new NumBinTrees().numTrees(3));
    }

}
