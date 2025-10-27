package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static leetcode.util.MathUtil.gcd;

public class LexicographicallySmallestStringAfterApplyingOperations {
    /*
    You are given a string s of even length consisting of digits from 0 to 9, and two integers a and b.
    You can apply either of the following two operations any number of times and in any order on s:

        Add `a` to all odd indices of s (0-indexed). Digits post 9 are cycled back to 0.
        For example, if s = "3456" and a = 5, s becomes "3951".

        Rotate s to the right by b positions. For example, if s = "3456" and b = 1, s becomes "6345".

    Return the lexicographically-smallest string you can obtain by applying the above operations any number of times on s.

    A string `a` is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
    For example, "0158" is lexicographically smaller than "0190" because the first position they differ is at the third letter, and '5' comes before '9'.

    Example 1:
    Input: s = "5525", a = 9, b = 2
    Output: "2050"
    Explanation: We can apply the following operations:
    Start:  "5525"
    Rotate: "2555"
    Add:    "2454"
    Add:    "2353"
    Rotate: "5323"
    Add:    "5222"
    Add:    "5121"
    Rotate: "2151"
    Add:    "2050"
    There is no way to obtain a string that is lexicographically smaller than "2050".

    Example 2:
    Input: s = "74", a = 5, b = 1
    Output: "24"
    Explanation: We can apply the following operations:
    Start:  "74"
    Rotate: "47"
    Add:    "42"
    Rotate: "24"
    There is no way to obtain a string that is lexicographically smaller than "24".

    Example 3:
    Input: s = "0011", a = 4, b = 2
    Output: "0011"
    Explanation: There are no sequence of operations that will give us a lexicographically smaller string than "0011".

    Constraints:
        2 <= s.length <= 100
        s.length is even.
        s consists of digits from 0 to 9 only.
        1 <= a <= 9
        1 <= b <= s.length - 1
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"105655", "565510", 7, 2},
                {"2050", "5525", 9, 2},
                {"24", "74", 5, 1},
        });
    }

    String result;
    HashSet<String> visited;
    int a, b;

    void dfs(String str) {
        if (!visited.add(str)) return;
        if (result.compareTo(str) > 0) result = str;
        dfs(add(str));
        dfs(rotate(str));
    }

    String add(String str) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
            if ((i & 1) == 0) s.append(str.charAt(i));
            else s.append((str.charAt(i) - '0' + a) % 10);
        return s.toString();
    }

    String rotate(String s) {
        int n = s.length();
        return s.substring(n - b, n) + s.substring(0, n - b);
    }

    public String findLexSmallestString(String s, int a, int b) {
        visited = new HashSet<>();
        this.a = a;
        this.b = b;
        dfs(result = s);
        return result;
    }

    public String findLexSmallestStringII(String s, int a, int b) {
        int[] chars = s.chars().map(i -> i - '0').toArray(), result = Arrays.copyOf(chars, chars.length);
        int step = gcd(b, chars.length), gcd = gcd(a, 10);
        boolean isOdd = (step & 1) == 1;
        for (int i = 0; i < chars.length; i += step) {
            int[] rotate = rotate(chars, i);
            optimizeDigits(rotate, 1, gcd);
            if (isOdd) optimizeDigits(rotate, 0, gcd);
            if (compare(rotate, result) < 0) result = rotate;
        }
        return getResult(result);
    }

    private int[] rotate(int[] s, int start) {
        int[] rotated = new int[s.length];
        System.arraycopy(s, start, rotated, 0, s.length - start);
        System.arraycopy(s, 0, rotated, s.length - start, start);
        return rotated;
    }

    private void optimizeDigits(int[] s, int start, int gcd) {
        int minValue = s[start] % gcd;
        int inc = (minValue - s[start] + 10) % 10;
        for (; start < s.length; start += 2) s[start] = (s[start] + inc) % 10;
    }

    int compare(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++)
            if (a[i] < b[i]) return -1;
            else if (a[i] > b[i]) return 1;
        return 0;
    }

    String getResult(int[] result) {
        for (int i = 0; i < result.length; ) result[i++] += '0';
        return new String(result, 0, result.length);
    }
}
