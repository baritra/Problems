package codejam;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BathRooms {

    public void solvBathroom(int N, int K) {
        Set<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        recordSegments(N, treeSet);
        Iterator<Integer> setIterator = treeSet.iterator();
        int i = 0;
        int lastPatron = N;
        while (setIterator.hasNext() && i < K) {
            lastPatron = setIterator.next();
            System.out.println(lastPatron);
            i ++;

        }

        if (lastPatron %2 == 0) {
            System.out.println("Furthest neighbor = " + lastPatron/2);

            System.out.println("Closest neighbor = " + (lastPatron/2 - 1));
        } else {
            System.out.println("Furthest neighbor = " + lastPatron/2);

            System.out.println("Closest neighbor = " + (lastPatron/2));
        }



    }

    private void recordSegments(int N, Set<Integer> sortedSet) {
        if (N < 1) return;
        System.out.println("Adding " + N);

        sortedSet.add(N);

        if (N % 2 == 0) {
            recordSegments(N/2, sortedSet);
            recordSegments(N/2 -1, sortedSet);
        } else {
            recordSegments(N/2, sortedSet);
            recordSegments(N/2, sortedSet);
        }

    }

    public static void main (String[] args) {
        new BathRooms().solvBathroom(1000, 1000);
    }


}
