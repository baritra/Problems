package dailycoding;

import java.util.*;

/**
 *
 *
 * Given a string, split it into as few strings as possible such that each string is a palindrome.
 *
 * For example, given the input string racecarannakayak, return ["racecar", "anna", "kayak"].
 *
 * Given the input string abc, return ["a", "b", "c"].
 *
 * Upgrade to premium and get in-depth solutions to every problem. Since you were referred by one of our affiliates, you'll get a 10% discount on checkout!
 *
 * If you liked this problem, feel free to forward it along so they can subscribe here! As always, shoot us an email if there's anything we can help with!
 */

public class Palindromes {

    private Map<Range, List<String>> results = new HashMap<>();

    public List<String> minPalindromeSplit(String s) {
        int l = 1, i = 0, j = 0;

        for (i = 0; i < s.length(); i++) {
            List<String> result = new ArrayList<>();
            result.add("" + s.charAt(i));
            results.put(new Range(i, i), result);

            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i+1)) {
                result = new ArrayList<>();
                result.add(""+s.charAt(i)+s.charAt(i+1));
                results.put(new Range(i, i+1), result);
            }
        }

        for (l = 3; l <= s.length(); l++) {
            for (i = 0; i < (s.length() - 1 - l + 1); i++) {
                //best if you could form a single palindrome
                if (results.get(new Range(i+1, j-1)).size() == 1
                        && s.charAt(i) == s.charAt(j)) {
                    List<String> result = new ArrayList<>();
                    result.add(s.substring(i, j+1));
                } else {
                    int numPalindromes = -1;
                    int pivot = 0;
                    for (int k = i+1; i < j; k++) {
                        int palindromes = results.get(new Range(i, k)).size() + results.get(new Range(k+1, j)).size();
                        if (palindromes > numPalindromes) {
                            pivot = k;
                            numPalindromes = palindromes;
                        }
                    }
                    List<String> result = new ArrayList<>();
                    result.addAll(results.get(new Range(i, pivot)));
                    result.addAll(results.get(new Range(pivot + 1, j)));
                    results.put(new Range(i, j), result);
                }

            }
        }
        return results.get(new Range(0, s.length() - 1));
    }


}

class Range {
    int i, j;

    public Range(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Range)) return false;
        Range range = (Range) o;
        return i == range.i &&
                j == range.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
