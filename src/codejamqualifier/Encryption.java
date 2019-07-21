package codejamqualifier;

import java.util.*;

public class Encryption {

    public String decrypt(int[] encrypted, int max) {
        List<Tuple> decryptedCode = new ArrayList<>();
        Set<Integer> decryptedPrimes = new HashSet<>();
        Set<Integer> allPrimes = findAllPrimesUpTo(max);
        for (int encryptedVal : encrypted) {
            boolean success = false;
            for (int dprime : decryptedPrimes) {
                if (encryptedVal % dprime == 0) {
                    int newPrime = encryptedVal / dprime;
                    decryptedPrimes.add(newPrime);
                    decryptedCode.add(new Tuple(dprime, newPrime));
                    success = true;
                    break;
                }
            }
            if (!success) {
                for (int prime : allPrimes) {
                    if (encryptedVal % prime == 0) {
                        int newPrime = encryptedVal / prime;
                        decryptedPrimes.add(prime);
                        decryptedPrimes.add(newPrime);
                        decryptedCode.add(new Tuple(prime, newPrime));
                        break;

                    }
                }
            }
        }
        List<Integer> orderedPrimes = new ArrayList<>(decryptedPrimes);
        Collections.sort(orderedPrimes);
        Map<Integer, String> toAlphabet = new HashMap<>();
        for (int i = 0; i < orderedPrimes.size(); i++) {
            toAlphabet.put(orderedPrimes.get(i), "" + (char) ('A' + i));
        }
        LinkedList<String> decodedText = new LinkedList<>();
        Tuple t1 = decryptedCode.get(0);
        Tuple t2 = decryptedCode.get(1);
        int pivot = -1;
        if (t1.left == t2.left || t1.left == t2.right) pivot = t1.left;
        else if (t1.right == t2.left || t1.right == t2.right) pivot = t1.right;
        decodedText.addLast(toAlphabet.get(t1.left == pivot ? t1.right : t1.left));
        decodedText.addLast(toAlphabet.get(pivot));
        decodedText.addLast(toAlphabet.get(t2.left == pivot ? t2.right : t2.left));
        for (int i = 2; i < decryptedCode.size(); i++) {
            Tuple t = decryptedCode.get(i);
            if (decodedText.getLast().equals(toAlphabet.get(t.left))) {
                decodedText.addLast(toAlphabet.get(t.right));
            } else {
                decodedText.addLast(toAlphabet.get(t.left));
            }

        }
        String result = "";
        for (int i = 0; i < decodedText.size(); i++) {
            result += decodedText.get(i);
        }
        return result;
    }


    private Set<Integer> findAllPrimesUpTo(int n) {
        LinkedHashSet<Integer> primes = new LinkedHashSet<>();
        for (int i = 2; i <= n; i++) {
            primes.add(i);
        }
        int p = 2;
        boolean toContinue = false;

        do {
            int multiplied = p;
            for (int i = 2; multiplied <= n; i++) {
                multiplied = p * i;
                primes.remove(multiplied);
            }
            toContinue = false;
            for (int number : primes) {
                if (number > p) {
                    toContinue = true;
                    p = number;
                    break;
                }
            }
        } while (toContinue);

        return primes;

    }


    public static void main(String[] args) {
        Encryption solution = new Encryption();
        Scanner s = new Scanner(System.in);
        int testCases = Integer.parseInt(s.nextLine());
        List<String> results = new ArrayList<>();
        for (int i = 0; i < testCases; i++) {
            Scanner lineScan = new Scanner(s.nextLine());
            int max = lineScan.nextInt();
            int numCodes = lineScan.nextInt();
            lineScan = new Scanner(s.nextLine());
            int[] numbers = new int[numCodes];
            for (int j = 0; j < numCodes; j++) {
                numbers[j] = lineScan.nextInt();
            }
            results.add(solution.decrypt(numbers, max));
        }
        for (int i = 1; i <= testCases; i++) {
            System.out.println("Case #"+i+": "+results.get(i-1));
        }
        //System.out.println(new Encryption().decrypt(new int[]{217, 1891, 4819, 2291, 2987, 3811, 1739, 2491, 4717, 445, 65, 1079, 8383, 5353, 901, 187, 649, 1003, 697, 3239, 7663, 291, 123, 779, 1007, 3551, 1943, 2117,1679, 989, 3053}, 103));
    }

}

class Tuple {
    int left, right;

    public Tuple(int left, int right) {
        this.left = left;
        this.right = right;
    }
}
