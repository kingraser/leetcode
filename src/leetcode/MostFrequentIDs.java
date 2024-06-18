package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Wit
 */
public class MostFrequentIDs {
/*
The problem involves tracking the frequency of IDs in a collection that changes over time. You have two integer arrays, nums and freq, of equal length n. Each element in nums represents an ID, and the corresponding element in freq indicates how many times that ID should be added to or removed from the collection at each step.
    Addition of IDs: If freq[i] is positive, it means freq[i] IDs with the value nums[i] are added to the collection at step i.
    Removal of IDs: If freq[i] is negative, it means -freq[i] IDs with the value nums[i] are removed from the collection at step i.
Return an array ans of length n, where ans[i] represents the count of the most frequent ID in the collection after the ith step. If the collection is empty at any step, ans[i] should be 0 for that step.

Example 1:
Input: nums = [2,3,2,1], freq = [3,2,-3,1]
Output: [3,3,2,2]
Explanation:
After step 0, we have 3 IDs with the value of 2. So ans[0] = 3.
After step 1, we have 3 IDs with the value of 2 and 2 IDs with the value of 3. So ans[1] = 3.
After step 2, we have 2 IDs with the value of 3. So ans[2] = 2.
After step 3, we have 2 IDs with the value of 3 and 1 ID with the value of 1. So ans[3] = 2.

Example 2:
Input: nums = [5,5,3], freq = [2,-2,1]
Output: [2,0,1]
Explanation:
After step 0, we have 2 IDs with the value of 5. So ans[0] = 2.
After step 1, there are no IDs. So ans[1] = 0.
After step 2, we have 1 ID with the value of 3. So ans[2] = 1.

Constraints:
    1 <= nums.length == freq.length <= 10^5
    1 <= nums[i] <= 10^5
    -10^5 <= freq[i] <= 10^5
    freq[i] != 0
    The input is generated such that the occurrences of an ID will not be negative in any step.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new long[]{3, 3, 2, 2}, new int[]{2, 3, 2, 1}, new int[]{3, 2, -3, 1}},
				{new long[]{2, 0, 1}, new int[]{5, 5, 3}, new int[]{2, -2, 1}},
		});
	}

	public long[] mostFrequentIDs(int[] nums, int[] freq) {
		long result[] = new long[nums.length], oldFreq[] = new long[1], newFreq;
		TreeMap<Long, Integer> freqCountMap = new TreeMap<>(Comparator.reverseOrder());
		HashMap<Integer, Long> idFreqMap = new HashMap<>();
		for (int i = 0; i < nums.length; oldFreq[0] = 0, i++) {
			if ((newFreq = idFreqMap.merge(nums[i], (long) freq[i], (old, change) -> (oldFreq[0] = old) + change)) != 0)
				freqCountMap.merge(newFreq, 1, Integer::sum);
			if (oldFreq[0] != 0) freqCountMap.merge(oldFreq[0], -1, Integer::sum);
			Map.Entry<Long, Integer> first = null;
			while (!freqCountMap.isEmpty() && (first = freqCountMap.firstEntry()).getValue() == 0)
				freqCountMap.remove(first.getKey());
			result[i] = freqCountMap.isEmpty() ? 0 : first.getKey();
		}
		return result;
	}
}
