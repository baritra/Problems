package leetcode;

/**
 * https://leetcode.com/problems/minimum-cost-for-tickets/
 */
public class MinTicketCost {
    public int mincostTickets(int[] days, int[] costs) {
        int[][] optimumCosts = new int[days.length][days.length];

        for (int l = 1; l <= days.length; l++) {
            for (int i = 0; i <= days.length - l; i++) {
                int j = i + l - 1;

                int weekCost = Integer.MAX_VALUE;
                int monthCost = Integer.MAX_VALUE;
                int dayCost = Integer.MAX_VALUE;
                if (l == 1) {
                    weekCost = costs[1];
                    monthCost = costs[2];
                    dayCost = costs[0];
                }
                if ((days[j] - days[i]) < 7) {
                    weekCost = costs[1];
                    monthCost = costs[2];
                }
                if ((days[j] - days[i]) < 30) {
                    monthCost = costs[2];
                }

                int minCost1 = Math.min(weekCost, monthCost);
                int minCost = Math.min(minCost1, dayCost);


                for (int k = i; k < j; k++) {
                    int cost = optimumCosts[i][k] + optimumCosts[k + 1][j];
                    if (cost < minCost) {
                        minCost = cost;
                    }
                }
                optimumCosts[i][j] = minCost;


            }
        }
        return optimumCosts[0][days.length - 1];
    }

    public static void main(String[] args) {
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {7, 2, 15};
        System.out.println(new MinTicketCost().mincostTickets(days, costs));
    }
}
