package expression;

import java.util.HashMap;
import java.util.Map;

public class Regex {

    public boolean isMatch(String s, String p) {
        if (p == null || p.isEmpty()) return false;
        FA fa = buildFA(p, 0);
        return accept(fa, fa.start, s, 0);
    }

    boolean accept(FA fa, State cur, String s, int i) {
        if (i == s.length()) {
            if (!cur.transitions.containsKey('0'))
                return cur == fa.end;
            else return accept(fa, cur.transitions.get('0'), s, i);

        }
        if (cur.transitions.containsKey(s.charAt(i))) {
            boolean result = accept(fa, cur.transitions.get(s.charAt(i)), s,i+1);
            if (result) return result;
        }

        if (cur.transitions.containsKey('.')) {
            boolean result = accept(fa, cur.transitions.get('.'), s,i+1);
            if (result) return result;
        }

        if (cur.transitions.containsKey('0')) {
            boolean result = accept(fa, cur.transitions.get('0'), s,i);
            if (result) return result;
        }
        return false;
    }

    public FA buildFA(String s, int i) {
        if (i >= s.length()) return null;
        if (i == s.length() - 1 || s.charAt(i+1) != '*') {
            FA fa = singleFA(s.charAt(i));
            FA nextFA = buildFA(s, i+1);
            if (nextFA == null) return fa;
            fa.end.transitions.put('0', nextFA.start);
            return new FA(fa.start, nextFA.end);
        } else {
            FA fa = closureFA(s.charAt(i));
            FA nextFA = buildFA(s, i+2);
            if (nextFA == null) return fa;
            fa.end.transitions.put('0', nextFA.start);

            return new FA(fa.start, nextFA.end);
        }

    }


    private static FA closureFA(char c) {
        State start = new State((c == '.' ? "dot" : c)+"*-start");
        State end = new State((c == '.' ? "dot" : c)+"*-end");
        State mid = new State((c == '.' ? "dot" : c)+"*-mid");
        start.transitions.put('0', end);
        start.transitions.put(c, mid);
        mid.transitions.put(c, mid);
        mid.transitions.put('0', end);
        return new FA(start, end);
    }

    private static FA singleFA(char c) {
        State start = new State((c == '.' ? "dot" : c)+"-start");
        State end = new State((c == '.' ? "dot" : c)+"-end");
        start.transitions.put(c, end);
        return new FA(start, end);
    }

    public static void main(String[] args) {
        System.out.println(new Regex().isMatch("a", ".*.."));
    }
}



class FA {
    State start;
    State end;
    public FA(State start, State end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "FA{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}


class State {

    Map<Character, State> transitions = new HashMap<>();
    String s;
    public State(String s) {
        this.s = s;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(s).append("(");
        for (Character k : transitions.keySet()) {
            builder.append(k).append(":").append(transitions.get(k).s).append(",");
        }
        builder.append(")");
        return builder.toString();
    }
}