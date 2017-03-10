package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReconstructOriginalDigitsfromEnglish {

  /*
  Given a non-empty string containing an out-of-order English representation of digits 0-9, 
  output the digits in ascending order.
  
  Note:  
    Input contains only lowercase English letters.
    Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
    Input length is less than 50,000.
  
  Example 1:  
  Input: "owoztneoer"  
  Output: "012"
  
  Example 2:  
  Input: "fviefuro"  
  Output: "45"  
  */

  @Test
  public void test() {
    assertEquals("012", originalDigits("owoztneoer"));
  }

  String[] english = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

  public String originalDigits(String s) {
    int[] digit = new int[128], count = new int[10];
    s.chars().forEach(c -> digit[c]++);
    remove(0, 'z', digit, count, 'o');
    remove(2, 'w', digit, count, 'o');
    remove(4, 'u', digit, count, 'f', 'o');
    remove(5, 'f', digit, count);
    remove(6, 'x', digit, count, 's');
    remove(7, 's', digit, count, 'n');
    remove(8, 'g', digit, count, 'h');
    remove(3, 'h', digit, count);
    remove(1, 'o', digit, count, 'n');
    remove(9, 'n', digit, count);
    count[9] >>= 1;
    return getResult(count);
  }

  private String getResult(int[] count) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10; i++)
      while (count[i]-- > 0)
        sb.append(english[i]);
    return sb.toString();
  }

  private void remove(int i, char c, int[] digit, int[] count, char... A) {
    if ((count[i] = digit[c]) == 0) return;
    for (char a : A)
      digit[a] -= digit[c];
    digit[c] = 0;
  }
}
