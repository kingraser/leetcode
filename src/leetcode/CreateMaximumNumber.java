package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CreateMaximumNumber {

  /*
  Given two arrays of length m and n with digits 0-9 representing two numbers. 
  Create the maximum number of length k <= m + n from digits of the two. 
  The relative order of the digits from the same array must be preserved. 
  Return an array of the k digits. 
  You should try to optimize your time and space complexity.
  
  Example 1:
  
  nums1 = [3, 4, 6, 5]
  nums2 = [9, 1, 2, 5, 8, 3]
  k = 5
  return [9, 8, 6, 5, 3]
  
  Example 2:
  
  nums1 = [6, 7]
  nums2 = [6, 0, 4]
  k = 5
  return [6, 7, 6, 0, 4]
  
  Example 3:
  
  nums1 = [3, 9]
  nums2 = [8, 9]
  k = 3
  return [9, 8, 9] 
  */

  /*
  The basic idea:
  To create max number of length k from two arrays, 
  you need to create max number of length i from array one and max number of length k-i from array two, 
  then combine them together. 
  After trying all possible i, you will get the max number created from two arrays.   
  
  Optimization:    
  Suppose nums1 = [3, 4, 6, 5], nums2 = [9, 1, 2, 5, 8, 3], 
  the maximum number you can create from nums1 is [6, 5] with length 2. 
  For nums2, it's [9, 8, 3] with length 3. Merging the two sequence, we have [9, 8, 6, 5, 3], 
  which is the max number we can create from two arrays without length constraint. 
  If the required length k<=5, we can simply trim the result to required length from front. 
  For instance, if k=3, then [9, 8, 6] is the result.
  
  Suppose we need to create max number with length 2 from num = [4, 5, 3, 2, 1, 6, 0, 8]. 
  The simple way is to use a stack, first we push 4 and have stack [4], then comes 5 > 4, 
  we pop 4 and push 5, stack becomes [5], 3 < 5, we push 3, 
  stack becomes [5, 3]. Now we have the required length 2, 
  but we need to keep going through the array in case a larger number comes, 2 < 3, 
  we discard it instead of pushing it because the stack already grows to required size 2. 
  1 < 3, we discard it. 6 > 3, we pop 3, since 6 > 5 and there are still elements left, 
  we can continue to pop 5 and push 6, the stack becomes [6], since 0 < 6, we push 0, the stack becomes [6, 0], 
  the stack grows to required length again. Since 8 > 0, we pop 0, although 8 > 6, 
  we can't continue to pop 6 since there is only one number, which is 8, left, if we pop 6 and push 8, 
  we can't get to length 2, so we push 8 directly, the stack becomes [6, 8].
  
  In the basic idea, we mentioned trying all possible length i. 
  If we create max number for different i from scratch each time, 
  that would be a waste of time. Suppose num = [4, 9, 3, 2, 1, 8, 7, 6], 
  we need to create max number with length from 1 to 8. For i==8, result is the original array. 
  For i==7, we need to drop 1 number from array, since 9 > 4, we drop 4, the result is [9, 3, 2, 1, 8, 7, 6]. 
  For i==6, we need to drop 1 more number, 3 < 9, skip, 2 < 3, skip, 1 < 2, skip, 8 > 1, we drop 1, the result is [9, 3, 2, 8, 7, 6]. 
  For i==5, we need to drop 1 more, but this time, we needn't check from beginning, during last scan, 
  we already know [9, 3, 2] is monotonically non-increasing, so we check 8 directly, since 8 > 2, we drop 2, the result is [9, 3, 8, 7, 6]. 
  For i==4, we start with 8, 8 > 3, we drop 3, the result is [9, 8, 7, 6]. 
  For i==3, we start with 8, 8 < 9, skip, 7 < 8, skip, 6 < 7, skip, by now, 
  we've got maximum number we can create from num without length constraint. 
  So from now on, we can drop a number from the end each time. The result is [9, 8, 7], 
  For i==2, we drop last number 7 and have [9, 8]. 
  For i==1, we drop last number 8 and have [9]. 
  */

    public int[] maxNumber(int[] A1, int[] A2, int k) {
        int[] result = new int[k], tmp = new int[k];
        List<int[]> dp1 = getMax(A1), dp2 = getMax(A2);
        for (int i = Math.max(k - A2.length, 0), l = Math.min(A1.length, k); i <= l; i++) {
            int[] B1 = dp1.get(dp1.size() - 1 - i), B2 = dp2.get(dp2.size() - 1 - k + i);
            for (int p1 = 0, p2 = 0, p = 0; p < k; p++)
                tmp[p] = greater(B1, p1, B2, p2) ? B1[p1++] : B2[p2++];
            if (greater(tmp, 0, result, 0)) result = Arrays.copyOf(tmp, tmp.length);
        }
        return result;
    }

    private boolean greater(int[] A1, int i1, int[] A2, int i2) {
        for (; i1 < A1.length && i2 < A2.length; i1++, i2++)
            if (A1[i1] > A2[i2]) return true;
            else if (A1[i1] < A2[i2]) return false;
        return i1 != A1.length;
    }

    private List<int[]> getMax(int[] A) {
        List<int[]> res = new ArrayList<>(List.of(A));
        for (int idx = 0, len = A.length; res.size() < len; ) res.add(A = copy(A, idx = getOmitIdx(A, idx)));
        res.add(new int[0]);
        return res;
    }

    private int getOmitIdx(int[] A, int idx) {
        if (idx >= A.length) idx = A.length - 1;
        if (idx > 0 && A[idx - 1] < A[idx]) return --idx;
        for (int end = A.length - 1; idx < end && A[idx] >= A[idx + 1]; ) idx++;
        return idx;
    }

    private int[] copy(int[] A, int omitIdx) {
        int[] result = new int[A.length - 1];
        for (int i = 0, j = 0; i < A.length; i++)
            if (i != omitIdx) result[j++] = A[i];
        return result;
    }

    int[] expected = null, A = null, B = null;

    @Test
    public void test() {
        set(new int[]{9, 8, 6, 5, 3}, new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3});
        assertArrayEquals(expected, maxNumber(A, B, 5));
        set(new int[]{6, 7, 6, 0, 4}, new int[]{6, 7}, new int[]{6, 0, 4});
        assertArrayEquals(expected, maxNumber(A, B, 5));
        set(new int[]{9, 8, 9}, new int[]{3, 9}, new int[]{8, 9});
        assertArrayEquals(expected, maxNumber(A, B, 3));
        set(new int[]{5, 5, 4}, new int[]{5, 5, 1}, new int[]{4, 0, 1});
        assertArrayEquals(expected, maxNumber(A, B, 3));
        expected = new int[]{9, 9, 9, 9, 9, 8, 7, 5, 6, 3, 4, 2, 4, 7, 4, 5, 7, 7, 2, 5, 6, 3, 6, 7, 2, 2, 8, 4, 6, 0, 4,
                7, 8, 9, 1, 7, 0, 3, 5, 3, 2, 8, 1, 6, 6, 1, 0, 8, 4, 0};
        A = new int[]{8, 0, 4, 4, 1, 7, 3, 6, 5, 9, 3, 6, 6, 0, 2, 5, 1, 7, 7, 7, 8, 7, 1, 4, 4, 5, 4, 8, 7, 6, 2, 2, 9,
                4, 7, 5, 6, 2, 2, 8, 4, 6, 0, 4, 7, 8, 9, 1, 7, 0};
        B = new int[]{6, 9, 8, 1, 1, 5, 7, 3, 1, 3, 3, 4, 9, 2, 8, 0, 6, 9, 3, 3, 7, 8, 3, 4, 2, 4, 7, 4, 5, 7, 7, 2, 5,
                6, 3, 6, 7, 0, 3, 5, 3, 2, 8, 1, 6, 6, 1, 0, 8, 4};
        assertArrayEquals(expected, maxNumber(A, B, 50));
    }

    private void set(int[] expected, int[] A, int[] B) {
        this.expected = expected;
        this.A = A;
        this.B = B;
    }

}
