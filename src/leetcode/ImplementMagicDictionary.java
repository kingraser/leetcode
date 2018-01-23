package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ImplementMagicDictionary {

  /*
  Implement a magic directory with buildDict, and search methods.  
  For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.  
  For the method search, you'll be given a word, 
  and judge whether if you modify exactly one character into another character in this word, 
  the modified word is in the dictionary you just built.
  
  Example 1:  
  Input: buildDict(["hello", "leetcode"]), Output: Null
  Input: search("hello"), Output: False
  Input: search("hhllo"), Output: True
  Input: search("hell"), Output: False
  Input: search("leetcoded"), Output: False
  
  Note:  
    You may assume that all the inputs are consist of lowercase letters a-z.
    For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
    Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
  */

  @Test
  public void test() {
    MagicDictionary dictionary = new MagicDictionary();
    dictionary.buildDict(new String[] { "hello", "leetcode" });
    assertFalse(dictionary.search("hello"));
    assertTrue(dictionary.search("hhllo"));
    assertFalse(dictionary.search("hell"));
    assertFalse(dictionary.search("leetcoded"));
  }

  class MagicDictionary {
    Map<Integer, Map<String, Alphabet>> map = new HashMap<>();

    public void buildDict(String[] dict) {
      for (String s : dict) {
        Map<String, Alphabet> temp = map.computeIfAbsent(s.length(), k -> new HashMap<>());
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
          char c = array[i];
          array[i] = '*';
          temp.computeIfAbsent(new String(array), k -> new Alphabet()).incr(c);;
          array[i] = c;
        }
      }
    }

    public boolean search(String word) {
      Map<String, Alphabet> temp = map.get(word.length());
      if (temp == null) return false;
      char[] array = word.toCharArray();
      for (int i = 0; i < word.length(); i++) {
        char c = array[i];
        array[i] = '*';
        Alphabet bet = temp.get(new String(array));
        if (bet != null && (bet.count > 1 || (bet.count == 1 && bet.chars[c - 'a'] == 0))) return true;
        array[i] = c;
      }
      return false;
    }

    class Alphabet {
      public int count = 0, chars[] = new int[26];

      public void incr(char c) {
        count++;
        chars[c - 'a']++;
      }
    }
  }

}
