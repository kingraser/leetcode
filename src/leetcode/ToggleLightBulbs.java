package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ToggleLightBulbs {
    /*
    You are given an array bulbs of integers between 1 and 100.
    There are 100 light bulbs numbered from 1 to 100. All of them are switched off initially.
    For each element bulbs[i] in the array bulbs:
    If the bulbs[i]th light bulb is currently off, switch it on.
    Otherwise, switch it off.
    Return the list of integers denoting the light bulbs that are on in the end, sorted in ascending order. If no bulb is on, return an empty list.

    Example 1:
    Input: bulbs = [10,30,20,10]
    Output: [20,30]
    Explanation:
    The bulbs[0] = 10th light bulb is currently off. We switch it on.
    The bulbs[1] = 30th light bulb is currently off. We switch it on.
    The bulbs[2] = 20th light bulb is currently off. We switch it on.
    The bulbs[3] = 10th light bulb is currently on. We switch it off.
    In the end, the 20th and the 30th light bulbs are on.

    Example 2:
    Input: bulbs = [100,100]
    Output: []
    Explanation:
    The bulbs[0] = 100th light bulb is currently off. We switch it on.
    The bulbs[1] = 100th light bulb is currently on. We switch it off.
    In the end, no light bulb is on.

    Constraints:
    1 <= bulbs.length <= 100
    1 <= bulbs[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(
                new Object[]{
                        List.of(20, 30),
                        List.of()
                },
                new Object[][]{
                        {List.of(10, 30, 20, 10)},
                        {List.of(100, 100)}
                }
        );
    }

    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        List<Integer> result = new ArrayList<>();
        int[] map = new int[101];
        bulbs.forEach(i -> map[i] ^= 1);
        for (int i = 0; i < map.length; i++) {
            if(map[i]==1) result.add(i);
        }
        return result;
    }
}
