package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindTheMostCommonResponse {
    /*
    You are given a 2D string array responses where each responses[i] is an array of strings representing survey responses from the ith day.
    Return the most common response across all days after removing duplicate responses within each responses[i]. If there is a tie, return the response.

    Example 1:
    Input: responses = [["good","ok","good","ok"],["ok","bad","good","ok","ok"],["good"],["bad"]]
    Output: "good"
    Explanation:
        After removing duplicates within each list, responses = [["good", "ok"], ["ok", "bad", "good"], ["good"], ["bad"]].
        "good" appears 3 times, "ok" appears 2 times, and "bad" appears 2 times.
        Return "good" because it has the highest frequency.

    Example 2:
    Input: responses = [["good","ok","good"],["ok","bad"],["bad","notsure"],["great","good"]]
    Output: "bad"
    Explanation:
        After removing duplicates within each list we have responses = [["good", "ok"], ["ok", "bad"], ["bad", "notsure"], ["great", "good"]].
        "bad", "good", and "ok" each occur 2 times.
        The output is "bad" because it is the lexicographically smallest amongst the words with the highest frequency.

    Constraints:
        1 <= responses.length <= 1000
        1 <= responses[i].length <= 1000
        1 <= responses[i][j].length <= 10
        responses[i][j] consists of only lowercase English letters
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"good", TestUtil.transferToString("[[\"good\",\"ok\",\"good\",\"ok\"],[\"ok\",\"bad\",\"good\",\"ok\",\"ok\"],[\"good\"],[\"bad\"]]")},
                {"bad", TestUtil.transferToString("[[\"good\",\"ok\",\"good\"],[\"ok\",\"bad\"],[\"bad\",\"notsure\"],[\"great\",\"good\"]]")}
        });
    }

    public String findCommonResponse(List<List<String>> responses) {
        Map<String, int[]> wordMap = new HashMap<>();
        int currentMax = 0;
        String mostFreqString = "";
        for (int[] day = new int[1]; day[0] < responses.size(); )
            for (String word : responses.get(day[0]++)) {
                int[] value = wordMap.computeIfAbsent(word, k -> new int[]{0, day[0] - 1});
                if (value[1] == day[0]) continue;
                value[1] = day[0];
                if (currentMax < ++value[0]) {
                    currentMax = value[0];
                    mostFreqString = word;
                } else if (currentMax == value[0] && word.compareTo(mostFreqString) < 0) mostFreqString = word;
            }
        return mostFreqString;
    }
}
