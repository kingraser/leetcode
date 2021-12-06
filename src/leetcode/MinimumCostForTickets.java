package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Wit
 */
public class MinimumCostForTickets {
    /*
    You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
    Train tickets are sold in three different ways:
    a 1-day pass is sold for costs[0] dollars,
    a 7-day pass is sold for costs[1] dollars, and
    a 30-day pass is sold for costs[2] dollars.
    The passes allow that many days of consecutive travel.
    For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
    Return the minimum number of dollars you need to travel every day in the given list of days.

    Example 1:
    Input: days = [1,4,6,7,8,20], costs = [2,7,15]
    Output: 11
    Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
    On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
    On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
    On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
    In total, you spent $11 and covered all the days of your travel.

    Example 2:
    Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
    Output: 17
    Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
    On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
    On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
    In total, you spent $17 and covered all the days of your travel.

    Constraints:
    1 <= days.length <= 365
    1 <= days[i] <= 365
    days is in strictly increasing order.
    costs.length == 3
    1 <= costs[i] <= 1000
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {11, new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}},
                {17, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}}
        });
    }

    public int minCostTickets(int[] days, int[] costs) {
        int cost = 0;
        Deque<int[]> last7days = new ArrayDeque<>(days.length), last30days = new ArrayDeque<>(days.length);
        for (int day : days) {
            while (!last7days.isEmpty() && last7days.peek()[0] + 7 <= day) last7days.poll();
            for (last7days.addLast(new int[]{day, cost + costs[1]}); !last30days.isEmpty() && last30days.peek()[0] + 30 <= day; )
                last30days.poll();
            last30days.addLast(new int[]{day, cost + costs[2]});
            cost = Math.min(cost + costs[0], Math.min(last30days.peek()[1], last7days.peek()[1]));
        }
        return cost;
    }
}
