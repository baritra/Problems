package icpc;

import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * https://icpc.kattis.com/problems/string
 *
 */
public class StringTheory {


    public int findMaxDepth(int[] quotes) {
        int maxDepth = getMaxPossibleDepth(quotes);
        if (maxDepth < 1) {
            return maxDepth;
        }

        for (int depth = maxDepth; depth >= 1; depth --) {
            ArrayList<Integer> list = new ArrayList<>();
            if (quotes[0] > depth) {
                list.add(quotes[0] - depth);
            }
            for (int i = 1; i < quotes.length; i++) {
                list.add(quotes[i]);
            }

            State start = new State(depth, Position.START);
            State end = new State(depth, Position.END);

            if (runStateMachine(start, end, list)) {
                return depth;
            }
        }
        return -1;
     }

     private boolean runStateMachine(State start, State end, List<Integer> inputStream) {
        if (start.equals(end) && inputStream.isEmpty()) {
            return true;
        }
        Map<Integer, State> transitions = start.getTransitions();
        for (int input : transitions.keySet()) {
            List<Integer> newList = new ArrayList<Integer>(inputStream);
            if (newList.get(0) >= input) {
                if (newList.get(0) == input) {
                    newList.remove(0);
                } else {
                    newList.set(0, newList.get(0) - 1);
                }
                if (runStateMachine(transitions.get(input), end, newList)) {
                    return true;
                }
            }
        }
        return false;
     }


    private LinkedList<Integer> stripQuotes(int[] quotes, int depth) {
        LinkedList<Integer> list = new LinkedList<>();
        if (quotes.length == 1) {
            list.add(quotes[0] - depth);
        } else {
            if (quotes[0] > depth) {
                list.add(quotes[0] - depth);
            }
            for (int i = 1; i < quotes.length - 1; i++) {
                list.add(quotes[i]);
            }
            if (quotes[quotes.length - 1] > depth) {
                list.add(quotes[quotes.length - 1] - depth);
            }
        }

        return list;
    }

    private int getMaxPossibleDepth(int[] quotes) {
        int allQuotes = 0;
        for (int i = 0; i < quotes.length; i++) {
            allQuotes += quotes[i];
        }
        if (allQuotes/2 != 0) {
            return -1;
        }
        int halfQuotes = allQuotes/2;

        int depth = 1;
        for (depth = 1; depth <= halfQuotes; depth ++) {
            if ((depth * (depth - 1)/2 > halfQuotes)) {
                break;
            }
        }
        return Math.max(quotes[0], depth - 1);
    }

}

class State {
    int depth;
    Position position;

    public State(int depth, Position position) {
        this.depth = depth;
        this.position = position;
    }

    public Map<Integer, State> getTransitions() {
        Map<Integer, State> transitions = new HashMap<>();
        if (position == Position.START) {
            if (depth == 1) {
                transitions.put(depth, new State(depth, Position.END));
            }
            else transitions.put(depth - 1, new State(depth - 1, Position.START));
            return transitions;
        }
        else {
            transitions.put(depth, new State(depth, Position.START));
            transitions.put(depth + 1, new State(depth + 1, Position.END));
        }
        return transitions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return depth == state.depth &&
                position == state.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depth, position);
    }
}


enum Position {
    START, END
}
