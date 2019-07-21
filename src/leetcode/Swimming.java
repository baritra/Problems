package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Swimming {
    public int swimInWater(int[][] grid) {
        int[][]cache = new int[grid.length][grid[0].length];
        int[][]visited = new int[grid.length][grid[0].length];
        cache[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
        int answer = pathWithMinMaxEdge(0,0, grid, cache, visited);
        return answer;
    }

    public int pathWithMinMaxEdge(int si, int sj, int[][]grid, int[][]cache, int[][]visited) {

        if (si < 0 || sj < 0 || si > grid.length-1 || sj > grid[0].length-1) {
            return -1;
        }
        if (visited[si][sj] != 0) {
            return -1;
        }
        visited[si][sj] = 1;


        if (cache[si][sj] != 0) {
            return cache[si][sj];
        }
        int east = pathWithMinMaxEdge(si, sj+1, grid, cache, visited);
        int west = pathWithMinMaxEdge(si, sj-1, grid, cache, visited);
        int north = pathWithMinMaxEdge(si-1, sj, grid, cache, visited);
        int south = pathWithMinMaxEdge(si+1, sj, grid, cache, visited);
        int min = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        if (east != -1) list.add(east);
        if (north != -1) list.add(north);
        if (south != -1) list.add(south);
        if (west != -1) list.add(west);
        if (list.isEmpty()) {
            return -1;
        }
        for (int solution : list) {
            if (solution < min) {
                min = solution;
            }
        }
        //The solution needs to contain this cell.
        int value = Math.max(grid[si][sj], min);
        cache[si][sj] = value;
        visited[si][sj] = 2;
        return Math.max(grid[si][sj], min);
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        //int[][] grid = {{10,9,8,7,6}};

        new Swimming().swimInWater(grid);
    }
}

