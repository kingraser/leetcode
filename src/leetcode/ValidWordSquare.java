package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ValidWordSquare {

  /*
  Given a sequence of words, check whether it forms a valid word square.  
  A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤k < max(numRows, numColumns).
  Note:  
    The number of words given is at least 1 and does not exceed 500.
    Word length will be at least 1 and does not exceed 500.
    Each word contains only lowercase English alphabet a-z.  
  Example 1:  
  Input:
  [
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
  ]  
  Output: true  
  Explanation:
  The first row and first column both read "abcd".
  The second row and second column both read "bnrt".
  The third row and third column both read "crmy".
  The fourth row and fourth column both read "dtye".  
  Therefore, it is a valid word square.
   
  Example 2:  
  Input:
  [
  "abcd",
  "bnrt",
  "crm",
  "dt"
  ]
  
  Output: true  
  Explanation:
  The first row and first column both read "abcd".
  The second row and second column both read "bnrt".
  The third row and third column both read "crm".
  The fourth row and fourth column both read "dt".  
  Therefore, it is a valid word square.
    
  Example 3:  
  Input:
  [
  "ball",
  "area",
  "read",
  "lady"
  ]
  Output: false 
  Explanation:
  The third row reads "read" while the third column reads "lead".
  Therefore, it is NOT a valid word square.
  */

  @Test
  public void test() {
    assertTrue(validWordSquare(Arrays.asList("abcd", "bnrt", "crmy", "dtye")));
    assertTrue(validWordSquare(Arrays.asList("abcd", "bnrt", "crm", "dt")));
    assertFalse(validWordSquare(Arrays.asList("ball", "area", "read", "lady")));
  }

  public boolean validWordSquare(List<String> words) {
    if (words.isEmpty()) return true;
    if (words.size() != words.get(0).length()) return false;
    for (int i = 0; i < words.size(); i++)
      for (int j = 0; j < words.get(i).length(); j++)
        if (i >= words.get(j).length() || words.get(i).charAt(j) != words.get(j).charAt(i)) return false;
    return true;
  }

}
