package topcoder;

public class OnehandRoadPainting {
    long fastest(int[] dStart, int[] dEnd, int paintPerBrush) {
        int curSegment = 0;
        long totalWalk = 0;
        int paintLeft = paintPerBrush;
        int curPosition = 0;
        int paintedUntil = dStart[0];
        while(true) {                                           // 3-4 7-9 totalwalk = 3 +
            totalWalk += (paintedUntil - curPosition);
            curPosition = paintedUntil; //

            if (paintedUntil + paintLeft >= dEnd[curSegment]) {
                // this segment is done;
                paintLeft -= (dEnd[curSegment] - curPosition);
                totalWalk += (dEnd[curSegment] - curPosition);
                curPosition = dEnd[curSegment];

                curSegment++;
                if (curSegment > dStart.length - 1) {
                    break;
                }
                paintedUntil = dStart[curSegment];
                if (paintLeft == 0) {
                    //done but also ran out of paint
                    //go back home
                    totalWalk  += curPosition;
                    curPosition = 0;
                    paintLeft = paintPerBrush;
                }
            } else {
                //no more paint left, gotta go back
                paintedUntil = curPosition + paintLeft;
                //for the painting
                totalWalk += paintLeft;
                // for walk
                totalWalk += paintedUntil;
                curPosition = 0;
                paintLeft = paintPerBrush;
            }


        }
        totalWalk += curPosition;
        return totalWalk;
    }

    public static void main (String[] args) {
        int[]start = {3,7};
        int[]end = {4,9};
        System.out.println(new OnehandRoadPainting().fastest(start, end, 5));
    }
}
