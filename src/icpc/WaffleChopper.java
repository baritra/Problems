package icpc;

import java.util.HashMap;
import java.util.Map;

public class WaffleChopper {

    public boolean isDivisionPossible(char[][] waffleBoard, int r, int c, int h, int v) {
        int[] chipsInRows = new int[r];
        int [] chipsInColumns = new int[c];

        int totalChips = 0;

        for (int i = 0; i < r ; i ++) {
            for (int j = 0; j < c; j++) {
                if (waffleBoard[i][j] == '@') {
                    chipsInRows[i] = chipsInRows[i] + 1;
                    chipsInColumns[j] = chipsInColumns[j] + 1;
                    totalChips++;
                }
            }
        }
        if (totalChips % (h + 1) != 0 || totalChips % (v + 1) != 0) {
            return false;
        }

        int chipsPerRow = totalChips / (h + 1);
        int curChips = 0;
        for (int i = 0; i < r; i ++) {
            curChips += chipsInRows[i];
            if (curChips > chipsPerRow) {
                return false;
            } else if (curChips == chipsPerRow) {
                curChips = 0;
            }
        }

        int chipsPerColumn = totalChips / (v + 1);
        curChips = 0;
        for (int i = 0; i < c; i ++) {
            curChips += chipsInColumns[i];
            if (curChips > chipsPerColumn) {
                return false;
            } else if (curChips == chipsPerColumn) {
                curChips = 0;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        /**
         * .@@..@
         * .....@
         * @.@.@@
         */
        char[][] waffleBoard = {{'.','@','@','.','.','@'}, {'.','.','.','.','@','@'}, {'@','.','@','.','@','@'}};
        System.out.println(new WaffleChopper().isDivisionPossible(waffleBoard, 3, 6, 1, 1));
    }
}
