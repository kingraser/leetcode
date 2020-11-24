package leetcode;

import leetcode.util.ArrayUtil;

/**
 * @author Wit
 */
public class ReverseOnlyLetters {
    /*
    Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.

    Example 1:
    Input: "ab-cd"
    Output: "dc-ba"

    Example 2:
    Input: "a-bC-dEf-ghIj"
    Output: "j-Ih-gfE-dCba"

    Example 3:
    Input: "Test1ng-Leet=code-Q!"
    Output: "Qedo1ct-eeLg=ntse-T!"

    Note:
    S.length <= 100
    33 <= S[i].ASCIIcode <= 122
    S doesn't contain \ or "
    */

    public String reverseOnlyLetters(String s) {
        char[] array = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < j; ) {
            while (i < j && !Character.isLetter(array[i])) i++;
            while (i < j && !Character.isLetter(array[j])) j--;
            ArrayUtil.swap(array, i++, j--);
        }
        return new String(array);
    }

}
