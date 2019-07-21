package dailycoding;

import java.util.*;

/**
 * Given a list of numbers, create an algorithm that arranges them in order to form the largest possible integer. For example, given [10, 7, 76, 415], you should return 77641510.
 */
public class MaxConat {
    public int maxConcat(int[] a) {
        Map<Integer, List<Integer>> digitsMap = new HashMap<>();
        int totalDigits = 0;
        for (int i = 0; i < a.length; i++) {
            List<Integer> digits = toDigits(a[i]);
            totalDigits += digits.size();
            digitsMap.put(a[i], digits);
        }

        List<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            sorted.add(a[i]);
        }
        final int total = totalDigits;
        Map<Integer, Integer> worth = new HashMap<>();
        for (int val : sorted) {
            worth.put(val, val * (int) Math.pow(10, total - digitsMap.get(val).size()));
        }
        Collections.sort(sorted, (val1, val2) -> {
            return worth.get(val2) - worth.get(val1);

        });
        int result = 0;
        int multiplier = (int) Math.pow(10, total - 1);
        for (int val : sorted) {
            List<Integer> digits = digitsMap.get(val);
            for (int digit : digits) {
                result += digit * multiplier;
                multiplier/= 10;
            }
        }
        return result;
    }

    private List<Integer> toDigits(int a) {
        LinkedList<Integer> result = new LinkedList<>();
        int num = a;
        do {
            int d = num%10;
            result.addFirst(d);
            num = num/10;
        } while (num > 0);
        return result;

    }

    public static void main(String[] args) {
        int[] input = {10, 7, 76, 415};
        System.out.println(new MaxConat().maxConcat(input));
    }

}
