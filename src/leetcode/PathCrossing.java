package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * @author Wit
 */
public class PathCrossing {
    /*
    Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
    Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.

    Example 1:
    Input: path = "NES"
    Output: false
    Explanation: Notice that the path doesn't cross any point more than once.

    Example 2:
    Input: path = "NESWW"
    Output: true
    Explanation: Notice that the path visits the origin twice.

    Constraints:
    1 <= path.length <= 10^4
    path[i] is either 'N', 'S', 'E', or 'W'.
    */
    public boolean isPathCrossing(String path) {
        HashSet<Integer> seen = new HashSet<>();
        seen.add(0);
        int x = 0, y = 0;
        for (char c : path.toCharArray()) {
            if (c == 'N') y++;
            else if (c == 'S') y--;
            else if (c == 'E') x++;
            else x--;
            if (!seen.add(x * 10000 + y)) return true;
        }
        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(isPathCrossing("NESWW"));
        Assert.assertFalse(isPathCrossing("WSSESEEE"));
    }
}
