/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月17日<p>
//-------------------------------------------------------
public class TextJustification {

  /*
  Given an array of words and a length L, 
  format the text such that each line has exactly L characters and is fully (left and right) justified.
  You should pack your words in a greedy approach; 
  that is, pack as many words as you can in each line. 
  Pad extra spaces ' ' when necessary so that each line has exactly L characters.
  Extra spaces between words should be distributed as evenly as possible. 
  If the number of spaces on a line do not divide evenly between words, 
  the empty slots on the left will be assigned more spaces than the slots on the right.
  
  For the last line of text, 
  it should be left justified and no extra space is inserted between words.
  
  For example,
  words: ["This", "is", "an", "example", "of", "text", "justification."]
  L: 16. 
  
  Return the formatted lines as:    
      [
          "This    is    an",
          "example  of text",
          "justification.  "
      ]
  */

  public List<String> fullJustify(String[] words, int maxWidth) {
    LinkedList<String> result = new LinkedList<>(), line = new LinkedList<>();
    for (int i = 0, len; i < words.length; result.add(getLine(line, maxWidth, len, i == words.length)))
      for (len = 0; i < words.length && len + line.size() + words[i].length() <= maxWidth; i++) {
        line.add(words[i]);
        len += words[i].length();
      }
    return result;
  }

  private String getLine(Deque<String> line, int maxWidth, int length, boolean isLastLine) {
    int intervals = line.size() - 1, spaces = isLastLine ? 1 : ((maxWidth - length) / intervals),
        extraSpaces = isLastLine ? 0 : ((maxWidth - length) % intervals);
    StringBuilder sb = new StringBuilder(line.pollFirst());
    while (!line.isEmpty()) {
      for (int i = 0; i++ < spaces; sb.append(' '));
      if (extraSpaces-- > 0) sb.append(' ');
      sb.append(line.poll());
    }
    for (; sb.length() < maxWidth; sb.append(' '));
    return sb.toString();
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList("This    is    an", "example  of text", "justification.  "),
        fullJustify(new String[] { "This", "is", "an", "example", "of", "text", "justification." }, 16));
  }

}
