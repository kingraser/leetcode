package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class GeneralizedAbbreviation {

  /*
  Write a function to generate the generalized abbreviations of a word.  
  Example:  
  Given word = "word", return the following list (order does not matter):  
  ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
  */
  @Test
  public void test() {
    assertEquals(
        Stream.of("word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1",
            "3d", "w3", "4").sorted().collect(Collectors.toList()),
        generateAbbreviations("word").stream().sorted().collect(Collectors.toList()));
  }

  public List<String> generateAbbreviations(String word) {
    List<String> result = new ArrayList<>();
    dfs(result, word.toCharArray(), 0, "", 0);
    return result;
  }

  private void dfs(List<String> result, char[] word, int idx, String s, int count) {
    if (idx == word.length) {
      result.add(count > 0 ? s + count : s);
      return;
    }
    dfs(result, word, idx + 1, s, count + 1);
    dfs(result, word, idx + 1, s + (count > 0 ? count : "") + word[idx], 0);
  }
}
