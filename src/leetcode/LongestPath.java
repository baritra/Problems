package leetcode;
class LongestPath {
    public int longestIncreasingPath(int[][] matrix) {
        int[][] longest = new int[matrix.length][matrix[0].length];
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int max = Integer.MIN_VALUE;


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (longest[i][j] == 0) {
                    longestPath(matrix, i, j, visited, longest);
                }

                System.out.println(String.format("longest path from %d-%d is %d", i, j, longest[i][j]));

                if (longest[i][j] > max) {
                    max = longest[i][j];
                }
            }
        }



        return max;
    }

    public int longestPath(int[][]matrix, int i, int j, boolean[][]visited, int[][]longest) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) return 0;
        if (longest[i][j] != 0) return longest[i][j];
        if (visited[i][j]) return 0;

        int max = Integer.MIN_VALUE;
        visited[i][j] = true;
        if (i < matrix.length - 1 && matrix[i+1][j] > matrix[i][j]) max = Math.max(max, 1 + longestPath(matrix, i+1, j, visited, longest));
        if (i > 0 && matrix[i-1][j] > matrix[i][j]) max = Math.max(max, 1 + longestPath(matrix, i-1, j, visited, longest));
        if (j  < matrix[0].length - 1 && matrix[i][j+1] > matrix[i][j]) max = Math.max(max, 1 + longestPath(matrix, i, j+1, visited, longest));
        if (j  > 0 && matrix[i][j-1] > matrix[i][j]) max = Math.max(max, 1 + longestPath( matrix, i, j-1, visited, longest));
        visited[i][j] = false;
        longest[i][j] = max;
        return longest[i][j];
    }




    public static void main(String[]args) {
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(new LongestPath().longestIncreasingPath(matrix));
    }


}
