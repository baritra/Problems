package codeforces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 You are given a string 𝑠s of length 𝑛n consisting of lowercase Latin letters. You may apply some operations to this string: in one operation you can delete some contiguous substring of this string, if all letters in the substring you delete are equal. For example, after deleting substring bbbb from string abbbbaccdd we get the string aaccdd.

 Calculate the minimum number of operations to delete the whole string 𝑠s.

 Input
 The first line contains one integer 𝑛n (1≤𝑛≤5001≤n≤500) — the length of string 𝑠s.

 The second line contains the string 𝑠s (|𝑠|=𝑛|s|=n) consisting of lowercase Latin letters.

 Output
 Output a single integer — the minimal number of operation to delete string 𝑠s.

 Examples
 inputCopy
 5
 abaca
 outputCopy
 3
 inputCopy
 8
 abcddcba
 output
 */

public class StringClearing {

    public int clearString(String s, Map<String, Integer> memoized) {
        if (s.isEmpty()) {
            return 0;
        }
        if (memoized.containsKey(s)) {
            return memoized.get(s);
        }
        List<SubStringRange> components = getComponents(s);
        int minSteps = Integer.MAX_VALUE;
        for (SubStringRange component : components) {
            String substring = clearComponent(s, component);
            int steps = clearString(substring, memoized);
            if (steps < minSteps) {
                minSteps = steps;
            }
        }
        int thisSteps = minSteps + 1;
        memoized.put(s, thisSteps);
        return thisSteps;
    }


    private List<SubStringRange> getComponents(String s) {
        int start = 0, end = 0;

        List<SubStringRange> components = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(start)) {
                end = i;

            } else {
                components.add(new SubStringRange(start, end));
                start = i;
                end = i;
            }
        }
        components.add(new SubStringRange(start, end));
        return components;
    }

    private String clearComponent(String s, SubStringRange component) {
        String returnString = "";
        for (int i = 0; i < s.length(); i ++ ) {
            if (i < component.start || i > component.end) {
                returnString += s.charAt(i);
            }
        }
        return returnString;
    }

    public static void main(String args[]) {
        System.out.println(new StringClearing().clearString("abcddcba", new HashMap<>()));
    }


}

class SubStringRange {
    int start, end;

    public SubStringRange(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
