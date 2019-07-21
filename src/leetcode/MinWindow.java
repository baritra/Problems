package leetcode;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class MinWindow {
    public String minWindow(String s, String t) {
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            chars.add(t.charAt(i));
        }

        Map<Character, Integer> tracker = new LinkedHashMap<>();
        int start = 0, end = 0;
        int min = -1;
        int minstart = 0;
        int minEnd = 0;

        while(!chars.contains(s.charAt(start))) {
            start++; end++;
        }
        if (start == s.length()) {
            return "";
        }
        tracker.put(s.charAt(start), start);
        end++;

        while(end < s.length()) {
            if (chars.contains(s.charAt(end))) {
                if (!tracker.containsKey(s.charAt(end))) {
                    //new char of t found
                    tracker.put(s.charAt(end), end);
                    if (tracker.size() == chars.size()) {
                        //matching subsequence found
                        //System.out.println("Found match " + s.substring(start, end+1));
                        if (min == -1 || min > (end - start + 1)) {
                            min = end - start + 1;
                            minstart = start;
                            minEnd = end;
                        }
                        //shift the start to the next matching character
                        tracker.remove(s.charAt(start));
                        if (tracker.isEmpty()) {
                            start = end;
                        } else {
                            start = tracker.values().iterator().next();
                        }

                    }
                } else {
                    // we had found it earlier.
                    // if this is the start of the window, we can shift start to the next match;
                    if (s.charAt(start) == s.charAt(end)) {
                        tracker.remove(s.charAt(start));
                        tracker.put(s.charAt(end), end);

                        if (tracker.isEmpty()) {
                            start = end;
                        } else {
                            start = tracker.values().iterator().next();
                        }
                    }
                }
            }
            end++;
        }

        if (min == -1) {
            return "";
        } else {
            return s.substring(minstart, minEnd+1);
        }

    }

    /*public static void main(String[] args) {
        System.out.println(new MinWindow().minWindow("ADOBECODEBANC", "ABC"));
    }*/
}
