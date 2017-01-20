package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReadNCharactersGivenRead4 {

  /*
  The API: int read4(char *buf) reads 4 characters at a time from a file.
  The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
  By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
  Note:
  The read function will only be called once for each test case.
  */

  private String text = "hello world";
  private int idx = 0;

  @Test
  public void test() {
    assertEquals(11, read(new char[100], 100));
  }

  public int read(char[] buf, int n) {
    int count = 0, res4;
    char[] buf4 = new char[4];
    while ((res4 = read4(buf4)) > 0)
      for (int i = 0; i < res4 && n > 0; buf[count++] = buf4[i++], n--);
    return count;
  }

  private int read4(char[] buf4) {
    int val = idx, end = Math.min(idx + 4, text.length());
    for (int i = 0; idx < end; buf4[i++] = text.charAt(idx++));
    return end - val;
  }

}
