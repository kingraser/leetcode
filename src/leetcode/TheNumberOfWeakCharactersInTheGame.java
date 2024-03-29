package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class TheNumberOfWeakCharactersInTheGame {
    /*
    You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attack_i, defense_i] represents the properties of the ith character in the game.
    A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attack_j > attack_i and defense_j > defense_i.
    Return the number of weak characters.

    Example 1:
    Input: properties = [[5,5],[6,3],[3,6]]
    Output: 0
    Explanation: No character has strictly greater attack and defense than the other.

    Example 2:
    Input: properties = [[2,2],[3,3]]
    Output: 1
    Explanation: The first character is weak because the second character has a strictly greater attack and defense.

    Example 3:
    Input: properties = [[1,5],[10,4],[4,3]]
    Output: 1
    Explanation: The third character is weak because the second character has a strictly greater attack and defense.

    Constraints:
    2 <= properties.length <= 10^5
    properties[i].length == 2
    1 <= attack_i, defense_i <= 10^5
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {0, new int[][]{
                        {5, 5},
                        {6, 3},
                        {3, 6}
                }},
                {1, new int[][]{
                        {2, 2},
                        {3, 3}
                }},
                {1, new int[][]{
                        {1, 5},
                        {10, 4},
                        {4, 3}
                }}
        });
    }

    public int numberOfWeakCharacters(int[][] properties) {
        int result = 0, maxDefenses[] = new int[100002];
        for (int[] property : properties) maxDefenses[property[0]] = Math.max(property[1], maxDefenses[property[0]]);
        for (int i = 100000; i >= 0; i--) maxDefenses[i] = Math.max(maxDefenses[i + 1], maxDefenses[i]);
        for (int[] property : properties) if (property[1] < maxDefenses[property[0] + 1]) result++;
        return result;
    }

}
