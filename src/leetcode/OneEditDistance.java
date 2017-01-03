package leetcode;

public class OneEditDistance {

  public boolean isOneEditDistance(String s, String t) {
    return s.length() == t.length() ? isOneModified(s, t)
        : Math.abs(s.length() - t.length()) == 1 ? isOneDeleted(s, t) : false;
  }

  private boolean isOneModified(String s, String t) {
    int flag = 0;
    for (int i = 0, len = s.length(); i < len; i++)
      if (s.charAt(i) != t.charAt(i)) {
        if (flag > 0) return false;
        flag++;
      }
    return flag == 1;
  }

  public boolean isOneDeleted(String longer, String shorter) {
    // 找到第一组不一样的字符，看后面是否一样
    for (int i = 0; i < shorter.length(); i++) {
      if (longer.charAt(i) != shorter.charAt(i)) {
        return longer.substring(i + 1).equals(shorter.substring(i));
      }
    }
    return true;
  }

}
