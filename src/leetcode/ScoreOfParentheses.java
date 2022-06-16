package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * @author Wit
 */
public class ScoreOfParentheses {
    /*
    Given a balanced parentheses string s, return the score of the string.
    The score of a balanced parentheses string is based on the following rule:
    "()" has score 1.
    AB has score A + B, where A and B are balanced parentheses strings.
    (A) has score 2 * A, where A is a balanced parentheses string.
    Example 1:
    Input: s = "()"
    Output: 1

    Example 2:
    Input: s = "(())"
    Output: 2

    Example 3:
    Input: s = "()()"
    Output: 2

    Constraints:
    2 <= s.length <= 50
    s consists of only '(' and ')'.
    s is a balanced parentheses string.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, "()"},
                {2, "(())"},
                {2, "()()"}
        });
    }

    public int scoreOfParentheses(String s) {
        int score = 0;
        ArrayDeque<Integer> st = new ArrayDeque<>(s.length());
        for (char c : s.toCharArray())
            if (c == '(') {
                st.push(score);
                score = 0;
            } else score = st.pop() + Math.max(score << 1, 1);
        return score;
    }
}
