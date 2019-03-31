package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class PoisonousTree {

    // Complete the poisonousPlants function below.
    static int poisonousPlants(int[] p) {
        Map<Integer, Integer> closestSmallerToLeft = new HashMap<Integer, Integer>();
        Map<Integer, Integer> lives = new HashMap<>();
        closestSmallerToLeft.put(0, 0);
        lives.put(0,0);
        int maxLife = 0;
        for (int i = 1; i < p.length; i++) {

            if (p[i] > p[i-1]) {
                closestSmallerToLeft.put(i, i-1);
                int life = 1;
                if (life > maxLife) {
                    maxLife = life;
                }
                lives.put(i, life);

            } else {
                int nextIndex = i-1;
                closestSmallerToLeft.put(i, 0);
                do {
                    int parent = closestSmallerToLeft.get(nextIndex);
                    if (parent >= 0 && p[i] > p[parent]) {
                        int life = lives.get(nextIndex) + 1;
                        if (life > maxLife) {
                            maxLife = life;
                        }
                        lives.put(i, life);
                        closestSmallerToLeft.put(i, parent);
                        break;
                    }
                    nextIndex = parent;
                } while (nextIndex > 0);

            }
        }
        return maxLife;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] p = new int[n];

        String[] pItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pItem = Integer.parseInt(pItems[i]);
            p[i] = pItem;
        }

        int result = poisonousPlants(p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
