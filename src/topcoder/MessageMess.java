package topcoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MessageMess {

    public String restore(String[] dictionary, String message) {
        Set<String> set = new HashSet<>();
        for (String d : dictionary) {
            set.add(d);
        }
        return restoreRec(set, message, 0);
    }

    public String restoreRec(Set<String> remainingWords, String message, int startIndex) {
        if (startIndex >= message.length()) {
            return "";
        }
        Set<String> possibleMatches = new HashSet<>();
        for (String word : remainingWords) {
            int i1 = startIndex;
            int i2 = 0;
            while (i1 < message.length() && i2 < word.length()) {
                if (message.charAt(i1) != word.charAt(i2)) {
                    break;
                } else {
                    i1++;
                    i2++;
                }
            }
            if (i2 == word.length()) {
                possibleMatches.add(word);
            }
        }

        int numSolution = 0;
        String subSolution = "";
        String finalChoice = "";
        for (String choice : possibleMatches) {

            /*Set<String> remaining = new HashSet<>(remainingWords);
            remaining.remove(choice);*/
            int newstart = startIndex + choice.length();
            String solution = restoreRec(remainingWords, message, newstart);
            if ("AMBIGUOUS!".equals(solution)) {
                return "AMBIGUOUS!";
            }
            if (!"IMPOSSIBLE!".equals(solution)) {
                finalChoice = choice;
                subSolution = solution;
                numSolution++;
            }
            if (numSolution > 1) {
                return "AMBIGUOUS!";
            }
        }
        if (numSolution == 0) {
            return "IMPOSSIBLE!";
        }
        if (subSolution.isEmpty()) {
            return finalChoice;
        }
        else return finalChoice + " "+subSolution;
    }

/*
    public static void main(String[] args) {
        String[] dict = {"HI", "YOU", "SAY"};
        String message = "HIYOUSAYHI";
        System.out.println(new MessageMess().restore(dict, message));

    }
*/


}
