package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SuperPow {

  /*
  Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
  
  Example1:  
  a = 2
  b = [3]  
  Result: 8
  
  Example2:  
  a = 2
  b = [1,0]  
  Result: 1024 
  */

  /*
  phi(i) is count of positive integer j, where j < i and i and j are co-prime.
  For example, 
    phi(5) is 4, which are 1, 2, 3, 4
    phi(9) is 6, which are 1, 2, 4, 5, 7, 8
    phi(15) is 8, which are 1, 2, 4, 7, 8, 11, 13, 14
  */

  @Test
  public void test() {
    assertEquals((1L << 50) % 1337, superPow(2, new int[] { 5, 0 }));
  }

  public int superPow(int num, int[] powArray) {
    if (num % 1337 == 0) return 0;
    int pow = 0;
    for (int i : powArray)
      pow = (pow * 10 + i) % 1140; // phi(1337) = phi(7) * phi(191) = 6 * 190 = 1140
    return pow(num, pow == 0 ? 1440 : pow, 1337);
  }

  public int pow(int num, int pow, int mod) {
    int result = 1;
    for (num %= mod; pow != 0; pow >>= 1) {
      if ((pow & 1) != 0) result = (result * num) % mod;
      num = (num * num) % mod;
    }
    return result;
  }

}
