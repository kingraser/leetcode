package leetcode.interviews;

import leetcode.util.TestUtil;
import org.junit.Test;

public class FindSmallestKLengthSubArray {
    /*
    Given an int array, find out the smallest sub-array of length k.
    [1,3,4] is smaller than [1,3,5]
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{2, 1, 8}, new int[]{4, 4, 3, 2, 3, 5, 1, 8}, 3},
        });
    }

    public int[] find(int[] array, int k) {
        int stack[] = new int[k], size = 0;
        for (int i = 0; i < array.length; i++) {
            while (size > 0 && stack[size - 1] > array[i] && size + array.length - i > k) {
                size--;
            }
            stack[size++] = array[i];
        }
        return stack;
    }
}
