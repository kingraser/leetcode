package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class ShortestCompletingWord {
    /*
    Given a string licensePlate and an array of strings words, find the shortest completing word in words.
    A completing word is a word that contains all the letters in licensePlate. Ignore numbers and spaces in licensePlate, and treat letters as case insensitive. If a letter appears more than once in licensePlate, then it must appear in the word the same number of times or more.
    For example, if licensePlate = "aBc 12c", then it contains letters 'a', 'b' (ignoring case), and 'c' twice. Possible completing words are "abccdef", "caaacab", and "cbca".
    Return the shortest completing word in words. It is guaranteed an answer exists. If there are multiple shortest completing words, return the first one that occurs in words.

    Example 1:
    Input: licensePlate = "1s3 PSt", words = ["step","steps","stripe","stepple"]
    Output: "steps"
    Explanation: licensePlate contains letters 's', 'p', 's' (ignoring case), and 't'.
    "step" contains 't' and 'p', but only contains 1 's'.
    "steps" contains 't', 'p', and both 's' characters.
    "stripe" is missing an 's'.
    "stepple" is missing an 's'.
    Since "steps" is the only word containing all the letters, that is the answer.

    Example 2:
    Input: licensePlate = "1s3 456", words = ["looks","pest","stew","show"]
    Output: "pest"
    Explanation: licensePlate only contains the letter 's'. All the words contain 's', but among these "pest", "stew", and "show" are shortest. The answer is "pest" because it is the word that appears earliest of the 3.

    Example 3:
    Input: licensePlate = "Ah71752", words = ["suggest","letter","of","husband","easy","education","drug","prevent","writer","old"]
    Output: "husband"

    Example 4:
    Input: licensePlate = "OgEu755", words = ["enough","these","play","wide","wonder","box","arrive","money","tax","thus"]
    Output: "enough"

    Example 5:
    Input: licensePlate = "iMSlpe4", words = ["claim","consumer","student","camera","public","never","wonder","simple","thought","use"]
    Output: "simple"

    Constraints:
    1 <= licensePlate.length <= 7
    licensePlate contains digits, letters (uppercase or lowercase), or space ' '.
    1 <= words.length <= 1000
    1 <= words[i].length <= 15
    words[i] consists of lower case English letters.
    */
    int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17};

    public String shortestCompletingWord(String licensePlate, String[] words) {
        int plateHashCode = 1, platePureSize = 0, resultLen = 16, resultIdx = 0, letterTable[] = new int[26], idx;
        for (int i = 0, letterIdx; i < licensePlate.length(); i++) if ((letterIdx = getLetterIdx(licensePlate.charAt(i))) >= 0) letterTable[letterIdx]++;
        for (int i = 0, j = 0, count; i < 26; i++) {
            if ((count = letterTable[i]) == 0) continue;
            platePureSize += count;
            while (count-- > 0) plateHashCode *= primes[j];
            letterTable[i] = ++j;
        }
        for (int i = 0, j, wordLen, wordHashCode, maxMiss, miss, letterIdx; i < words.length; i++) {
            if ((wordLen = words[i].length()) < platePureSize || wordLen >= resultLen) continue;
            for (j = 0, wordHashCode = 1, maxMiss = wordLen - platePureSize, miss = 0; j < wordLen && miss <= maxMiss; j++)
                if ((letterIdx = getLetterIdx(words[i].charAt(j))) < 0 || letterTable[letterIdx] == 0) miss++;
                else wordHashCode *= primes[letterTable[letterIdx] - 1];
            if (wordHashCode % plateHashCode == 0) resultLen = words[resultIdx = i].length();
        }
        return words[resultIdx];
    }

    private int getLetterIdx(char c) {
        return c < 'A' ? -1 : c <= 'Z' ? c - 'A' : (c >= 'a' && c <= 'z') ? c - 'a' : -1;
    }

    @Test
    public void test() {
        Assert.assertEquals("according", shortestCompletingWord("GrC8950", new String[]{"measure", "other", "every", "base", "according", "level", "meeting", "none", "marriage", "rest"}));
        Assert.assertEquals("party", shortestCompletingWord("Ar16259", new String[]{"nature", "though", "party", "food", "any", "democratic", "building", "eat", "structure", "our"}));
        Assert.assertEquals("steps", shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}));
    }
}
