package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class CrackSafe {
    public String crackSafe(int n, int k) {

        Map<Integer, Set<String>> permutations= new HashMap<>();

        for (int l = 1; l <= n; l++) {
            if (l == 1) {
                Set<String> set = new HashSet<>();
                for (int i = 0; i < k; i++) {
                    set.add(new String(""+i));
                }
                permutations.put(l, set);
            } else {

                Set<String> set = new HashSet<>();
                for (String p : permutations.get(l-1)) {
                    for (int i = 0; i < k; i++) {
                        set.add(new String(p + i));

                    }
                }
                permutations.put(l, set);
            }
        }



        String result = permutations.get(n).iterator().next();
        int target = permutations.get(n).size();
        Set<String> coveredCombinations = new HashSet<>();
        coveredCombinations.add(result);
        int count = 1;
        int startIndex = result.length() - n + 1;


        while (count < target) {

            String prefix = "";
            if (startIndex < result.length()) prefix = result.substring(startIndex);
            boolean success = false;
            for (String suffix : permutations.get(n - prefix.length())) {
                if (! coveredCombinations.contains(prefix + suffix)) {
                    String newResult = result + suffix;
                    coveredCombinations.add(newResult);
                    result = newResult;
                    count++;
                    success = true;
                }
            }
            if (success) startIndex = result.length() - n + 1;
            else startIndex++;


        }

        return result;

    }

    public static void main(String[] args) {
        System.out.println(new CrackSafe().crackSafe(2, 2));
    }
}
