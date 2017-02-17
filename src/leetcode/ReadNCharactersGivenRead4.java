package leetcode;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ReadNCharactersGivenRead4 {

  /*
  The API: int read4(char *buf) reads 4 characters at a time from a file.
  The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
  By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
  Note:
  The read function will only be called once for each test case.
  */

  @Test
  public void test() {
    char[] buf = new char[100];
    assertArrayEquals("Hello".toCharArray(), Arrays.copyOf(buf, read(buf, 5)));
    assertArrayEquals(" ".toCharArray(), Arrays.copyOf(buf, read(buf, 1)));
    assertArrayEquals("Wor".toCharArray(), Arrays.copyOf(buf, read(buf, 3)));
    assertArrayEquals("ld".toCharArray(), Arrays.copyOf(buf, read(buf, 4)));
    assertArrayEquals("".toCharArray(), Arrays.copyOf(buf, read(buf, 4)));
    api = new API("Hello World");
    assertEquals(11, read(new char[100], 100));
  }

  API api = new API("Hello World");

  private int idx = 0, len = 0;
  private char[] cache = new char[4];

  public int read(char[] buf, int n) {
    int count = 0;
    for (; count < n; idx %= len)
      if (idx == 0 && (len = api.read4(cache)) == 0) break;
      else for (; count < n && idx < len; buf[count++] = cache[idx++]);
    return count;
  }

  static class API {
    private String text;
    private int idx = 0;

    public API(String s) {
      text = s;
    }

    public int read4(char[] buf4) {
      int val = idx, end = Math.min(idx + 4, text.length());
      for (int i = 0; idx < end; buf4[i++] = text.charAt(idx++));
      return end - val;
    }
  }
}
