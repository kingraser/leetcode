package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.PriorityQueue;

import static leetcode.util.TestUtil.getArray;

/**
 * @author Wit
 */
public class NumberOfFlowersInFullBloom {
    /*
    You are given a 0-indexed 2D integer array flowers, where flowers[i] = [start_i, end_i] means the ith flower will be in full bloom from start_i to end_i (inclusive). You are also given a 0-indexed integer array persons of size n, where persons[i] is the time that the ith person will arrive to see the flowers.
    Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.

    Example 1:
    Input: flowers = [[1,6],[3,7],[9,12],[4,13]], persons = [2,3,7,11]
    Output: [1,2,2,2]
    Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
    For each person, we return the number of flowers in full bloom during their arrival.

    Example 2:
    Input: flowers = [[1,10],[3,3]], persons = [3,3,2]
    Output: [2,2,1]
    Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
    For each person, we return the number of flowers in full bloom during their arrival.

    Constraints:
    1 <= flowers.length <= 5 * 10^4
    flowers[i].length == 2
    1 <= start_i <= end_i <= 10^9
    1 <= persons.length <= 5 * 10^4
    1 <= persons[i] <= 10^9
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {getArray("[1,2,2,2]"), getArray("[[1,6],[3,7],[9,12],[4,13]]"), getArray("[2,3,7,11]")},
                {getArray("[2,2,1]"), getArray("[[1,10],[3,3]]"), getArray("[3,3,2]")}
        });
    }

    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        int[] result = new int[persons.length];
        // event-type: 0 bloom, 1 person, 2 die
        // {time, event-type, if person -> person-index}
        // first sort by time, then by event type
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < persons.length; i++) pq.offer(new int[]{persons[i], 1, i});
        for (int[] flower : flowers) {
            pq.offer(new int[]{flower[0], 0});
            pq.offer(new int[]{flower[1], 2});
        }
        for (int blooms = 0, current[]; !pq.isEmpty(); )
            if ((current = pq.poll())[1] == 0) blooms++;
            else if (current[1] == 2) blooms--;
            else result[current[2]] = blooms;
        return result;
    }
}
