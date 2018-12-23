import java.util.*;

public class InfinitePancake {

    public static int iterations = 0;

    public int makeAllPancakesSmiling(String startState, Map<String, Integer> configs, int flip, int steps, Map<String, Integer> shortesPath) {


        if (shortesPath.containsKey(startState)) {
            return shortesPath.get(startState);
        }
        boolean complete = true;
        for (int i = 0; i < startState.length(); i++) {
            if (startState.charAt(i) == '-') complete = false;
        }
        if (complete) return 0;
        if (configs.containsKey(startState) && configs.get(startState) < steps) {
            return -1;
        }
        configs.put(startState, steps);

        iterations ++;



        int minSteps = Integer.MAX_VALUE;
        boolean foundPath = false;
        for (int i = 0; i <= startState.length() - flip; i ++) {
            String flipped = new String();
            for (int j = 0; j < startState.length(); j++) {
                if (j < i || j > i + flip -1) {
                    flipped += startState.charAt(j);
                }
                else {
                    if (startState.charAt(j) == '-') flipped += '+';
                    else flipped += '-';
                }
            }
            int stepsRequired = makeAllPancakesSmiling(flipped, configs, flip, steps + 1, shortesPath);
            if (stepsRequired != -1) {
                foundPath = true;
                stepsRequired ++;
                if (stepsRequired < minSteps) {
                    minSteps = stepsRequired;
                }
            }
        }

        shortesPath.put(startState, minSteps);

        if (foundPath) {
            return minSteps;

        }
        return -1;
    }


    public static void main(String[] args) {
        InfinitePancake p = new InfinitePancake();
        int steps = p.makeAllPancakesSmiling("---+-++-", new HashMap<>(), 3, 0, new HashMap<>());
        System.out.println("Steps = " + steps);
        System.out.println("Iterations = " + InfinitePancake.iterations);
    }

    class Entry {
        String state;
        int pathTo;

        public Entry(String state, int pathTo) {
            this.state = state;
            this.pathTo = pathTo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry entry = (Entry) o;
            return pathTo == entry.pathTo &&
                    Objects.equals(state, entry.state);
        }

        @Override
        public int hashCode() {
            return Objects.hash(state, pathTo);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "state='" + state + '\'' +
                    ", pathTo=" + pathTo +
                    '}';
        }
    }
}
