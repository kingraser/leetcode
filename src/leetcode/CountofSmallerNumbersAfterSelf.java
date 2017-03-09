package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class CountofSmallerNumbersAfterSelf {

  /*
  You are given an integer array nums and you have to return a new counts array. 
  The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
  Example:
  
  Given nums = [5, 2, 6, 1] 
  To the right of 5 there are 2 smaller elements (2 and 1).
  To the right of 2 there is only 1 smaller element (1).
  To the right of 6 there is 1 smaller element (1).
  To the right of 1 there is 0 smaller element.
  Return the array [2, 1, 1, 0].  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(2, 1, 1, 0), countSmaller(new int[] { 5, 2, 6, 1 }));
  }

  // binary search tree
  public List<Integer> countSmallerZero(int[] nums) {
    TreeNode root = null;
    int[] result = new int[nums.length];
    for (int i = nums.length - 1; i >= 0; i--)
      root = insert(root, nums[i], result, i, 0);
    return Arrays.stream(result).boxed().collect(Collectors.toList());
  }

  public TreeNode insert(TreeNode root, int val, int[] ans, int index, int preSum) {
    if (root == null) {
      root = new TreeNode(val, 0);
      ans[index] = preSum;
    } else if (root.val > val) {
      root.small++;
      root.left = insert(root.left, val, ans, index, preSum);
    } else root.right = insert(root.right, val, ans, index, root.small + preSum + (root.val < val ? 1 : 0));
    return root;
  }

  class TreeNode {
    int val, small;
    TreeNode left, right;

    public TreeNode(int val, int small) {
      this.val = val;
      this.small = small;
    }
  }

  // merge sort
  public List<Integer> countSmaller(int[] nums) {
    int[] result = new int[nums.length];
    mergeCount(result, IntStream.range(0, nums.length).mapToObj(i -> new Node(nums[i], i)).toArray(l -> new Node[l]), 0,
        nums.length - 1);
    return Arrays.stream(result).boxed().collect(Collectors.toList());
  }

  /**
   * @param result
   * @param nums
   * @param left inclusive
   * @param right inclusive
   */
  private void mergeCount(int[] result, Node[] nums, int left, int right) {
    if (left >= right) return;
    int mid = (left + right) >> 1;
    mergeCount(result, nums, left, mid);
    mergeCount(result, nums, mid + 1, right);
    Node[] cache = new Node[right - left + 1];
    int rightIdx1 = mid + 1, rightIdx2 = mid + 1;
    for (int leftIdx = left, idx = 0; leftIdx <= mid; leftIdx++, idx++) {
      for (; rightIdx1 <= right && nums[leftIdx].val > nums[rightIdx1].val; rightIdx1++);
      for (; rightIdx2 <= right && nums[rightIdx2].val < nums[leftIdx].val; cache[idx++] = nums[rightIdx2++]);
      cache[idx] = nums[leftIdx];
      result[nums[leftIdx].idx] += rightIdx1 - mid - 1;
    }
    System.arraycopy(cache, 0, nums, left, rightIdx2 - left);
  }

  class Node {
    int val, idx;

    public Node(int val, int idx) {
      this.val = val;
      this.idx = idx;
    }
  }
}
