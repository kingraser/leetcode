package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.BitSet;
import java.util.stream.Stream;

import org.junit.Test;

public class PalindromePermutation {

  /*
  Given a string, determine if a permutation of the string could form a palindrome. 
  For example, "code" -> False, "aab" -> True, "carerac" -> True. 
  Hint:
    Consider the palindromes of odd vs even length. What difference do you notice?
    Count the frequency of each character.
    If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times? 
  */

  @Test
  public void test() {
    Stream.of("aab", "carerac").forEach(s -> assertTrue(canPermutePalindrome(s)));
    Stream.of("code").forEach(s -> assertFalse(canPermutePalindrome(s)));
  }

  boolean canPermutePalindrome(String s) {
    BitSet bitSet = new BitSet(128);
    s.chars().forEach(c -> bitSet.flip(c));
    return bitSet.cardinality() < 2;
  }

}
