package codejamqualifier;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SplitCheck {
    public Pair splitCheck(int n) {
        LinkedList<Integer> digits = new LinkedList<>();
        int x = n;
        do {
            int remainder = x%10;
            digits.addLast(remainder);
            x = x/10;
        } while(x != 0);

        int closestNumberWithoutfour = 0;
        int multiplier = 1;
        for (int digit : digits) {
            closestNumberWithoutfour += (digit == 4? 3 : digit) * multiplier;
            multiplier *= 10;
        }
        return new Pair(closestNumberWithoutfour, (n - closestNumberWithoutfour));

    }

    public static void main(String[] args) {
        SplitCheck splitCheck = new SplitCheck();
        Scanner s = new Scanner(System.in);
        int testCases = Integer.parseInt(s.nextLine());
        List<Pair> results = new ArrayList<>();
        for (int i = 0; i < testCases; i++) {
            Scanner testCaseScanner = new Scanner(s.nextLine());
            int n = testCaseScanner.nextInt();
            results.add(splitCheck.splitCheck(n));
        }
        for (int i = 1; i <= testCases; i++) {
            System.out.println("Case #"+i+": "+results.get(i-1).left + " "+results.get(i-1).right);
        }
    }
}


class Pair {
    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }
    int left, right;
}

