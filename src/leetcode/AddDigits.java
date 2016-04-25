/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class AddDigits {

  /*
  For example:
  Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.  
     
      任何一个整数模9同余于它的各数位上数字之和
      具体证明过程如下：
      设自然数N=a[n]a[n-1]…a[0]，其中a[0]，a[1]、…、a[n]分别是个位、十位、…上的数字
      再设M=a[0]+a[1]+…+a[n]
      求证：N≡M(mod 9).
      证明：
       ∵ N=a[n]a[n-1]…a[0]=a[n]*10^n+a[n-1]*10^(n-1)+…+a[1]*10+a[0].
       又∵      1≡1(mod 9),
       10≡1(mod 9),
       10^2≡1(mod 9),
       10^n≡1(mod 9).
      上面这些同余式两边分别同乘以a[0]、a[1]、a[2]、…、a[n]，再相加得：
  a[0]+a[1]*10+…+a[n]*10^n≡(a[0]+a[1]+…+a[n])(mod 9)，
      即 N≡M(mod 9)，得证。
  */

  @Test
  public void test() {
    Assert.assertEquals(2, addDigits(38));
  }

  public int addDigits(int num) {
    return num == 0 ? 0 : num % 9 == 0 ? 9 : num % 9;
  }

}
