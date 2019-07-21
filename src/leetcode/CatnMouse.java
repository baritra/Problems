package leetcode;

import java.util.*;

public class CatnMouse {

    Map<State, Integer> values = new HashMap<>();

    public int catMouseGame(int[][] graph) {
        Set<State> visited = new HashSet<>();
        State start = new State(2, 1, 'm');
        int result = explore(start, graph, visited);
        if (result == -1) return 2;
        else return result;
    }

    private int explore(State s, int[][] graph, Set<State> visited) {
        Integer val = evaluate(s, visited);
        if (val != null) {
            values.put(s, val);
            return val;
        } else {
            List<State> nexts = nextStates(graph, s);
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (State n : nexts) {
                int nextVal = explore(n, graph, visited);
                if (nextVal > max) {
                    max = nextVal;

                }

                if (nextVal < min) {
                    min = nextVal;
                }
            }

            int thisVal = s.mover == 'c' ? min : max;
            values.put(s, thisVal);
            return thisVal;
        }
    }

    private List<State> nextStates(int[][] graph, State start) {
        List<State> nexts = new ArrayList<>();
        if (start.mover == 'm') {
            for (int j = 0; j < graph[start.mousePos].length; j++) {
                State newState = new State(start.catPos, graph[start.mousePos][j], 'c');
                nexts.add(newState);
            }
        } else if (start.mover == 'c') {
            for (int j = 0; j < graph[start.catPos].length; j++) {
                State newState = new State(graph[start.catPos][j], start.mousePos,'m');
                nexts.add(newState);
            }
        }
        return nexts;
    }

    private Integer evaluate(State s, Set<State> seen) {
        if (s.catPos == s.mousePos) return -1;
        if (s.mousePos == 0) return 1;
        if (seen.contains(s)) return 0;
        return null;
    }


}

class State {
    int catPos;
    int mousePos;
    char mover;
    int value;

    public State(int catPos, int mousePos, char mover) {
        this.catPos = catPos;
        this.mousePos = mousePos;
        this.mover = mover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return catPos == state.catPos &&
                mousePos == state.mousePos &&
                mover == state.mover;
    }

    @Override
    public int hashCode() {
        return Objects.hash(catPos, mousePos, mover);
    }
}
