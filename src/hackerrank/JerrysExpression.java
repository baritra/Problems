package hackerrank;

import java.util.*;

public class JerrysExpression {


    public static List<Integer> solve(String expression) {

        Map<Integer, Integer> positiveOpearandIndices = new TreeMap<>((i,j) -> i-j);
        Map<Integer, Integer> negativeOpearndIndices = new TreeMap<>((i,j) -> i-j);
        separateSigns(expression, 0, 1, positiveOpearandIndices, negativeOpearndIndices);
        int target = Math.max(positiveOpearandIndices.size(), negativeOpearndIndices.size());

        int remainingVal = target;

        Iterator it = positiveOpearandIndices.keySet().iterator();
        while(it.hasNext()) {
            int index = (int) it.next();
            if (!it.hasNext()) {
                positiveOpearandIndices.put(index, remainingVal);
            } else {
                positiveOpearandIndices.put(index, 1);
                remainingVal --;
            }

        }

        remainingVal = target;

        it = negativeOpearndIndices.keySet().iterator();
        while(it.hasNext()) {
            int index = (int) it.next();
            if (!it.hasNext()) {
                negativeOpearndIndices.put(index, remainingVal);
            } else {
                negativeOpearndIndices.put(index, 1);
                remainingVal --;
            }

        }

        Map<Integer, Integer> allIndices = new TreeMap<>((i,j) -> i-j);
        allIndices.putAll(positiveOpearandIndices);
        allIndices.putAll(negativeOpearndIndices);

        List<Integer> result = new ArrayList<>();
        for (Map.Entry e : allIndices.entrySet()) {
            result.add((int) e.getValue());
        }

        return result;




    }


    public static int separateSigns(String expression, int curIndex, int enclosingSign, Map<Integer, Integer> positiveOpearandIndices, Map<Integer, Integer> negativeOpearndIndices) {
        if (curIndex >= expression.length()) {
            return -1;
        }
        if (expression.charAt(curIndex) == '?') {
            if (enclosingSign == 1)  {
                positiveOpearandIndices.put(curIndex, 0);
            } else {
                negativeOpearndIndices.put(curIndex, 0);
            }
            return curIndex+1;
        } else if (expression.charAt(curIndex) == '+') {
            int firstOperandEnd = separateSigns(expression, curIndex+1, enclosingSign*1, positiveOpearandIndices, negativeOpearndIndices);
            int secondOperandEnd = separateSigns(expression, firstOperandEnd, enclosingSign*1, positiveOpearandIndices, negativeOpearndIndices);
            return secondOperandEnd;
        } else  {
            int firstOperandEnd = separateSigns(expression, curIndex+1, enclosingSign*1, positiveOpearandIndices, negativeOpearndIndices);
            int secondOperandEnd = separateSigns(expression, firstOperandEnd, enclosingSign*(-1), positiveOpearandIndices, negativeOpearndIndices);
            return secondOperandEnd;
        }

    }




    public static void main(String[] args) {
        List<Integer> result = JerrysExpression.solve("+------???????-??");
        System.out.println(result);
    }

}
