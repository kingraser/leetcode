package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class OccurrencesAfterBigram {
    /*
    Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.
    For each such occurrence, add "third" to the answer, and return the answer.

    Example 1:
    Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
    Output: ["girl","student"]

    Example 2:
    Input: text = "we will we will rock you", first = "we", second = "will"
    Output: ["we","rock"]

    Note:
    1 <= text.length <= 1000
    text consists of space separated words, where each word consists of lowercase English letters.
    1 <= first.length, second.length <= 10
    first and second consist of lowercase English letters.
    */

    public String[] findOcurrences(String text, String first, String second) {
        List<String> result = new ArrayList<>();
        String[] array = text.split(" ");
        for (int i = 2; i < array.length; i++)
            if (array[i - 2].equals(first) && array[i - 1].equals(second)) result.add(array[i]);
        return result.toArray(new String[0]);
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new String[]{"girl", "student"}, findOcurrences("alice is a good girl she is a good student", "a", "good"));
        Assert.assertArrayEquals(new String[]{"we", "rock"}, findOcurrences("we will we will rock you", "we", "will"));
    }
}
