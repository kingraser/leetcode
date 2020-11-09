import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class RotatedDigits {
    /*
    X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.
    A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other (on this case they are rotated in a different direction, in other words 2 or 5 gets mirrored); 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.
    Now given a positive number N, how many numbers X from 1 to N are good?

    Example:
    Input: 10
    Output: 4
    Explanation:
    There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
    Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
    Note: N will be in range [1, 10000].
    */

    private final int[] validDigitCountLessEqualIdx = new int[]{1, 2, 3, 3, 3, 4, 5, 5, 6, 7};
    private final int[] sameValidDigitCountLessEqualIdx = new int[]{1, 2, 2, 2, 2, 2, 2, 2, 3, 3};
    /**
     * 0 for not valid
     * 1 for valid but same
     * 2 for valid and different
     */
    private final int[] digits = new int[]{1, 1, 2, 0, 0, 2, 2, 0, 1, 2};

    public int rotatedDigits(int n) {
        int result = 0;
        for (int i = 0, digits[] = getDigits(n), digit, idx, leftDigitCount, countOfDifferentValidDigit = 0; i < digits.length; i++) {
            if ((digit = digits[i]) == 0 & (leftDigitCount = digits.length - 1 - i) > 0) continue;
            result += validDigitCountLessEqualIdx[idx = leftDigitCount == 0 ? digit : digit - 1] * Math.pow(7, leftDigitCount) - (countOfDifferentValidDigit > 0 ? 0 : sameValidDigitCountLessEqualIdx[idx] * Math.pow(3, leftDigitCount));
            if (this.digits[digit] == 0) break;
            else if (this.digits[digit] == 2) countOfDifferentValidDigit++;
        }
        return result;
    }

    int[] getDigits(int n) {
        int length = n < 10 ? 1 : n < 100 ? 2 : n < 1000 ? 3 : n < 10000 ? 4 : 5, result[] = new int[length];
        for (; length > 0; n /= 10) result[--length] = n % 10;
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(247, rotatedDigits(857));
        Assert.assertEquals(4, rotatedDigits(9));
        Assert.assertEquals(4, rotatedDigits(10));
    }
}
