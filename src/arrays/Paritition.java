package arrays;

public class Paritition {
    public int partition(int[]nums, int start, int end) {
        int smallerEnd = start;
        int largerEnd = start;
        int pivot = nums[start];
        while (largerEnd <= end) {
            if (nums[largerEnd] > pivot) {
                break;
            }
            largerEnd++;
        }
        if (largerEnd > start) {
            smallerEnd = largerEnd - 1;
        }

        while(largerEnd <= end) {
            if (nums[largerEnd] < pivot) {
                smallerEnd++;
                int temp = nums[smallerEnd];
                nums[smallerEnd] = nums[largerEnd];
                nums[largerEnd] = temp;
            } else {
                largerEnd++;
            }
        }

        nums[start] = nums[smallerEnd];
        nums[smallerEnd] = pivot;
        return smallerEnd;
    }

    public int findKthSmallestUsingParition(int[] nums, int k, int start, int end) {
        if (start > end) {
            return -1;
        }
        int pivotIndex = partition(nums, start, end);
        int numLessOrEqual = (pivotIndex - start) + 1;
        if (numLessOrEqual == k) {
            return nums[pivotIndex];
        } else if (numLessOrEqual < k) {
            return findKthSmallestUsingParition(nums, k - numLessOrEqual, pivotIndex + 1, end);
        } else {
            return findKthSmallestUsingParition(nums, k, start, pivotIndex - 1);
        }

    }

    public int findKthLargestUsingParition(int[] nums, int k, int start, int end) {
        if (start > end) {
            return -1;
        }
        int pivotIndex = partition(nums, start, end);
        int numGreaterOrEqual = (end - pivotIndex) + 1;
        if (numGreaterOrEqual == k) {
            return nums[pivotIndex];
        } else if (numGreaterOrEqual < k) {
            return findKthLargestUsingParition(nums, k - numGreaterOrEqual, start, pivotIndex - 1);
        } else {
            return findKthLargestUsingParition(nums, k, pivotIndex + 1, end);
        }

    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 8, 9, 10, 0};
        //System.out.println(new Paritition().partition(nums, 0, nums.length -1 ));
        System.out.println(new Paritition().findKthLargestUsingParition(nums, 3, 0, nums.length - 1));
    }
}
