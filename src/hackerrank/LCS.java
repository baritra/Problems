package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LCS {
    public static int commonChild(String s1, String s2) {
        Map<ProblemKey, ProblemVal> solutions = new HashMap<>();
        solutions.put(new ProblemKey(0,0), new ProblemVal(0, ""));

        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (i == 0 && j == 0) {
                    solutions.put(new ProblemKey(0,0), s1.charAt(i) == s2.charAt(j) ? new ProblemVal(1, ""+s1.charAt(i)): new ProblemVal(0, ""));
                    continue;
                }
                ProblemVal val = null;

                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i >= 1 && j >= 1) {
                        ProblemVal subVal = solutions.get(new ProblemKey(i - 1, j - 1));

                        ProblemVal newVal = new ProblemVal(subVal.length + 1, subVal.answer + s1.charAt(i));
                        if (val == null || newVal.length > val.length ) {
                            val = newVal;
                        }
                    }
                } else {
                    if (i >= 1) {
                        ProblemVal leftVal = solutions.get(new ProblemKey(i-1, j));
                        if (val == null || leftVal.length > val.length ) {
                            val = leftVal;
                        }
                    }
                    if (j >= 1) {
                        ProblemVal rightVal = solutions.get(new ProblemKey(i, j-1));
                        if (val == null || rightVal.length > val.length ) {
                            val = rightVal;
                        }
                    }
                }
                if (val == null) {
                    val = new ProblemVal(0, "");
                }
                solutions.put(new ProblemKey(i, j), val);
            }

        }
        ProblemVal solution = solutions.get(new ProblemKey(s1.length() - 1, s2.length() - 1));
        System.out.println(solution.answer);
        return solution.length;
    }

    public static void main (String [] args) {
        System.out.println(LCS.commonChild("SHINCHAN", "NOHARAAA"));
    }
}


class ProblemKey {
    int i, j;

    public ProblemKey(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProblemKey)) return false;
        ProblemKey that = (ProblemKey) o;
        return i == that.i &&
                j == that.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public String toString() {
        return "ProblemKey{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}

class ProblemVal {
    int length;
    String answer;

    public ProblemVal(int length, String answer) {
        this.length = length;
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProblemVal)) return false;
        ProblemVal that = (ProblemVal) o;
        return length == that.length &&
                Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, answer);
    }

    @Override
    public String toString() {
        return "ProblemVal{" +
                "length=" + length +
                ", answer='" + answer + '\'' +
                '}';
    }
}
