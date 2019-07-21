package codeforces;

import java.util.HashSet;
import java.util.Set;

public class PairCover {

    public boolean findPairToCover(int[][] allPairs) {
        int first = allPairs[0][1];

        Set<Integer> seconds = new HashSet<>();
        for (int i = 1; i < allPairs.length; i++) {
            int second = first;

            if (first != allPairs[i][0]) second = allPairs[i][0];
            if (first != allPairs[i][1]) second = allPairs[i][1];

            if (first != second) {
                seconds.add(second);
            }
        }
        if (seconds.size() == 1) {
            System.out.println(""+first + "," + seconds.iterator().next());
        }
        return seconds.size() <= 1;
    }
}
