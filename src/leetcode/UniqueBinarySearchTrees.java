package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UniqueBinarySearchTrees {

  /*
  Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
  
  For example,
  Given n = 3, there are a total of 5 unique BST's.
  
     1         3     3      2      1
      \       /     /      / \      \
       3     2     1      1   3      2
      /     /       \                 \
     2     1         2                 3
  */

  /*
  Sort and we will see the regularity.
   1    1        2        3     3 
    \    \      / \      /     /
     3    2    1   3    2     1
    /      \           /       \
   2        3         1         2             
  The count of BST with some named value is left tree count multiply right tree count. 
  Let f(i) is count of BST of 1...i
  f(i) = Sum(f(k − 1) * f(i − k))(k --> 1...i)
  Thus it is cantalan number
  Let h(0) = 1, h(1) = 1
  h(n)= h(0) * h(n - 1) + h(1) * h(n - 2) + ... + h(n - 1) * h(0) (n>=2)
  h(2)= h(0) * h(1) + h(1) * h(0) = 1 * 1 + 1 * 1 = 2
  h(3)= h(0) * h(2) + h(1) * h(1) + h(2) * h(0) = 1 * 2 + 1 * 1 + 2 * 1 = 5
  h(n) = C(2n, n) / (n + 1)
  */

  public int numTrees(int n) {
    long ans = 1, i = 1;
    for (; i <= n; ans = ans * (i + n) / i++);
    return (int) (ans / i);
  }

  @Test
  public void test() {
    assertEquals(5, numTrees(3));
  }

}
