package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntegertoEnglishWords {

  /*
  Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
  
  For example,
  
  123 -> "One Hundred Twenty Three"
  12345 -> "Twelve Thousand Three Hundred Forty Five"
  1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
  
  Hint:
  
    Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
    Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
    There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
  */

  private final String[] lessThan20 = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
      "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" },
      tens = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" },
      thousands = { "", "Thousand", "Million", "Billion" };

  public String numberToWords(int num) {
    if (num < 20) return lessThan20[num];
    String words = "";
    for (int i = 0; num > 0; i++, num /= 1000)
      if (num % 1000 != 0) words = String.join(" ", helper(num % 1000), thousands[i], words).trim();
    return words;
  }

  private String helper(int num) {
    if (num == 0) return "";
    else if (num < 20) return lessThan20[num];
    else if (num < 100) return String.join(" ", tens[num / 10], helper(num % 10)).trim();
    else return String.join(" ", lessThan20[num / 100], "Hundred", helper(num % 100)).trim();
  }

  @Test
  public void test() {
    assertEquals("One Hundred Twenty Three", numberToWords(123));
    assertEquals("Twelve Thousand Three Hundred Forty Five", numberToWords(12345));
    assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", numberToWords(1234567));
  }

}
