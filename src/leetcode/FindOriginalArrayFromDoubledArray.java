package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindOriginalArrayFromDoubledArray {
/*
An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.
Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.

Example 1:
Input: changed = [1,3,4,2,6,8]
Output: [1,3,4]
Explanation: One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].

Example 2:
Input: changed = [6,3,0,1]
Output: []
Explanation: changed is not a doubled array.

Example 3:
Input: changed = [1]
Output: []
Explanation: changed is not a doubled array.

Constraints:
1 <= changed.length <= 10^5
0 <= changed[i] <= 10^5
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{1, 2, 2}, new int[]{2, 1, 2, 4, 2, 4}},
				{new int[0], new int[]{3, 3, 3, 3}},
				{new int[2], new int[4]},
				{new int[]{1, 3, 4}, new int[]{1, 3, 4, 2, 6, 8}},
				{new int[]{}, new int[]{6, 3, 0, 1}},
				{new int[]{}, new int[]{1}},
		});
	}


	public int[] findOriginalArray(int[] changed) {
		if ((changed.length & 1) == 1) return new int[0];
		int res[] = new int[changed.length >> 1], freq[] = new int[100_001], max = 0, size;
		for (int num : changed) {
			max = Math.max(max, num);
			freq[num]++;
		}
		if ((freq[0] & 1) == 1 || (max & 1) == 1) return new int[0];
		size = freq[0] >> 1;
		for (int i = 1, halfMax = max >> 1; i <= halfMax; i++)
			if ((freq[i << 1] -= freq[i]) < 0) return new int[0];
			else while (freq[i]-- > 0) res[size++] = i;
		return size != res.length ? new int[0] : res;
	}
}
