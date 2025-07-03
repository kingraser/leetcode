package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheapestFlightsWithinKStop {
    /*
    There are n cities connected by some number of flights.
    You are given an array flights where flights[i] = [from_i, to_i, price_i] indicates that there is a flight from
    city from_i to city to_i with cost price_i.
    You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
    If there is no such route, return -1.

    Example 1:
    Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
    Output: 700
    Explanation:
    The graph is shown above.
    The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
    Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.

    Example 2:
    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
    Output: 200
    Explanation:
    The graph is shown above.
    The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.

    Example 3:
    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
    Output: 500
    Explanation:
    The graph is shown above.
    The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.

    Constraints:
        1 <= n <= 100
        0 <= flights.length <= (n * (n - 1) / 2)
        flights[i].length == 3
        0 <= from_i, to_i < n
        from_i != to_i
        1 <= price_i <= 10^4
        There will not be any multiple flights between two cities.
        0 <= src, dst, k < n
        src != dst
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {7, 5, TestUtil.getArray("[[1,0,5],[2,1,5],[3,0,2],[1,3,2],[4,1,1],[2,4,1]]"), 2, 0, 2},
                {6, 4, TestUtil.getArray("[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]"), 0, 3, 1},
                {700, 4, TestUtil.getArray("[[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]]"), 0, 3, 1},
                {200, 3, TestUtil.getArray("[[0,1,100],[1,2,100],[0,2,500]]"), 0, 2, 1},
                {500, 3, TestUtil.getArray(" [[0,1," + "100],[1,2,100],[0,2,500]]"), 0, 2, 0},});
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        while (k-- > -1) if (isBellMannFordComplete(flights, cost)) break;
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }

    private boolean isBellMannFordComplete(int[][] flights, int[] cost) {
        boolean complete = true;
        int old[] = cost.clone(), from, to, price;
        for (int[] flight : flights)
            if (old[from = flight[0]] != Integer.MAX_VALUE && (old[from] + (price = flight[2])) < cost[to = flight[1]]) {
                cost[to] = old[from] + price;
                complete = false;
            }
        return complete;
    }


    public int findCheapestPriceII(int n, int[][] flights, int src, int dst, int k) {
        int[] distanceMap = new int[n], newDistanceMap = new int[n];
        Arrays.fill(distanceMap, Integer.MAX_VALUE);
        distanceMap[src] = 0;
        //noinspection unchecked
        List<int[]>[] flightMap = new List[n];
        for (int i = 0; i < n; i++) flightMap[i] = new ArrayList<>();
        for (int[] flight : flights) flightMap[flight[0]].add(flight);
        for (ArrayDeque<Integer> queue = new ArrayDeque<>() {{add(src);}}; !queue.isEmpty() && k-- > -1; ) {
            for (int size = queue.size(), from, to, price; size-- > 0; )
                for (int[] flight : flightMap[from = queue.removeFirst()])
                    if (distanceMap[to = flight[1]] > (price = flight[2] + distanceMap[from]))
                        if (newDistanceMap[to] == 0) {
                            newDistanceMap[to] = price;
                            queue.add(to);
                        } else if (newDistanceMap[to] > price) newDistanceMap[to] = price;
            for (int to : queue) {
                distanceMap[to] = newDistanceMap[to];
                newDistanceMap[to] = 0;
            }
        }
        return distanceMap[dst] == Integer.MAX_VALUE ? -1 : distanceMap[dst];
    }
}