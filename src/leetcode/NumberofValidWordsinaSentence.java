package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class NumberofValidWordsinaSentence {
    /*
    A sentence consists of lowercase letters ('a' to 'z'), digits ('0' to '9'), hyphens ('-'), punctuation marks ('!', '.', and ','), and spaces (' ') only. Each sentence can be broken down into one or more tokens separated by one or more spaces ' '.
    A token is a valid word if:
    It only contains lowercase letters, hyphens, and/or punctuation (no digits).
    There is at most one hyphen '-'. If present, it should be surrounded by lowercase characters ("a-b" is valid, but "-ab" and "ab-" are not valid).
    There is at most one punctuation mark. If present, it should be at the end of the token.
    Examples of valid words include "a-b.", "afad", "ba-c", "a!", and "!".
    Given a string sentence, return the number of valid words in sentence.

    Example 1:
    Input: sentence = "cat and  dog"
    Output: 3
    Explanation: The valid words in the sentence are "cat", "and", and "dog".

    Example 2:
    Input: sentence = "!this  1-s b8d!"
    Output: 0
    Explanation: There are no valid words in the sentence.
    "!this" is invalid because it starts with a punctuation mark.
    "1-s" and "b8d" are invalid because they contain digits.

    Example 3:
    Input: sentence = "alice and  bob are playing stone-game10"
    Output: 5
    Explanation: The valid words in the sentence are "alice", "and", "bob", "are", and "playing".
    "stone-game10" is invalid because it contains digits.

    Example 4:
    Input: sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."
    Output: 6
    Explanation: The valid words in the sentence are "he", "bought", "pencils,", "erasers,", "and", and "pencil-sharpener.".

    Constraints:
    1 <= sentence.length <= 1000
    sentence only contains lowercase English letters, digits, ' ', '-', '!', '.', and ','.
    There will be at least 1 token.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {40, "g5ui9586 .6oc6mq4r8 r51ja ,m  5  ,  uk4 ge48  boe4 .8cl phola r20 ,g0b5 -zb,ce69k iw  n zk  j-7 rh mu hjle!eq5 jzzkw06boy3 2e  mk-w5 hn  h !7. 7ce47l, b svs0xjq,ncjyhqi. 6.v .laq2-fhoo eyag2ks,w 0mz7cd557pa6z p8fa .b zy91chzd6phrm  ai8zfh- h 5 1  soj 4im- 7a   r-prpk4y4phxh44s4zk h h t7!jae9p   y3j1do zg !wzjz .  .  8! ys 7 ih7 33s r8--8!k  -,8  k 91ed e.2w ruh!5q!n i c0yt,m ! x01  9k6v3 e67t,!f    h d!a094n ks x ,m8fnggrsq9 zwjn2ucb vlp5!8p!5r!- mpp s3,b 8 no.fp,4wt e9 4pa2hj17 ,e 4vj!-mzp60ze qahmt  mfe4m k!uz07u  xr4fl71ai 4b!8b1 k-bi,2.p u-.ukni-bs md2wfu1e-dy56f!  c 4sb52xg h j6ehb4ndf ha s7zad57b 6r r  orf,-31  uv ot 48i gp4t d u w tl!  a 0vgh 7k591up4 !   .mxa c 7n 49  uj- qwkp-9w a 3"},
                {3, "cat and  dog"},
                {0, "!this  1-s b8d!"},
                {5, "alice and  bob are playing stone-game10"},
                {6, "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."}
        });
    }

    public int countValidWords(String sentence) {
        int result = 0;
        char s[] = sentence.toCharArray(), c;
        /* state 0 for space; 1 for invalid word; 2 for valid word without hyphens; 3 for valid word with one hyphen*/
        for (int i = 0, state = 0; i < s.length; )
            if (state == 0) {
                if ((c = s[i++]) == ' ') continue;
                else if (isLetter(c) || (isPunctuationMark(c) && (i == s.length || s[i] == ' '))) {
                    result++;
                    state = 2;
                } else state = 1;
            } else if (state > 1) {
                if (isDigit(c = s[i++]) || (c == '-' && (++state > 3 || i == s.length || !isLetter(s[i]))) || (isPunctuationMark(c) && i < s.length && s[i] != ' ')) {
                    result--;
                    state = 1;
                } else if (c == ' ') state = 0;
            } else if (s[i++] == ' ') state = 0;
        return result;
    }

    public int countValidWordsII(String sentence) {
        return (int) Arrays.stream(sentence.split("\\s+")).filter(this::isValid).count();
    }

    boolean isValid(String s) {
        for (int i = 0, len = s.length(), c, hyphenCount = 0; i < len; )
            if (isDigit(c = s.charAt(i++)) || (isPunctuationMark(c) && i != len) || (c == '-' && (++hyphenCount > 1 || i == 1 || i == len || !isLetter(s.charAt(i)))))
                return false;
        return true;
    }

    boolean isLetter(int c) {return c >= 'a' && c <= 'z';}

    boolean isDigit(int c) {return c >= '0' && c <= '9';}

    boolean isPunctuationMark(int c) {return c == '!' || c == '.' || c == ',';}
}
