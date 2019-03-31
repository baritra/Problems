package hackerrank;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution {

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

    /*
     * Complete the runningMedian function below.
     */
    static double[] runningMedian(int[] a) {
        Solution runningMedian = new Solution();
        double[] medians = new double[a.length];
        int i = 0;
        for (int val : a) {
            medians[i++] = runningMedian.calculateMedian(val);
        }
        return medians;


    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Solution runningMedian = new Solution();
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

class Heap<T>  {
    private Comparator<T> comparator;
    private int heapSize;

    private List<T> elements;

    public Heap(Comparator<T> comparator) {
        this.comparator = comparator;
        elements = new ArrayList<>();
    }

    private int leftChildIndex(int i) {
        int index = 2*i;
        if (index > heapSize - 1) {
            return -1;
        } else {
            return index;
        }

    }

    private int rightChildIndex(int i) {
        int index = 2*i + 1;
        if (index >= heapSize - 1) {
            return -1;
        } else {
            return index;
        }
    }

    private int parentIndex(int i) {
        return i/2;
    }

    public T getRoot() {
        return elements.get(0);
    }

    public void insert(T element) {
        int thisIndex = heapSize;
        addElement(element);
        int parentIndex = thisIndex;;
        do {
            parentIndex = parentIndex(thisIndex);
            T parent = elements.get(parentIndex);
            if (comparator.compare(element, parent) > 0) {
                //element is bigger than parent
                T temp = parent;
                elements.set(parentIndex, element);
                elements.set(thisIndex, parent);
                thisIndex = parentIndex;
            } else {
                break;
            }
        } while (parentIndex > 0);

    }

    public void removeRoot() {
        elements.set(0, elements.get(heapSize - 1));
        heapify(0);
        heapSize--;
    }

    private void heapify(int i) {
        int largestChildIndex = i;
        if (largestChildIndex >= heapSize - 1) {
            return;
        }
        int leftChildIndex = leftChildIndex(i);
        int rightChildIndex = rightChildIndex(i);
        if (leftChildIndex != -1 && comparator.compare(elements.get(leftChildIndex), elements.get(i)) > 0) {
            largestChildIndex = leftChildIndex;
        }

        if (rightChildIndex != -1 && comparator.compare(elements.get(rightChildIndex), elements.get(largestChildIndex)) > 0) {
            largestChildIndex = rightChildIndex;
        }
        if (largestChildIndex != i) {
            T temp = elements.get(i);
            elements.set(i, elements.get(largestChildIndex));
            elements.set(largestChildIndex, temp);
            heapify(largestChildIndex);
        }


    }

    public int getHeapSize() {
        return heapSize;
    }

    private void addElement(T element) {

        if (elements.size() <= heapSize) {
            elements.add(element);
        } else {
            elements.set(heapSize , element);
        }
        heapSize++;
    }


}




