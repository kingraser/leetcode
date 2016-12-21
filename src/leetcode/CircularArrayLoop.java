package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CircularArrayLoop {

  @Test
  public void test() {
    assertFalse(circularArrayLoop(new int[] { -2, 1, -1, -2, -2 }));
    assertTrue(circularArrayLoop(new int[] { 2, -1, 1, 2, 2 }));
    assertFalse(circularArrayLoop(new int[] { -1, 2 }));
  }

  private int next(int now, int[] A) {
    return ((now + A[now]) % A.length + A.length) % A.length;
  }

  private boolean same(int flag, int b) {
    return b == 0 ? false : (flag ^ (b >>> 31)) == 0;
  }

  public boolean circularArrayLoop(int[] nums) {
    if (nums == null || nums.length < 2) return false;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) continue;
      for (int flag = nums[i] >>> 31, slow = i, fast = next(slow, nums); same(flag, nums[slow])
          && same(flag, nums[fast]); slow = next(slow, nums), fast = next(next(fast, nums), nums))
        if (slow == fast) if (slow == next(slow, nums)) break;
        else return true;
      for (int flag = nums[i] >>> 31, j = i, next; same(flag, nums[j]); next = next(j, nums), nums[j] = 0, j = next);
    }
    return false;
  }

}
