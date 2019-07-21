package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Spiral {

    public List<Integer> spiralOrder(int[][] matrix) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int rowMin = 0;
        int colMin = 0;
        int rowMax = matrix.length - 1;
        int colMax = matrix[0].length - 1;

        List<Integer> result = new ArrayList<>();
        while (rowMax >= rowMin && colMax >= colMin) {
            visitRow(matrix, rowMin, colMin, colMax, true, true, true, result);
            visitCol(matrix, colMax, rowMin, rowMax, false, true, true, result);
            if (rowMin != rowMax) visitRow(matrix, rowMax, colMin, colMax, false, true, false, result);
            if (colMin != colMax) visitCol(matrix, colMin, rowMin, rowMax, false, false, false, result);
            rowMin++;
            rowMax--;
            colMin++;
            colMax--;
        }
        return result;
    }

    private void visitRow(int[][]matrix, int rowIndex, int colMin, int colMax, boolean includeStart, boolean includeEnd, boolean forward, List<Integer> result) {
        if (colMax < colMin) {
            return;
        }
        if (forward) {
            for (int j = includeStart ? colMin : colMin + 1; j <= (includeEnd ? colMax: colMax - 1); j++) {
                if (j < matrix[rowIndex].length) result.add(matrix[rowIndex][j]);
            }
        } else {
            for (int j = includeStart ? colMax : colMax - 1 ; j >= (includeEnd ? colMin : colMin + 1); j--) {
                if (j < matrix[rowIndex].length) result.add(matrix[rowIndex][j]);
            }
        }
    }


    private void visitCol(int[][]matrix, int colIndex, int rowMin, int rowMax, boolean includeStart, boolean includeEnd, boolean forward, List<Integer> result) {
        if (rowMax < rowMin) {
            return;
        }
        if (forward) {
            for (int i = includeStart ? rowMin : rowMin + 1; i <= (includeEnd ? rowMax : rowMax - 1); i++) {
                if (colIndex < matrix[i].length) result.add(matrix[i][colIndex]);
            }
        } else {
            for (int i = includeStart ? rowMax : rowMax - 1; i >= (includeEnd ? rowMin : rowMin + 1); i --) {
                if (colIndex < matrix[i].length) result.add(matrix[i][colIndex]);
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };
        int[][] matrix2 = {{6,9,7}};

        System.out.println(new Spiral().spiralOrder(matrix));
    }


}
