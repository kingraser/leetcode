package leetcode;

public class SuperPow {

  /*
  Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
  
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
  An integer can be represented as sum(k[i] * 10^i). So a^b can be represented as multiply(a^(k[i] * 10^i))
  We know (a*b) mod n = ((a mod n) * (b mod n)) mod n
  */

  public int superPow(int a, int[] b) {
    return superPow(a, b, 1337);
  }

  private int superPow(int a, int[] b, int mod) {
    int result = 1;
    for (int i = b.length - 1; i >= 0; i--)
      result = (result * pow(a, b[i], b.length - 1 - i, mod)) % mod;
    return result;
  }

  private int pow(int a, int k, int i, int mod) {
    // TODO Auto-generated method stub
    return 0;
  }

}
