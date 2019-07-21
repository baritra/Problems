package leetcode;

public class SurroundingRegions {

    public void solve(char[][] board) {

        if (board.length == 0 || board[0].length == 0) {
            return;
        }

        Meta[][] metadata = new Meta[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                metadata[i][j] = new Meta(board[i][j], false);

            }
        }

        for (int i = 0; i < metadata.length; i++) {
            boolean regionOpen = false;
            int lastX = -1;

            for (int j = 0; j < metadata[i].length; j++) {
                Meta data = metadata[i][j];

                if (data.val == 'O' && lastX != -1) {
                    regionOpen = true;
                }

                if (regionOpen && data.val == 'X') {
                    for (int k = j; k >= lastX; k--) {
                        if (metadata[i][k].val == 'O') {
                            metadata[i][k].rowLocked = true;
                        }
                    }
                    regionOpen = false;
                }
                if (data.val == 'X') {
                    lastX = j;
                }
            }
        }

        for (int i = 0; i < metadata[0].length; i ++) {
            boolean regionOpen = false;
            int lastX = -1;
            for (int j = 0; j < metadata.length; j++) {
                Meta data = metadata[j][i];

                if (data.val == 'O' && lastX != -1) {
                    regionOpen = true;
                }

                if (regionOpen && data.val == 'X') {
                    for (int k = j; k >= lastX; k--) {
                        if (metadata[k][i].val == 'O' && metadata[k][i].rowLocked) {
                            metadata[k][i].val = 'X';
                        }
                    }
                    regionOpen = false;
                }
                if (data.val == 'X') {
                    lastX = j;
                }
            }
        }
        for (int i = 0; i < metadata.length; i++) {
            for (int j = 0; j < metadata[0].length; j++) {
                board[i][j] = metadata[i][j].val;
            }
        }
    }

    public static void main(String[] args) {
        char[][] maze = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        char[][] maze2 = {{'O','X','X','O','X'},
                          {'X','O','O','X','O'},
                          {'X','O','X','O','X'},
                          {'O','X','O','O','O'},
                          {'X','X','O','X','O'}};

        new SurroundingRegions().solve(maze2);

        print(maze2);

    }



    private static void print(char[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print("{");
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + ",");
            }
            System.out.println("}");
        }
    }

}

class Meta {
    char val;
    boolean rowLocked = false;

    public Meta(char val, boolean rowLocked) {
        this.val = val;
        this.rowLocked = rowLocked;
    }
}
