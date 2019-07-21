package leetcode;

class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int remainingLines = rows;
        int remainingChar = cols;

        int i = 0;
        int count = 0;
        while (remainingLines > 0) {
            if (count > 0 && remainingChar == cols) {
                //we are at the beginning of a line and starting the sentence all over again
                int lineSpent = rows - remainingLines;
                int countAchieved = count;
                if (remainingLines > lineSpent) {
                    count += (remainingLines / lineSpent) * count;
                    remainingLines = remainingLines % lineSpent;
                    //System.out.println("count = "+ count + "remainingLines = " + remainingLines);

                }
                 }
            if (remainingLines > 0) {
                String s = sentence[i];
                if (s.length() <= remainingChar) {
                    //place the word
                    remainingChar -= s.length();
                    if (remainingChar > 0) remainingChar--; // for the space after the word
                    if (remainingChar == 0) {
                        remainingLines--;
                        remainingChar = cols;
                    }
                    // if last word placed increment count
                    if (i == sentence.length - 1) count++;
                    // point to the next word
                    i = (i + 1) % sentence.length;
                } else {
                    remainingLines--;
                    remainingChar = cols;
                }
            }
        }


        return count;
    }


}