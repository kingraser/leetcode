package leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class GoatLatin {
    /*
    A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
    We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

    The rules of Goat Latin are as follows:
    If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
    For example, the word 'apple' becomes 'applema'.
    If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
    For example, the word "goat" becomes "oatgma".
    Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
    For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
    Return the final sentence representing the conversion from S to Goat Latin.

    Example 1:
    Input: "I speak Goat Latin"
    Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"

    Example 2:
    Input: "The quick brown fox jumped over the lazy dog"
    Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"

    Notes:
    S contains only uppercase, lowercase and spaces. Exactly one space between each word.
    1 <= S.length <= 150.
    */
    public String toGoatLatin(String s) {
        int[] idx = new int[1];
        return Arrays.stream(s.split("\\s+")).map(word -> toGoatLatin(word, idx[0]++)).collect(Collectors.joining(" "));
    }

    String toGoatLatin(String word, int i) {
        StringBuilder sb = new StringBuilder();
        char c = word.charAt(0);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
            sb.append(word);
        else sb.append(word.substring(1)).append(c);
        for (sb.append("ma"), i++; i-- > 0; ) sb.append('a');
        return sb.toString();
    }

}
