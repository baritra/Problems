package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClumsyFactorial {
    public int clumsy(int N) {
        String[] operationOrder = {"*", "/", "+", "-"};
        List<Element> elements = new ArrayList<>();
        int opIndex = 0;
        for (int i = N; i >= 1; i--) {
            elements.add(new Element(i));
            if (i > 1) {
                elements.add(new Element(operationOrder[(opIndex++)%operationOrder.length]));
            }
        }
        Stack<Element> stack = new Stack<>();
        stack.push(new Element("+"));
        for (Element e : elements) {
            if (stack.isEmpty()) {
                stack.push(e);
            } else if (!e.operation.isEmpty()) {
                stack.push(e);
            } else {
                String lastOperation = stack.peek().operation;
                if (lastOperation.equals("/") || lastOperation.equals("*")) {
                    stack.pop();
                    int operator1 = stack.pop().num;
                    stack.push(new Element(lastOperation.equals("/") ? operator1/e.num : operator1*e.num));
                } else {
                    stack.push(e);
                }
            }
        }

        int result = 0;
        while(!stack.isEmpty()) {
            int num = stack.pop().num;
            String operation = stack.pop().operation;
            if (operation.equals("+")) {
                result += num;
            } else if (operation.equals("-")) {
                result -= num;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ClumsyFactorial().clumsy(10));
    }
}

class Element {
    int num = 0;
    String operation = "";

    public Element(int num) {
        this.num = num;
    }

    public Element(String operation) {
        this.operation = operation;
    }


}

