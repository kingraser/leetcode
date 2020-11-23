package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.BitSet;
import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class ShuffleTheArray {
    /*
    Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
    Return the array in the form [x1,y1,x2,y2,...,xn,yn].

    Example 1:
    Input: nums = [2,5,1,3,4,7], n = 3
    Output: [2,3,5,4,1,7]
    Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].

    Example 2:
    Input: nums = [1,2,3,4,4,3,2,1], n = 4
    Output: [1,4,2,3,3,2,4,1]

    Example 3:
    Input: nums = [1,1,2,2], n = 2
    Output: [1,2,1,2]
    */

    @Test
    public void test() {
        Assert.assertArrayEquals(IntStream.of(2, 3, 5, 4, 1, 7).toArray(), shuffle(IntStream.of(2, 5, 1, 3, 4, 7).toArray(), 3));
        Assert.assertArrayEquals(IntStream.of(1, 4, 2, 3, 3, 2, 4, 1).toArray(), shuffle(IntStream.of(1, 2, 3, 4, 4, 3, 2, 1).toArray(), 4));
        Assert.assertArrayEquals(IntStream.of(1, 2, 1, 2).toArray(), shuffle(IntStream.of(1, 1, 2, 2).toArray(), 2));
    }

    public int[] shuffle(int[] nums, int n) {
        BitSet bitSet = new BitSet(n << 1);
        for (int i = 1, len = nums.length - 1; i < len; i++)
            for (int j = i, toSwap = nums[j], tmp; !bitSet.get(j); ) {
                bitSet.set(j);
                tmp = toSwap;
                toSwap = nums[j = j < n ? j << 1 : ((j - n) << 1) + 1];
                nums[j] = tmp;
            }
        return nums;
    }
}
