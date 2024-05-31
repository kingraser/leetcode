package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class WaterBottlesII {
/*
You are given two integers numBottles and numExchange.
numBottles represents the number of full water bottles that you initially have. In one operation, you can perform one of the following operations:
    Drink any number of full water bottles turning them into empty bottles.
    Exchange numExchange empty bottles with one full water bottle. Then, increase numExchange by one.
Note that you cannot exchange multiple batches of empty bottles for the same value of numExchange. For example, if numBottles == 3 and numExchange == 1, you cannot exchange 3 empty water bottles for 3 full bottles.
Return the maximum number of water bottles you can drink.

Example 1:
Input: numBottles = 13, numExchange = 6
Output: 15
Explanation: The table above shows the number of full water bottles, empty water bottles, the value of numExchange, and the number of bottles drunk.

Example 2:
Input: numBottles = 10, numExchange = 3
Output: 13
Explanation: The table above shows the number of full water bottles, empty water bottles, the value of numExchange, and the number of bottles drunk.

Constraints:
    1 <= numBottles <= 100
    1 <= numExchange <= 100
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{17, 15, 5},
				{15, 13, 6},
				{13, 10, 3},
				{14, 13, 13},
		});
	}

	public int maxBottlesDrunk(int numBottles, int numExchange) {
		int result = numBottles;
		for (numExchange--; numBottles > numExchange; result++) numBottles -= numExchange++;
		return result;
	}

	public int maxBottlesDrunkII(int numBottles, int numExchange) {
		int result = numBottles;
		if (numBottles-- >= numExchange--)
			result += ((int) Math.sqrt((numExchange = (numExchange << 1) - 1) * numExchange + (numBottles << 3)) - numExchange) >> 1;
		return result;
	}
}
