package leetcode;

import static org.junit.Assert.assertTrue;

import java.util.BitSet;
import java.util.Set;

import org.junit.Test;

public class WordBreak {
  /*
  Given a string s and a dictionary of words dict, 
  determine if s can be segmented into a space-separated sequence of one or more dictionary words.
  
  For example, given
  s = "leetcode",
  dictionary = ["leet", "code"].
  
  Return true because "leetcode" can be segmented as "leet code". 
  */

  /*
  Let cuts(i) represents whether s[0,i) can break, thus
  cuts(i) = any_of(cuts(j) && dictionary.contains(s[j, i))), 0 ≤ j < i
  */

  @Test
  public void test() {
    Set<String> dict = Set.of("leet", "code");
    assertTrue(wordBreak("leetcode", dict));
  }

  public boolean wordBreak(String s, Set<String> dict) {
    BitSet cuts = new BitSet();
    cuts.set(0);
    for (int right = 1; right <= s.length(); right++)
      for (int left = right - 1; !cuts.get(right) && left >= 0; left--)
        if (cuts.get(left) && dict.contains(s.substring(left, right))) cuts.set(right);
    return cuts.get(s.length());
  }
}
