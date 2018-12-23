import java.util.ArrayList;
import java.util.List;

/**
 *
 Small input
 5 points
 Solve B-small
 Large input
 15 points
 Solve B-large
 Problem
 Tatiana likes to keep things tidy. Her toys are sorted from smallest to largest, her pencils are sorted from shortest to longest and her computers from oldest to newest. One day, when practicing her counting skills, she noticed that some integers, when written in base 10 with no leading zeroes, have their digits sorted in non-decreasing order. Some examples of this are 8, 123, 555, and 224488. She decided to call these numbers tidy. Numbers that do not have this property, like 20, 321, 495 and 999990, are not tidy.

 She just finished counting all positive integers in ascending order from 1 to N. What was the last tidy number she counted?

 Input
 The first line of the input gives the number of test cases, T. T lines follow. Each line describes a test case with a single integer N, the last number counted by Tatiana.

 Output
 For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is the last tidy number counted by Tatiana.

 Limits
 1 ≤ T ≤ 100.
 Small dataset
 1 ≤ N ≤ 1000.
 Large dataset
 1 ≤ N ≤ 1018.

 */
public class TidyNumbers {

    public String tidy(String s) {
        String cur = s;
        String result = "";

        while (true) {
            Payload p = fixFirst(cur);
            if (p.result) {
                for (int i = 0; i < p.s.length(); i++) {
                    if (p.s.charAt(i) != '0') result += p.s.charAt(i);
                }
                return result;
            }
            cur = p.s;
        }

    }

    public static void main(String[] args) {
        System.out.println(new TidyNumbers().tidy("989999999999999999"));
    }




    public Payload fixFirst(String s) {
        String transformed = "";
        boolean done = true;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && (s.charAt(i) - '0') > (s.charAt(i + 1) - '0')) {
                done = false;
                transformed += ((s.charAt(i) - '0') - 1) + "";
                transformed += '9';
                for (int j = i + 2; j < s.length(); j++) {
                    transformed += '9';
                }
                return new Payload(transformed, done);
            } else {
                transformed += s.charAt(i);
            }
        }
        return new Payload(transformed, done);
    }


    public List<Integer> stringToDigits(String s) {
        List<Integer> digits = new ArrayList();
        for (int i = 0; i < s.length(); i++) {
            digits.add(s.charAt(i) - '0');
        }
        return digits;
    }

    class Payload {
        String s;
        boolean result;

        public Payload(String s, boolean result) {
            this.s = s;
            this.result = result;
        }
    }


}








