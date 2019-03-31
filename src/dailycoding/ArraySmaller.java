package dailycoding;

public class ArraySmaller {

    /**
     * Given an array of integers, return a new array where each element in the new array is the number of smaller elements to the right of that element in the original input array.
     *
     * For example, given the array [3, 4, 9, 6, 1], return [1, 1, 2, 1, 0]
     *
     * 5 50 1 2 3 4
     */

    public int[] arraySmallerThanFrequency(int [] a) {
        int[] result = new int[a.length];
        for (int i = a.length - 1; i >= 0; i --) {
            if (i == a.length - 1) {
                result[i] = 0;
            } else if (a[i] > a[i+1]){
                result[i] = result[i+1] + 1;
            } else if (a[i] == a[i+1]) {
                result[i] = result[i+1];
            } else {
                int j;
                for (j = i+2; j < a.length ; j++) {
                    if (a[j] == a[i]) {
                        result[i] = result[j];
                        break;
                    } else if (a[i] > a[j]) {
                        result[i] = result[j] + 1;
                        break;
                    }
                }
                if (j == a.length) {
                    result[i] = 0;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] x = new ArraySmaller().arraySmallerThanFrequency(new int[]{3, 4, 9, 6, 1});
        for (int i = 0 ; i < x.length ; i ++) {
            System.out.print(x[i] + ",");
        }
    }
}
