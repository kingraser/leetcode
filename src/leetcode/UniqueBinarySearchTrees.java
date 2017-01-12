/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
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
          如果把上例的顺序改一下,就可以看出规律了。
             1    1        2        3     3 
              \    \      / \      /     /
               3    2    1   3    2     1
              /      \           /       \
             2        3         1         2
          比如,以1为根的树的个数,等于左子树的个数乘以右子树的个数,
          左子树是0个元素的树,右子树是2个元素的树。
          以2为根的树的个数,等于左子树的个数乘以右子树的个数,
          左子树是1个元素的树,右子树也是1个元素的树。依此类推。
          当数组为1, 2, 3, ..., n 时,基于以下原则的构建的BST树具有唯一性:
          以i为根节点的树,其左子树由[1, i-1]构成,其右子树由[i+1, n]构成。
          定义f(i)为以[1, i]能产生的Unique Binary Search Tree的数目,
          则如果数组为空,毫无疑问,只有一种 BST,即空树,f(0)=1。
          如果数组仅有一个元素1,只有一种 BST,单个节点,f(1)=1。
          如果数组有两个元素1,2,那么有两种可能            
          f(2) = f(0) * f(1) ,1为根的情况
               + f(1) * f(0) ,2为根的情况
          再看一看3个元素的数组,可以发现 BST 的取值方式如下:
          f(3) = f(0) * f(2) ,1为根的情况
               + f(1) * f(1) ,2为根的情况
               + f(2) * f(0) ,3为根的情况
          所以,由此观察,可以得出 f 的递推公式为
          f(i) = Sum(f(k − 1) * f(i − k))(k --> 1...i)
          至此,问题划归为一维动态规划。
          
          数学法
          由上显然是cantalan数
          令h(0)=1,h(1)=1，catalan数满足递推式：
          h(n)= h(0)*h(n-1)+h(1)*h(n-2) + ... + h(n-1)h(0) (n>=2)
          例如：h(2)=h(0)*h(1)+h(1)*h(0)=1*1+1*1=2
          h(3)=h(0)*h(2)+h(1)*h(1)+h(2)*h(0)=1*2+1*1+2*1=5
          h(n)=C(2n,n)/(n+1)
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
