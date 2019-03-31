package hackerrank;

import heap.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunningMedian {

    private Heap<Integer> maxHeap = new Heap<>((i, j) -> i - j);
    private Heap<Integer> minHeap = new Heap<>((i, j) -> j - i);

    private double curMedian = -1;

    public double calculateMedian(int newval) {
        if (curMedian == -1) {
            maxHeap.insert(newval);
            curMedian = newval;
            return curMedian;
        }
        if (newval <= maxHeap.getRoot()) {
            maxHeap.insert(newval);
        } else {
            minHeap.insert(newval);
        }

        if (maxHeap.getHeapSize() == minHeap.getHeapSize() + 2) {
            int maxHeapRoot = maxHeap.getRoot();
            maxHeap.removeRoot();
            minHeap.insert(maxHeapRoot);
        } else if (minHeap.getHeapSize() == maxHeap.getHeapSize() + 2) {
            int minHeapRoot = minHeap.getRoot();
            minHeap.removeRoot();
            maxHeap.insert(minHeapRoot);
        }


        if (maxHeap.getHeapSize() == minHeap.getHeapSize()) {
            curMedian = ((double)maxHeap.getRoot() + minHeap.getRoot())/2;
        } else {
            Heap<Integer> largerHeap = maxHeap.getHeapSize() > minHeap.getHeapSize() ? maxHeap : minHeap;
            curMedian = largerHeap.getRoot();

        }
        return curMedian;
    }


    public static void main(String[] args) {
        RunningMedian runningMedian = new RunningMedian();
        Scanner in = new Scanner(System.in);
        List<Integer> inputs = new ArrayList<>();
        int num = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= num; i ++) {
            int val = Integer.parseInt(in.nextLine());
            inputs.add(val);
        }

        for (int val : inputs) {
            System.out.println(runningMedian.calculateMedian(val));
        }

    }

}
