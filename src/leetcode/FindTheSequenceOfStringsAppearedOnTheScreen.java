package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FindTheSequenceOfStringsAppearedOnTheScreen {
    /*
    You are given a string target.
    Alice is going to type target on her computer using a special keyboard that has only two keys:
        Key 1 appends the character "a" to the string on the screen.
        Key 2 changes the last character of the string on the screen to its next character in the English alphabet. For example, "c" changes to "d" and "z" changes to "a".
    Note that initially there is an empty string "" on the screen, so she can only press key 1.
    Return a list of all strings that appear on the screen as Alice types target, in the order they appear, using the minimum key presses.

    Example 1:
    Input: target = "abc"
    Output: ["a","aa","ab","aba","abb","abc"]
    Explanation:
    The sequence of key presses done by Alice are:
        Press key 1, and the string on the screen becomes "a".
        Press key 1, and the string on the screen becomes "aa".
        Press key 2, and the string on the screen becomes "ab".
        Press key 1, and the string on the screen becomes "aba".
        Press key 2, and the string on the screen becomes "abb".
        Press key 2, and the string on the screen becomes "abc".

    Example 2:
    Input: target = "he"
    Output: ["a","b","c","d","e","f","g","h","ha","hb","hc","hd","he"]

    Constraints:
        1 <= target.length <= 400
        target consists only of lowercase English letters.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of("a", "aa", "ab", "aba", "abb", "abc"), "abc"},
                {List.of("a", "b", "c", "d", "e", "f", "g", "h", "ha", "hb", "hc", "hd", "he"), "he"},
        });
    }

    public List<String> stringSequence(String target) {
        List<String> result = new ArrayList<>();
        int length = target.length(), size = 0;
        char[] chars = new char[length];
        for (; size < length; size++)
            for (char targetChar = target.charAt(size), current = 'a'; ; ) {
                chars[size] = current;
                result.add(new String(chars, 0, size + 1));
                if (current++ == targetChar) break;
            }
        return result;
    }
}
