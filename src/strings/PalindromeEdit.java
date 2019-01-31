package strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Google.
 *
 * Given a string which we can delete at most k, return whether you can make a palindrome.
 *
 * For example, given 'waterrfetawx' and a k of 2, you could delete f and x to get 'waterretaw'.
 *
 * Upgrade to premium and get in-depth solutions to every problem. Since you were referred by one of our affiliates, you'll get a 10% discount on checkout!
 *
 * If you liked this problem, feel free to forward it along so they can subscribe here! As always, shoot us an email if there's anything we can help with!
 */

public class PalindromeEdit {

    public boolean isPalindromeAfterRemoval(String s, int k) {
        int start1, end1, start2, end2;
        start1 = 0;
        end1 = s.length()%2 == 0 ? s.length()/2 - 1 : s.length()/2;
        start2 = s.length()%2 == 0 ? end1 + 1 : end1;
        end2 = s.length() - 1;

        int count = 0;
        while (count <= k) {
            boolean result = isSubSequence(s, start1, end1, start2, end2);
            if (result) return result;
            if (end1 == start2) {
                end1 --;
            } else {
                start2 --;
            }
            count ++;

        }

        start1 = 0;
        end1 = s.length()%2 == 0 ? s.length()/2 - 1 : s.length()/2;
        start2 = s.length()%2 == 0 ? end1 + 1 : end1;
        end2 = s.length() - 1;
        count = 0;
        while (count <= k) {
            boolean result = isSubSequence(s, start1, end1, start2, end2);
            if (result) return result;
            if (end1 == start2) {
                start2 ++;
                if (start2 > s.length() - 1) break;
            } else {
                end1 ++;
                if (end1 > s.length() - 1) break;

            }
            count ++;
        }
        return false;

    }

    public boolean isSubSequence(String source, int start1, int end1, int start2, int end2) {
        System.out.println("Testing subsequence " + source.substring(start1, end1 + 1) + ",   " + source.substring(start2, end2 + 1));
        int startSmall, endSmall, startBig, endBig;
        if ((end1 - start1) > (end2 - start2)) {
            startBig = start1;
            startSmall = start2;
            endBig = end1;
            endSmall = end2;
        } else {
            startBig = start2;
            startSmall = start1;
            endBig = end2;
            endSmall = end1;
        }
        int j = startSmall;
        Set<String> elimited = new HashSet<>();
        for (int i = endBig; i >= startBig; i--) {
            if (j <= endSmall && source.charAt(i) == source.charAt(j)) {
                j++;

            }
            else elimited.add(source.charAt(i)+"");
        }
        if (j == endSmall + 1) {
            System.out.println("Chars to be removed for palindrome = " + elimited);
        }
        return j == endSmall + 1;

    }

    public static void main(String[] args) {
        new PalindromeEdit().isPalindromeAfterRemoval("34abxcba", 3);
    }


}
