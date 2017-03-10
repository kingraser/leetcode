package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LongestWordinDictionarythroughDeleting {

  /*
  Given a string and a string dictionary, 
  find the longest string in the dictionary that can be formed by deleting some characters of the given string. 
  If there are more than one possible results, return the longest word with the smallest lexicographical order. 
  If there is no possible result, return the empty string.
  
  Example 1:  
  Input: s = "abpcplea", d = ["ale","apple","monkey","plea"] 
  Output: "apple"
  
  Example 2:  
  Input: s = "abpcplea", d = ["a","b","c"]  
  Output: "a"
  
  Note:  
    All the strings in the input will only contain lower-case letters.
    The size of the dictionary won't exceed 1,000.
    The length of all the strings in the input won't exceed 1,000.  
  */

  @Test
  public void test() {
    assertEquals("apple", findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea")));
    assertEquals("a", findLongestWord("abpcplea", Arrays.asList("a", "b", "c")));
  }

  public String findLongestWord(String s, List<String> dictionary) {
    return dictionary.stream().sorted().filter(word -> isSubString(s, word))
        .sorted((s1, s2) -> s2.length() - s1.length()).findFirst().orElse("");
  }

  private boolean isSubString(String string, String subString) {
    int sIdx = 0, subIdx = 0, sLength = string.length(), subLength = subString.length(), subRemain;
    while ((subRemain = subLength - subIdx) > 0 && (sLength - sIdx) >= subRemain)
      if (subString.charAt(subIdx) == string.charAt(sIdx++)) subIdx++;
    return subIdx == subString.length();
  }
}
