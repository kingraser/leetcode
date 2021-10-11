package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberofPairsofStringsWithConcatenationEqualtoTarget {
    /*
    Given an array of digit strings nums and a digit string target, return the number of pairs of indices (i, j) (where i != j) such that the concatenation of nums[i] + nums[j] equals target.

    Example 1:
    Input: nums = ["777","7","77","77"], target = "7777"
    Output: 4
    Explanation: Valid pairs are:
    - (0, 1): "777" + "7"
    - (1, 0): "7" + "777"
    - (2, 3): "77" + "77"
    - (3, 2): "77" + "77"

    Example 2:
    Input: nums = ["123","4","12","34"], target = "1234"
    Output: 2
    Explanation: Valid pairs are:
    - (0, 1): "123" + "4"
    - (2, 3): "12" + "34"

    Example 3:
    Input: nums = ["1","1","1"], target = "11"
    Output: 6
    Explanation: Valid pairs are:
    - (0, 1): "1" + "1"
    - (1, 0): "1" + "1"
    - (0, 2): "1" + "1"
    - (2, 0): "1" + "1"
    - (1, 2): "1" + "1"
    - (2, 1): "1" + "1"

    Constraints:
    2 <= nums.length <= 100
    1 <= nums[i].length <= 100
    2 <= target.length <= 100
    nums[i] and target consist of digits.
    nums[i] and target do not have leading zeros.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {4, new String[]{"777", "7", "77", "77"}, "7777"},
                {2, new String[]{"123", "4", "12", "34"}, "1234"},
                {6, new String[]{"1", "1", "1"}, "11"}
        });
    }

    @SuppressWarnings("AssignmentUsedAsCondition")
    public int numOfPairs(String[] nums, String target) {
        int result = 0, tLen = target.length(), pre[] = new int[tLen], suf[] = new int[tLen], numLen;
        boolean targetIsNotRepeat = true, startWith, endWith;
        for (String num : nums) {
            if ((numLen = num.length()) >= tLen) continue;
            if (startWith = target.startsWith(num)) pre[numLen]++;
            if (endWith = target.endsWith(num)) suf[numLen]++;
            if (targetIsNotRepeat && startWith && endWith && tLen == numLen << 1) targetIsNotRepeat = false;
        }
        for (int i = 1; i < tLen; ) result += pre[i] * suf[tLen - i++];
        return result - (targetIsNotRepeat ? 0 : pre[tLen >> 1]);
    }

}
