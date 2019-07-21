package util;

import java.util.*;

public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        int numPlaced = 0;
        int lastPlacedHeight = Integer.MAX_VALUE;
        int[][]result = new int[people.length][people[0].length];
        Set<Pair> data = new HashSet<>();
        Map<Pair, Integer> effectiveKs = new HashMap<>();
        int curIndex = 0;

        for (int i = 0; i < people.length; i++) {
            Pair p = new Pair(people[i][0], people[i][1]);
            data.add(p);
            effectiveKs.put(p, p.y);
        }

        for (int i = 0; i < people.length; i++) {
            //for (int l = 0; l < result.length; l++) System.out.print(result[l][0]+":"+result[l][1]+",");
            int minHeight = Integer.MAX_VALUE;
            Pair minPair = null;
            for (Pair p : data) {
                if (effectiveKs.get(p) == 0) {
                    if (p.x < minHeight) {
                        minHeight = p.x;
                        minPair = p;
                    }
                }
            }

            data.remove(minPair);
            effectiveKs.remove(minPair);

            for (Pair p : effectiveKs.keySet()) {
                if (minPair.x >= p.x) {
                    effectiveKs.put(p, effectiveKs.get(p) - 1);
                }
            }
            result[curIndex][0] = minPair.x;
            result[curIndex][1] = minPair.y;
            curIndex++;
        }
        return result;
    }

/*    public static void main(String[] args) {
        int[][] input = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] result = new ReconstructQueue().reconstructQueue(input);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0]+","+result[i][1]);
        }
    }*/

}
class Tuple {
    int i,j,k;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;
        Tuple tuple = (Tuple) o;
        return i == tuple.i &&
                j == tuple.j &&
                k == tuple.k;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, k);
    }


}

