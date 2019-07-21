package leetcode;

import java.util.*;

public class SubArrayK {
    public int subarraysWithKDistinct(int[] A, int K) {

        Map<Pair, Set<Integer>> distinctIntMap = new HashMap<>();
        Set<Integer> blacklist = new HashSet<>();
        int result = 0;
        for (int l = 1; l <= A.length; l++) {
            for (int i = 0; i <= A.length - l; i++) {
                if (blacklist.contains(i)) {
                    continue;
                }
                int j = i + l -1;
                Set<Integer> set = null;
                if (l == 1) {
                    set = new HashSet<>();
                    set.add(A[i]);
                    distinctIntMap.put(new Pair(i, j), set);
                } else if (distinctIntMap.containsKey(new Pair(i, j-1))){
                    set = new HashSet<>(distinctIntMap.get(new Pair(i, j-1)));
                    set.add(A[j]);

                    distinctIntMap.put(new Pair(i, j), set);
                }
                if (set != null) distinctIntMap.put(new Pair(i, j), set);
                if (set.size() == K) {
                    //System.out.println(set);
                    result++;
                } else if (set.size() > K) {
                    blacklist.add(i);
                }
            }
        }
        return result;
    }

    class Pair {
        int i, j;
        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return i == pair.i &&
                    j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public static void main(String[]args) {
        int[] val = {2690,798,5800};
        System.out.println(new SubArrayK().subarraysWithKDistinct(val, 360));
    }
}
