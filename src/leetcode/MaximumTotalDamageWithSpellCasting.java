package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author Wit
 */
public class MaximumTotalDamageWithSpellCasting {
/*
A magician has various spells.
You are given an array power, where each element represents the damage of a spell. Multiple spells can have the same damage value.
It is a known fact that if a magician decides to cast a spell with a damage of power[i], they cannot cast any spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.
Each spell can be cast only once.
Return the maximum possible total damage that a magician can cast.

Example 1:
Input: power = [1,1,3,4]
Output: 6
Explanation:
The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.

Example 2:
Input: power = [7,1,6,6]
Output: 13
Explanation:
The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with damage 1, 6, 6.

Constraints:
    1 <= power.length <= 10^5
    1 <= power[i] <= 10^9
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{6L, new int[]{1, 1, 3, 4}},
				{13L, new int[]{7, 1, 6, 6}},
				{31L, new int[]{5, 9, 2, 10, 2, 7, 10, 9, 3, 8}}
		});
	}

	public long maximumTotalDamage(int[] power) {
		long result = 0;
		TreeMap<Integer, Long> map = new TreeMap<>();
		for (int i : power) map.merge(i, 1L, Long::sum);
		for (Map.Entry<Integer, Long> entry : map.entrySet()) {
			long value = entry.getValue() * entry.getKey();
			Map.Entry<Integer, Long> prev;
			if (Objects.nonNull(prev = map.lowerEntry(entry.getKey() - 2))) value += prev.getValue();
			entry.setValue(result = Long.max(result, value));
		}
		return result;
	}
}
