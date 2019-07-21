package sorting;

public class Sorting {

    // 2, 1, 8, 7, 0, 4, 5
    public static int partition(int[] a, int low, int high) {
        int pivotIndex = high;
        int bigElementsHead = 0;
        while (a[bigElementsHead++] < a[pivotIndex]);
        bigElementsHead--;
        int bigElementsTail = bigElementsHead;

        while (bigElementsTail <= high) {
            if (a[bigElementsTail] < a[pivotIndex]) {
                int temp = a[bigElementsTail];
                a[bigElementsTail] = a[bigElementsHead];
                a[bigElementsHead] = temp;
                bigElementsHead++;

            } else {
                bigElementsTail++;
            }
        }
        int temp = a[bigElementsHead];
        a[bigElementsHead] = a[pivotIndex];
        a[pivotIndex] = temp;
        return bigElementsHead;
    }

    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }
    public static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivot = partition(a, low, high);
            quickSort(a, low, pivot - 1);
            quickSort(a, pivot + 1, high);
        }

    }

    public static void arrayPrint(int[] a) {
        System.out.print("[");
        for (int val : a) System.out.print(val+",");
        System.out.print("]");

    }

    public static void main(String[] args) {
        int[] a = {2, 1, 8, 7, 0, 4, 5};
        quickSort(a);
        arrayPrint(a);

    }
}
