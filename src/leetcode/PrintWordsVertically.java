package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class PrintWordsVertically {
    /*
    Given a string s. Return all the words vertically in the same order in which they appear in s.
    Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
    Each word would be put on only one column and that in one column there will be only one word.

    Example 1:
    Input: s = "HOW ARE YOU"
    Output: ["HAY","ORO","WEU"]
    Explanation: Each word is printed vertically.
     "HAY"
     "ORO"
     "WEU"

    Example 2:
    Input: s = "TO BE OR NOT TO BE"
    Output: ["TBONTB","OEROOE","   T"]
    Explanation: Trailing spaces is not allowed.
    "TBONTB"
    "OEROOE"
    "   T"

    Example 3:
    Input: s = "CONTEST IS COMING"
    Output: ["CIC","OSO","N M","T I","E N","S G","T"]

    Constraints:
    1 <= s.length <= 200
    s contains only upper case English letters.
    It's guaranteed that there is only one space between 2 words.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of("ABCDEF", "AB DE", " B DE", "   DE", "    E"), "AA BBB C DDDD EEEEE F"},
                {List.of("HAY", "ORO", "WEU"), "HOW ARE YOU"},
                {List.of("TBONTB", "OEROOE", "   T"), "TO BE OR NOT TO BE"},
                {List.of("CIC", "OSO", "N M", "T I", "E N", "S G", "T"), "CONTEST IS COMING"}
        });
    }

    public List<String> printVertically(String s) {
        List<Word> list = new ArrayList<>((s.length() + 1) >> 1);
        int wordIdx = 0, letterIdx = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                wordIdx++;
                letterIdx = 0;
                continue;
            }
            if (letterIdx == list.size()) list.add(new Word(s.length()));
            list.get(letterIdx++).set(c, wordIdx);
        }
        return list.stream().map(Word::toString).collect(Collectors.toList());
    }

    public class Word {
        int size = 0;
        char[] chars;

        public Word(int n) {Arrays.fill(this.chars = new char[n], ' ');}

        public void set(char c, int i) {
            chars[i] = c;
            size = Math.max(size, ++i);
        }

        public String toString() {return new String(chars, 0, size);}
    }
}
