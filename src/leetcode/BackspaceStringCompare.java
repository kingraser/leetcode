package leetcode;

/**
 * @author Wit
 */
public class BackspaceStringCompare {
    /*
    Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
    Note that after backspacing an empty text, the text will continue empty.

    Example 1:
    Input: S = "ab#c", T = "ad#c"
    Output: true
    Explanation: Both S and T become "ac".

    Example 2:
    Input: S = "ab##", T = "c#d#"
    Output: true
    Explanation: Both S and T become "".

    Example 3:
    Input: S = "a##c", T = "#a#c"
    Output: true
    Explanation: Both S and T become "c".

    Example 4:
    Input: S = "a#c", T = "b"
    Output: false
    Explanation: S becomes "c" while T becomes "b".

    Note:
    1 <= S.length <= 200
    1 <= T.length <= 200
    S and T only contain lowercase letters and '#' characters.
    Follow up:
    Can you solve it in O(N) time and O(1) space?
    */

    public boolean backspaceCompare(String s, String t) {
        for (int idxS = s.length() - 1, idxT = t.length() - 1, skipS = 0, skipT = 0; idxS >= 0 || idxT >= 0; idxS--, idxT--) {
            for (; idxS >= 0; idxS--)
                if (s.charAt(idxS) == '#') skipS++;
                else if (skipS > 0) skipS--;
                else break;
            for (; idxT >= 0; idxT--)
                if (t.charAt(idxT) == '#') skipT++;
                else if (skipT > 0) skipT--;
                else break;
            if ((idxS >= 0) != (idxT >= 0) || (idxS >= 0 && s.charAt(idxS) != t.charAt(idxT))) return false;
        }
        return true;
    }
}
