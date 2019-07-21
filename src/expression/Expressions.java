package expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Expressions {

    int i = 0;

    public int evaluateInfix(String s) {
        Stack<Token> operators = new Stack<>();
        Stack<Token> operands = new Stack<>();
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-',1);
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('(', 3);
        Token token = next(s);
        do {
            if (Type.OPERAND == token.t) {
                operands.push(token);
            }
            else if (Type.OPERATOR == token.t) {
                if (token.op == '(') {
                    operators.push(token);
                } else if (token.op == ')') {
                    char op = operators.pop().op;
                    while (op != '(') {
                        int val2= operands.pop().val;
                        int val1 = operands.pop().val;
                        operands.push(new Token(Type.OPERAND, eval(val1, val2, op)));
                        op = operators.pop().op;
                    }

                } else if (operators.isEmpty() || operators.peek().op == '(') {
                    operators.push(token);
                } else if (precedence.get(token.op) > precedence.get(operators.peek().op)) {
                    operators.push(token);
                }
                else {
                    // evaluate the peek operator
                    char op = operators.pop().op;
                    int val2 = operands.pop().val;
                    int val1 = operands.pop().val;
                    operands.push(new Token(Type.OPERAND, eval(val1, val2, op)));
                    // push this operator
                    operators.push(token);
                }
            }
            token = next(s);

        } while (token != null);

        int sum = 0;
        while (!operators.isEmpty()) {
            char op = operators.pop().op;
            int val2= operands.pop().val;
            int val1 = operands.pop().val;
            int result = eval(val1, val2, op);
            operands.push(new Token(Type.OPERAND, result));
        }
        return operands.pop().val;

    }

    private int eval(int val1, int val2, char op) {
        switch(op) {
            case '+' : return val1 + val2;
            case '-': return val1 - val2;
            case '*': return val1*val2;
            case '/' : return val1/val2;

        }
        return 0;
    }

    private Token next(String s) {
        if (i >= s.length()) return null;
        while (s.charAt(i) == ' ') i++;
        if (Character.isDigit(s.charAt(i))) {
            int start = i;
            while(i < s.length() && Character.isDigit(s.charAt(i))) i++;
            int val = Integer.parseInt(s.substring(start, i));
            return new Token(Type.OPERAND, val);

        } else {
            Token t = new Token(Type.OPERATOR, s.charAt(i));
            i++;
            return t;

        }
    }


    public static void main(String[] args) {
        System.out.println(new Expressions().evaluateInfix("(12+24) * 2 - ((20+20+17))"));
    }
}


enum Type {
    OPERAND, OPERATOR
}

class Token {
    Type t;
    int val;
    char op;

    public Token(Type t, int val) {
        this.t = t;
        this.val = val;
    }

    public Token(Type t, char op) {
        this.t = t;
        this.op = op;
    }

    @Override
    public String toString() {
        if (t == Type.OPERAND) return val+"";
        else return op+"";
    }
}