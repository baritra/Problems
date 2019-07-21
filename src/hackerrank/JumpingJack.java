package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JumpingJack {

    public static int maxStep(int n, int k) {
        Map<Triplet, Integer> solutions = new HashMap<>();
        return solve(0, 1, k, n, solutions);
    }

    public static int solve(int position, int nextJumpLength, int k, int remainingJumps, Map<Triplet, Integer> solutions) {
        if (solutions.containsKey(new Triplet(position, nextJumpLength, remainingJumps))) {
            System.out.println("cache hit");
            solutions.get(new Triplet(position, nextJumpLength, remainingJumps));
        }
        if (position == k) {
            return -1;
        }
        if (remainingJumps == 0 && position > k) {
            return position;
        }
        if (remainingJumps <= 0) {
            return -1;
        }
        if (remainingJumps > 0 && position > k) {
            return -1;
        }

        int maxPosition = -1;
        for (int i = 0; i < remainingJumps; i++) {
            //wait at position for i times
            int positionReached = solve(position + nextJumpLength + i, nextJumpLength + i + 1, k, remainingJumps - i - 1, solutions);
            if (positionReached > maxPosition) {
                maxPosition = positionReached;
            }
        }
        solutions.put(new Triplet(position, nextJumpLength, remainingJumps), maxPosition);
        return maxPosition;
    }

    public static void main(String[] args) {
        System.out.println(JumpingJack.maxStep(4, 6));
    }

}

class Triplet {
    int v1, v2, v3;

    public Triplet(int v1, int v2, int v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triplet)) return false;
        Triplet triplet = (Triplet) o;
        return v1 == triplet.v1 &&
                v2 == triplet.v2 &&
                v3 == triplet.v3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2, v3);
    }
}
