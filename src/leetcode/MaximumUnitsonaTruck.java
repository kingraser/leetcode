package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MaximumUnitsonaTruck {
    /*
    You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxes-i, numberOfUnitsPerBox-i]:
    numberOfBoxes-i is the number of boxes of type i.
    numberOfUnitsPerBox-i is the number of units in each box of the type i.
    You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
    Return the maximum total number of units that can be put on the truck.

    Example 1:
    Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
    Output: 8
    Explanation: There are:
    - 1 box of the first type that contains 3 units.
    - 2 boxes of the second type that contain 2 units each.
    - 3 boxes of the third type that contain 1 unit each.
    You can take all the boxes of the first and second types, and one box of the third type.
    The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.

    Example 2:
    Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
    Output: 91

    Constraints:
    1 <= boxTypes.length <= 1000
    1 <= numberOfBoxes-i, numberOfUnitsPerBox-i <= 1000
    1 <= truckSize <= 10^6
    */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int result = 0;
        Arrays.sort(boxTypes, (boxType1, boxType2) -> boxType2[1] - boxType1[1]);
        for (int i = 0; i < boxTypes.length && truckSize > 0; truckSize -= boxTypes[i++][0])
            result += boxTypes[i][1] * Integer.min(truckSize, boxTypes[i][0]);
        return result;
    }

    @Test
    public void test() throws NoSuchMethodException {
        TestUtil.testEquals(this,
                this.getClass().getMethod("maximumUnits", int[][].class, int.class),
                new Object[][]{
                        {8, new int[][]{{1, 3}, {2, 2}, {3, 1}}, 4},
                }
        );
    }
}
