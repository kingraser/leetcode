package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GenerateBinaryStringsWithoutAdjacentZeros {
    /*
    You are given a positive integer n.
    A binary string x is valid if all substrings of x of length 2 contain at least one "1".
    Return all valid strings with length n, in any order.

    Example 1:
    Input: n = 3
    Output: ["010","011","101","110","111"]
    Explanation:
    The valid strings of length 3 are: "010", "011", "101", "110", and "111".

    Example 2:
    Input: n = 1
    Output: ["0","1"]
    Explanation:
    The valid strings of length 1 are: "0" and "1".

    Constraints:
        1 <= n <= 18
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of("010", "011", "101", "110", "111"), 3},
                {List.of("0", "1"), 1}
        });

        IntStream.range(1, 19)
                .mapToObj(this::validStrings)
                .map(List::size)
                .forEach(System.out::println);
    }

    public List<String> validStrings(int n) {
        List<String> result = new ArrayList<>();
        dfs(new char[n], 0, result);
        return result;
    }

    private void dfs(char[] s, int size, List<String> result) {
        if (size == s.length) {
            result.add(new String(s));
            return;
        }
        if (size == 0 || s[size - 1] == '1') dfs(s, size, result, '0');
        dfs(s, size, result, '1');
    }

    void dfs(char[] chars, int currentSize, List<String> result, char c) {
        chars[currentSize++] = c;
        dfs(chars, currentSize, result);
    }
}
