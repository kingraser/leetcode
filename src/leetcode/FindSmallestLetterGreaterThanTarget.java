package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindSmallestLetterGreaterThanTarget {
  /*
  Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, 
  find the smallest element in the list that is larger than the given target.  
  Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
  
  Examples:  
  Input:
  letters = ["c", "f", "j"]
  target = "a"
  Output: "c"
  
  Input:
  letters = ["c", "f", "j"]
  target = "c"
  Output: "f"
  
  Input:
  letters = ["c", "f", "j"]
  target = "d"
  Output: "f"
  
  Input:
  letters = ["c", "f", "j"]
  target = "g"
  Output: "j"
  
  Input:
  letters = ["c", "f", "j"]
  target = "j"
  Output: "c"
  
  Input:
  letters = ["c", "f", "j"]
  target = "k"
  Output: "c"
  
  Note:  
    letters has a length in range [2, 10000].
    letters consists of lowercase letters, and contains at least 2 unique letters.
    target is a lowercase letter.  
  */

  @Test
  public void test() {
    assertEquals('c', nextGreatestLetter("cfj".toCharArray(), 'a'));
    assertEquals('f', nextGreatestLetter("cfj".toCharArray(), 'c'));
    assertEquals('f', nextGreatestLetter("cfj".toCharArray(), 'd'));
    assertEquals('j', nextGreatestLetter("cfj".toCharArray(), 'g'));
    assertEquals('c', nextGreatestLetter("cfj".toCharArray(), 'j'));
    assertEquals('c', nextGreatestLetter("cfj".toCharArray(), 'k'));
  }

  public char nextGreatestLetter(char[] letters, char target) {
    int left = 0;
    for (int mid, right = letters.length; left < right;)
      if (letters[(mid = (right + left) >> 1)] > target) right = mid;
      else left = mid + 1;
    return letters[left % letters.length];
  }

}
