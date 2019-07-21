package leetcode;

public class RegionCut {

    public int regionsBySlashes(String[] grid) {
        int[][] explicitGrid = new int[grid.length*2][grid[0].length()*2];
        for (int i = 0, x = 0; i < grid.length; i++, x+=2) {
            for (int j = 0, y = 0; j < grid[0].length(); j++, y+=2) {
                if (grid[i].charAt(j) == ' ') {
                    explicitGrid[x][y] = 1;
                    explicitGrid[x+1][y] = 1;
                    explicitGrid[x][y+1] = 1;
                    explicitGrid[x+1][y+1] = 1;

                } else if (grid[i].charAt(j) == '\\'){
                    explicitGrid[x][y] = 0;
                    explicitGrid[x+1][y] = 1;
                    explicitGrid[x][y+1] = 1;
                    explicitGrid[x+1][y+1] = 0;
                } else if (grid[i].charAt(j) == '/') {
                    explicitGrid[x][y] = 1;
                    explicitGrid[x + 1][y] = -1;
                    explicitGrid[x][y + 1] = -1;
                    explicitGrid[x + 1][y + 1] = 1;
                }
            }
        }

        int numRegions = 0;
        for (int i = 0; i < explicitGrid.length; i ++) {
            for (int j = 0; j < explicitGrid[0].length; j++) {
                if (explicitGrid[i][j] == 1) {
                    numRegions ++;
                    dfs(explicitGrid, i, j);
                }
            }
        }

        return numRegions;


    }

    public void dfs(int[][]grid, int i, int j) {
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1) {
            return;
        }

        if (grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;

        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);

        //can I do i+1, j+1
        if (i < grid.length - 1 && j < grid[0].length - 1) {
            if (grid[i + 1][j] != 1 && grid[i][j + 1] != 1 && grid[i + 1][j] != grid[i][j+1]) {
                dfs(grid, i+1, j);
            }
        }

    }

    public static void main(String[] args) {
        String[] grid = {"//","/ "};
        System.out.println(new RegionCut().regionsBySlashes(grid));
    }
}
