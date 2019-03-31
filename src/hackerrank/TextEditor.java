package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * In this challenge, you must implement a simple text editor. Initially, your editor contains an empty string, . You must perform  operations of the following  types:
 *
 * append - Append string  to the end of .
 * delete - Delete the last  characters of .
 * print - Print the  character of .
 * undo - Undo the last (not previously undone) operation of type  or , reverting  to the state it was in prior to that operation.
 * Input Format
 *
 * The first line contains an integer, , denoting the number of operations.
 * Each line  of the  subsequent lines (where ) defines an operation to be performed. Each operation starts with a single integer,  (where ), denoting a type of operation as defined in the Problem Statement above. If the operation requires an argument,  is followed by its space-separated argument. For example, if  and , line will be 1 abcd.
 *
 * Constraints
 *
 * The sum of the lengths of all  in the input .
 * The sum of  over all delete operations .
 * All input characters are lowercase English letters.
 * It is guaranteed that the sequence of operations given as input is possible to perform.
 * Output Format
 *
 * Each operation of type  must print the  character on a new line.
 *
 * Sample Input
 *
 * 8
 * 1 abc
 * 3 3
 * 2 3
 * 1 xy
 * 3 2
 * 4
 * 4
 * 3 1
 * Sample Output
 *
 * c
 * y
 * a
 * Explanation
 *
 * Initially,  is empty. The following sequence of  operations are described below:
 *
 * . We append  to , so .
 * Print the  character on a new line. Currently, the  character is c.
 * Delete the last  characters in  (), so .
 * Append  to , so .
 * Print the  character on a new line. Currently, the  character is y.
 * Undo the last update to , making  empty again (i.e., ).
 * Undo the next to last update to  (the deletion of the last  characters), making .
 * Print the  character on a new line. Currently, the  character is a.
 */

public class TextEditor {

    Stack<String> editedTexts;

    public TextEditor() {
        editedTexts = new Stack<>();
        editedTexts.push("");
    }

    public void append(String s) {
        editedTexts.push(editedTexts.peek() + s);
    }

    public void delete(int k) {
        String previous = editedTexts.peek();
        editedTexts.push(previous.substring(0, previous.length() - k));
    }

    public Character print(int index) {
        return editedTexts.peek().charAt(index - 1);
    }

    public void undo() {
        editedTexts.pop();
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner s = new Scanner(System.in);
        List<String> outputs = new ArrayList<>();
        int lines = Integer.parseInt(s.nextLine());
        for(int i = 0; i < lines; i ++) {
            String line = s.nextLine();
            Scanner lineScan = new Scanner(line);
            int command = lineScan.nextInt();
            switch (command) {
                case 1:
                    String appendString = lineScan.next();
                    editor.append(appendString);
                    break;
                case 2:
                    int k = lineScan.nextInt();
                    editor.delete(k);
                    break;
                case 3:
                    int index = lineScan.nextInt();
                    outputs.add(""+editor.print(index));
                    break;
                case 4:
                    editor.undo();
            }

        }
        for (String output : outputs) {
            System.out.println(output);
        }
    }

}
