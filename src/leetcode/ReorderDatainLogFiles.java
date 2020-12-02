package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class ReorderDatainLogFiles {
    /*
    You have an array of logs.  Each log is a space delimited string of words.
    For each log, the first word in each log is an alphanumeric identifier.  Then, either:
    Each word after the identifier will consist only of lowercase letters, or;
    Each word after the identifier will consist only of digits.
    We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
    Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
    Return the final order of the logs.

    Example 1:
    Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
    Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]

    Constraints:
    0 <= logs.length <= 100
    3 <= logs[i].length <= 100
    logs[i] is guaranteed to have an identifier, and a word after the identifier.
    */

    @Test
    public void test() {
        Assert.assertArrayEquals(new String[]{"let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"},
                reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"}));
    }

    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (s1, s2) -> {
            int space1 = s1.indexOf(' '), space2 = s2.indexOf(' '), l1 = s1.length(), l2 = s2.length();
            if (s1.charAt(space1 + 1) <= '9') return s2.charAt(space2 + 1) <= '9' ? 0 : 1;
            else if (s2.charAt(space2 + 1) <= '9') return -1;
            for (int i = space1 + 1, j = space2 + 1, cmp; i < l1 && j < l2; )
                if ((cmp = Character.compare(s1.charAt(i++), s2.charAt(j++))) != 0) return cmp;
            if (l1 != l2) return Integer.compare(l1, l2);
            for (int i = 0, j = 0, cmp; i < space1 && j < space2; )
                if ((cmp = Character.compare(s1.charAt(i++), s2.charAt(j++))) != 0) return cmp;
            return Integer.compare(space1, space2);
        });
        return logs;
    }
}
