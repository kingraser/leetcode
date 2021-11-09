package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class BulbSwitcherII {
    /*
    There is a room with n bulbs labeled from 1 to n that all are turned on initially, and four buttons on the wall. Each of the four buttons has a different functionality where:
    Button 1: Flips the status of all the bulbs.
    Button 2: Flips the status of all the bulbs with even labels (i.e., 2, 4, ...).
    Button 3: Flips the status of all the bulbs with odd labels (i.e., 1, 3, ...).
    Button 4: Flips the status of all the bulbs with a label j = 3k + 1 where k = 0, 1, 2, ... (i.e., 1, 4, 7, 10, ...).
    You must make exactly presses button presses in total. For each press, you may pick any of the four buttons to press.
    Given the two integers n and presses, return the number of different possible statuses after performing all presses button presses.

    Example 1:
    Input: n = 1, presses = 1
    Output: 2
    Explanation: Status can be:
    - [off] by pressing button 1
    - [on] by pressing button 2

    Example 2:
    Input: n = 2, presses = 1
    Output: 3
    Explanation: Status can be:
    - [off, off] by pressing button 1
    - [on, off] by pressing button 2
    - [off, on] by pressing button 3

    Example 3:
    Input: n = 3, presses = 1
    Output: 4
    Explanation: Status can be:
    - [off, off, off] by pressing button 1
    - [off, on, off] by pressing button 2
    - [on, off, on] by pressing button 3
    - [off, on, on] by pressing button 4

    Example 4:
    Input: n = 1, presses = 0
    Output: 1
    Explanation: Status can only be [on] since you cannot press any of the buttons.

    Example 5:
    Input: n = 1, presses = 2
    Output: 2
    Explanation: Status can be:
    - [off] by pressing button 1 then button 1 again
    - [on] by pressing button 1 then button 2

    Constraints:
    1 <= n <= 1000
    0 <= presses <= 1000
    */

    /*
    Easy to find
    1   You can change the order of the operation of pressing the first button without changing the result.
        For example press 1+2 == press 2+1
    2   1+2 == 3
        2+3 == 3+2 == 1
        1+3 == 2
    3   So there are only 8 states: original, 1, 2, 3, 4, 1+4, 2+4, 3+4
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, 1, 1}, {3, 2, 1}, {4, 3, 1}, {1, 1, 0}, {2, 1, 2}
        });
    }

    public int flipLights(int n, int presses) {
        if (presses == 0) return 1;
        if (n == 1) return 2;
        if (n == 2) return presses == 1 ? 3 : 4;
        if (presses == 1) return 4;
        return presses == 2 ? 7 : 8; // when presses are 2, state 4 is missing
    }
}
