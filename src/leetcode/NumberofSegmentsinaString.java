package leetcode;

public class NumberofSegmentsinaString {

  public int countSegments(String s) {
    int result = 0;
    for (int i = 0, len = s.length(); i < len;) {
      if (s.charAt(i++) != ' ') result++;
      for (; i < len && s.charAt(i) != ' '; i++);
    }
    return result;
  }

}
