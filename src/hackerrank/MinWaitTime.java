package hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/minimum-average-waiting-time/problem
 */
public class MinWaitTime {


    static int minimumAverage(int[][] customers) {
        List<Customer> sortedByArrival = new ArrayList<>();
        for (int i = 0; i < customers.length; i++) {
            sortedByArrival.add(new Customer(customers[i][0], customers[i][1]));
        }

        Collections.sort(sortedByArrival, (c1, c2) -> c1.arriveTime - c2.arriveTime);
        Heap<Customer> minProcessTimeHeap = new Heap<>((c1, c2) -> c2.processTime - c1.processTime);

        int t = sortedByArrival.get(0).arriveTime;
        int totalWaitTime = 0;
        int lastAddedIndex = -1;
        int customersScheduled = 0;

        while(customersScheduled < sortedByArrival.size()) {
            // add all customers who have already arrived to the heap
            for (int i = lastAddedIndex + 1; i < sortedByArrival.size(); i ++) {
                if (sortedByArrival.get(i).arriveTime <= t) {
                    minProcessTimeHeap.insert(sortedByArrival.get(i));
                    lastAddedIndex = i;
                } else {
                    break;
                }
            }
            if (minProcessTimeHeap.getHeapSize() > 0) {
                Customer scheduledCustomer = minProcessTimeHeap.getRoot();
                totalWaitTime += scheduledCustomer.waitTime(t);
                minProcessTimeHeap.removeRoot();
                customersScheduled ++;
                t += scheduledCustomer.processTime;
            }
            else {
                t = sortedByArrival.get(lastAddedIndex + 1).arriveTime;
            }
        }
        return totalWaitTime/(sortedByArrival.size());
    }

    public static void main(String[] args) {
        int[][] customers = {
                {0, 3},
                {1, 9},
                {2, 6},
                {50, 5}

        };
        System.out.println(minimumAverage(customers));
    }

}

class Customer {
    int arriveTime, processTime;

    public Customer(int arriveTime, int processTime) {
        this.arriveTime = arriveTime;
        this.processTime = processTime;
    }

    public int waitTime(int scheduled) {
        return (scheduled - arriveTime) + processTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "arriveTime=" + arriveTime +
                ", processTime=" + processTime +
                '}';
    }
}


