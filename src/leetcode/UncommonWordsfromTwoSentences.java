package leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Wit
 */
public class UncommonWordsfromTwoSentences {
    /*
    We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
    A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
    Return a list of all uncommon words.
    You may return the list in any order.

    Example 1:
    Input: A = "this apple is sweet", B = "this apple is sour"
    Output: ["sweet","sour"]

    Example 2:
    Input: A = "apple apple", B = "banana"
    Output: ["banana"]

    Note:
    0 <= A.length <= 200
    0 <= B.length <= 200
    A and B both contain only spaces and lowercase letters.
    */

    public String[] uncommonFromSentences(String a, String b) {
        return Stream.concat(Arrays.stream(a.split(" ")), Arrays.stream(b.split(" "))).collect(Collectors.toMap(Function.identity(), s -> 1, Integer::sum)).entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).toArray(String[]::new);
    }
}
