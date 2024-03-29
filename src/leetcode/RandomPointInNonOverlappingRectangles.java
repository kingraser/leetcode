package leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Wit
 */
public class RandomPointInNonOverlappingRectangles {
    /*
    You are given an array of non-overlapping axis-aligned rectangles rects where rects[i] = [ai, bi, xi, yi] indicates that (ai, bi) is the bottom-left corner point of the ith rectangle and (xi, yi) is the top-right corner point of the ith rectangle. Design an algorithm to pick a random integer point inside the space covered by one of the given rectangles. A point on the perimeter of a rectangle is included in the space covered by the rectangle.
    Any integer point inside the space covered by one of the given rectangles should be equally likely to be returned.
    Note that an integer point is a point that has integer coordinates.
    Implement the Solution class:
    Solution(int[][] rects) Initializes the object with the given rectangles rects.
    int[] pick() Returns a random integer point [u, v] inside the space covered by one of the given rectangles.

    Example 1:
    Input
    ["Solution", "pick", "pick", "pick", "pick", "pick"]
    [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
    Output
    [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
    Explanation
    Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
    solution.pick(); // return [1, -2]
    solution.pick(); // return [1, -1]
    solution.pick(); // return [-1, -2]
    solution.pick(); // return [-2, -2]
    solution.pick(); // return [0, 0]

    Constraints:
    1 <= rects.length <= 100
    rects[i].length == 4
    -10^9 <= ai < xi <= 10^9
    -10^9 <= bi < yi <= 10^9
    xi - ai <= 2000
    yi - bi <= 2000
    All the rectangles do not overlap.
    At most 104 calls will be made to pick.
    */

    public class Solution {
        Random random = new Random();
        int rects[][], sizes[];

        public Solution(int[][] rects) {
            (sizes = new int[(this.rects = rects).length])[0] = getCount(rects[0]);
            for (int i = 1; i < rects.length; i++) sizes[i] = sizes[i - 1] + getCount(rects[i]);
        }

        public int[] pick() {return pickInARect(rects[pickRect()]);}

        int pickRect() {
            int index = Arrays.binarySearch(sizes, random.nextInt(sizes[sizes.length - 1]) + 1);
            return index >= 0 ? index : -index - 1;
        }

        int[] pickInARect(int[] rect) {return new int[]{rect[0] + random.nextInt(rect[2] - rect[0] + 1), rect[1] + random.nextInt(rect[3] - rect[1] + 1)};}

        int getCount(int[] rect) {return (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);}
    }
}
