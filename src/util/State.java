package util;

import java.util.Objects;

public class State {
    int index;
    int distance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return index == state.index &&
                distance == state.distance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, distance);
    }
}
