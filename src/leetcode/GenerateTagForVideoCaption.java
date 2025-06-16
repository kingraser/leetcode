package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class GenerateTagForVideoCaption {
    /*
    You are given a string caption representing the caption for a video.
    The following actions must be performed in order to generate a valid tag for the video:
        Combine all words in the string into a single camelCase string prefixed with '#'. A camelCase string is one where the first letter of all words except the first one is capitalized. All characters after the first character in each word must be lowercase.
        Remove all characters that are not an English letter, except the first '#'.
        Truncate the result to a maximum of 100 characters.
    Return the tag after performing the actions on caption.

    Example 1:
    Input: caption = "Leetcode daily streak achieved"
    Output: "#leetcodeDailyStreakAchieved"
    Explanation:
    The first letter for all words except "leetcode" should be capitalized.

    Example 2:
    Input: caption = "can I Go There"
    Output: "#canIGoThere"
    Explanation:
    The first letter for all words except "can" should be capitalized.

    Example 3:
    Input: caption = "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
    Output: "#hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
    Explanation:
    Since the first word has length 101, we need to truncate the last two letters from the word.

    Constraints:
        1 <= caption.length <= 150
        caption consists only of English letters and ' '.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"#a", "a"},
                {"#leetcodeDailyStreakAchieved", "Leetcode dAily streak achieved"},
                {"#leetcodeDailyStreakAchieved", "Leetcode daily streak achieved"},
                {"#canIGoThere", "can I Go There"},
                {"#hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh", "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"},
        });
    }

    public String generateTag(String caption) {
        int size = 0;
        char result[] = new char[100], c;
        result[size++] = '#';
        for (int i = 0, length = caption.length(); i < length && size < 100; )
            if ((c = caption.charAt(i++)) != ' ')
                for (result[size++] = Character.toUpperCase(c); i < length && size < 100 && (c = caption.charAt(i++)) != ' '; )
                    result[size++] = Character.toLowerCase(c);
        if (size > 1) result[1] = Character.toLowerCase(result[1]);
        return new String(result, 0, size);
    }
}
