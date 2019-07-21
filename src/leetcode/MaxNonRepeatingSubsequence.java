package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MaxNonRepeatingSubsequence {
    public int lengthOfLongestSubstring2(String s) {
        int i = 0;
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        int maxLen = 0;
        while (i < s.length()) {
            // this marks the end of a sequence with no repeating characters
            if (lastIndexMap.containsKey(s.charAt(i))) {
                //found a subsequence with nonrepeating charcters
                if (lastIndexMap.size() > maxLen) {
                    maxLen = lastIndexMap.size();
                }
                lastIndexMap.put(s.charAt(i), i);
                i++;

            } else {
                lastIndexMap.put(s.charAt(i), i);
                if (i == s.length() - 1) {
                    if (lastIndexMap.size() > maxLen) {
                        maxLen = lastIndexMap.size();
                    }
                }
                i++;
            }
        }
        return maxLen;

    }

    public int lengthOfLongestSubstring(String s) {
        int i = 0;
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        int windowStart = 0;
        int windowEnd = -1;
        int maxLen = 0;
        while(windowEnd < s.length() - 1) {
            windowEnd ++;
            if (!lastIndexMap.containsKey(s.charAt(windowEnd))) {
                lastIndexMap.put(s.charAt(windowEnd), windowEnd);
                if (windowEnd == s.length() - 1) {
                    if (maxLen < (windowEnd - windowStart) + 1) {
                        maxLen = windowEnd - windowStart + 1;
                    }
                }

            } else {
                int lastIndex = lastIndexMap.get(s.charAt(windowEnd));
                windowEnd--;
                if ((windowEnd - windowStart + 1) > maxLen) {
                    maxLen = windowEnd - windowStart + 1;
                }


                for (int j = windowStart; j <= lastIndex; j++) {
                    lastIndexMap.remove(s.charAt(j));
                }


                windowStart = lastIndex + 1;
            }
        }

        return maxLen;


    }

    public static void main(String[] args) {
        System.out.println(new MaxNonRepeatingSubsequence().lengthOfLongestSubstring("abcabcbb"));
    }

}
