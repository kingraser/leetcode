package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CountandSay {

  /*
  The count-and-say sequence is the sequence of integers beginning as follows:
  1, 11, 21, 1211, 111221, ...
  
  1 is read off as "one 1" or 11.
  11 is read off as "two 1s" or 21.
  21 is read off as "one 2, then one 1" or 1211.
  
  Given an integer n, generate the nth sequence.
  
  Note: The sequence of integers will be represented as a string. 
  */

    @Test
    public void test() {
        countAndSay(10);
        assertEquals(Arrays.asList("1", "11", "21", "1211", "111221", "312211", "13112221", "1113213211", "31131211131221",
                "13211311123113112211"), result);
    }

    public static List<String> result = new ArrayList<>(List.of("1"));

    @SuppressWarnings("UnusedReturnValue")
    public String countAndSay(int n) {
        if (result.size() < n--) for (int i = result.size() - 1; i < n; i++) result.add(getNext(result.get(i)));
        return result.get(n);
    }

    private String getNext(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 1; i < s.length(); sb.append(j - i).append(s.charAt(i)), i = j++)
            while (j < s.length() && s.charAt(i) == s.charAt(j)) j++;
        return sb.toString();
    }

}
