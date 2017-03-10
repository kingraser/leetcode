package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class DecodeString {

  /*
  Given an encoded string, return it's decoded string.
  The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
  Note that k is guaranteed to be a positive integer.
  You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
  Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
  For example, there won't be input like 3a or 2[4].
  
  Examples:
  
  s = "3[a]2[bc]", return "aaabcbc".
  s = "3[a2[c]]", return "accaccacc".
  s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
  */

  @Test
  public void test() {
    assertEquals("aaabcbc", decodeString("3[a]2[bc]"));
    assertEquals("accaccacc", decodeString("3[a2[c]]"));
    assertEquals("abcabccdcdcdef", decodeString("2[abc]3[cd]ef"));
  }

  public String decodeString(String s) {
    Stack<Integer> times = new Stack<>();
    Stack<StringBuilder> stack = new Stack<>();
    stack.push(new StringBuilder());
    for (int i = 0, num = 0, c; i < s.length(); i++)
      if ((c = s.charAt(i)) >= '0' && c <= '9') num = num * 10 + c - '0';
      else if (c == '[') {
        stack.push(new StringBuilder());
        times.push(num);
        num = 0;
      } else if (c == ']') {
        StringBuilder top = repeat(stack.pop(), times.pop());
        stack.peek().append(top);
      } else stack.peek().append((char) c);
    return stack.peek().toString();
  }

  private StringBuilder repeat(StringBuilder sb, int times) {
    for (int len = sb.length(); --times > 0;)
      for (int i = 0; i < len; sb.append(sb.charAt(i++)));
    return sb;
  }
}
