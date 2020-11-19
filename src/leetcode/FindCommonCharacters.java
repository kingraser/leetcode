package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class FindCommonCharacters {
    /*
    Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
    You may return the answer in any order.

    Example 1:
    Input: ["bella","label","roller"]
    Output: ["e","l","l"]

    Example 2:
    Input: ["cool","lock","cook"]
    Output: ["c","o"]

    Note:
    1 <= A.length <= 100
    1 <= A[i].length <= 100
    A[i][j] is a lowercase letter
    */

    public List<String> commonChars(String[] a) {
        return Arrays.stream(a).map(s -> s.chars().mapToObj(i -> (char) i).collect(Collectors.toMap(Function.identity(), k -> 1, Integer::sum))).reduce((map1, map2) -> map1.entrySet().stream().filter(e -> map2.containsKey(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, e -> Math.min(e.getValue(), map2.get(e.getKey()))))).orElse(new HashMap<>()).entrySet().stream().flatMap(e -> IntStream.range(0, e.getValue()).mapToObj(i -> e.getKey().toString())).collect(Collectors.toList());
    }

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList("e", "l", "l"), commonChars(new String[]{"bella", "label", "roller"}));
    }
}
