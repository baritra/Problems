package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<T>  {
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
