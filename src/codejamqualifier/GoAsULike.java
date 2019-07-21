package codejamqualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoAsULike {
    public String findPath(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'E') {
                result = result + 'S';
            } else {
                result = result + 'E';
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GoAsULike solution = new GoAsULike();
        Scanner s = new Scanner(System.in);
        int testCases = Integer.parseInt(s.nextLine());
        List<String> results = new ArrayList<>();
        for (int i = 0; i < testCases; i++) {
            s.nextLine();
            String input = s.nextLine();
            results.add(solution.findPath(input));
        }
        for (int i = 1; i <= testCases; i++) {
            System.out.println("Case #"+i+": "+results.get(i-1));
        }
    }

}
