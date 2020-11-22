package leetcode;

import leetcode.util.MathUtil;

/**
 * @author Wit
 */
public class MaximumNumberofBalloons {
    /*
    Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
    You can use each character in text at most once. Return the maximum number of instances that can be formed.

    Example 1:
    Input: text = "nlaebolko"
    Output: 1

    Example 2:
    Input: text = "loonbalxballpoon"
    Output: 2

    Example 3:
    Input: text = "leetcode"
    Output: 0

    Constraints:
    1 <= text.length <= 10^4
    text consists of lower case English letters only.
    */

    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        text.chars().map(i -> i - 'a').forEach(i -> count[i]++);
        return MathUtil.min(count[1], count[0], count[11] >> 1, count[14] >> 1, count[13]);
    }
}
