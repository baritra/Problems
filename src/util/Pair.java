package util;

import java.util.Objects;

public class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return x == pair.x &&
                y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
class Event {
    int time;
    Type t;

    public Event(int time, Type t) {
        this.time = time;
        this.t = t;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, t);
    }
}

enum Type { START, END }


class Tuple3 {
    int i, j, m;

    public Tuple3(int i, int j, int m) {
        this.i = i;
        this.j = j;
        this.m = m;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple3)) return false;
        Tuple3 tuple3 = (Tuple3) o;
        return i == tuple3.i &&
                j == tuple3.j &&
                m == tuple3.m;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, m);
    }
}

class Freq {
    char c;
    int freq;

}