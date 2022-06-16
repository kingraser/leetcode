package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static leetcode.util.ArrayUtil.swap;

public class ReverseVowelsofaString {

  /*
  Write a function that takes a string as input and reverse only the vowels of a string.
  
  Example 1:
  Given s = "hello", return "holle".
  
  Example 2:
  Given s = "leetcode", return "leotcede". 
  */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"uoiea", "aeiou"},
                {"holle", "hello"},
                {"leotcede", "leetcode"}
        });
    }

    private static final Set<Character> VOWEL = Stream.of('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U')
            .collect(Collectors.toSet());

    public String reverseVowels(String s) {
        char[] array = s.toCharArray();
        for (int l = 0, r = s.length() - 1; l < r; ) {
            for (; l < r && !VOWEL.contains(array[l]); l++) ;
            for (; l < r && !VOWEL.contains(array[r]); r--) ;
            swap(array, l++, r--);
        }
        return new String(array);
    }

}
