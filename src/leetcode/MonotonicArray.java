package leetcode;

/**
 * @author Wit
 */
public class MonotonicArray {
    /*
    An array is monotonic if it is either monotone increasing or monotone decreasing.
    An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
    Return true if and only if the given array A is monotonic.

    Example 1:
    Input: [1,2,2,3]
    Output: true

    Example 2:
    Input: [6,5,4,4]
    Output: true

    Example 3:
    Input: [1,3,2]
    Output: false

    Example 4:
    Input: [1,2,4,5]
    Output: true

    Example 5:
    Input: [1,1,1]
    Output: true

    Note:
    1 <= A.length <= 50000
    -100000 <= A[i] <= 100000
    */

    public boolean isMonotonic(int[] array) {
        for (int i = 1, store = 0, c; i < array.length; )
            if ((c = Integer.compare(array[i - 1], array[i++])) == 0) continue;
            else if (store == 0) store = c;
            else if (c != store) return false;
        return true;
    }
}
