package codeforces;

/**
 * https://codeforces.com/problemset/problem/1430/E
 */
public class StringReverse {
    public int minReverseSteps(String s) {
        StringBuilder b = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            b.append(s.charAt(i));
        }
        String r = b.toString();
        String curString = s;
        int steps = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (r.charAt(i) != curString.charAt(i)) {
                char desired = r.charAt(i);
                int desiredAt = findFirstIndexOf(i+1, desired, curString);
                steps += (desiredAt - i);

                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < i; j++) {
                    builder.append(curString.charAt(j));
                }
                builder.append(curString.charAt(desiredAt));
                for (int j = i; j < curString.length(); j++) {
                    if (j != desiredAt) {
                        builder.append(curString.charAt(j));
                    }
                }
                curString = builder.toString();
            }
            System.out.println(curString + " steps " + steps);

        }
        return steps;
    }

    private int findFirstIndexOf(int start, char c, String s) {
        int firstIndex = -1;
        for (int i = s.length() - 1; i >= start; i--) {
            if (s.charAt(i) == c) {
                firstIndex = i;
            }
        }
        return firstIndex;
    }

    public static void main(String[] args) {
        System.out.println(new StringReverse().minReverseSteps("icpcsguru"));
    }
}
