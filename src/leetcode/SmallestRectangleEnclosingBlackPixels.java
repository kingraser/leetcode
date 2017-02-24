package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SmallestRectangleEnclosingBlackPixels {

  /*
  An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
  The black pixels are connected, i.e., there is only one black region. 
  Pixels are connected horizontally and vertically. 
  Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
  
  For example, given the following image:
  
  [
  "0010",
  "0110",
  "0100"
  ]
  
  and x = 0, y = 2,
  Return 6.
  */

  @Test
  public void test() {
    assertEquals(6, minArea(new char[][] { "0010".toCharArray(), "0110".toCharArray(), "0100".toCharArray() }, 0, 2));
  }

  public int minArea(char[][] screen, int x, int y) {
    int height = screen.length, width = screen[0].length;
    int left = binarySearch(0, y, 0, height, true, screen, true);
    int right = binarySearch(y + 1, width, 0, height, false, screen, true);
    int top = binarySearch(0, x, left, right, true, screen, false);
    int bottom = binarySearch(x + 1, height, left, right, false, screen, false);
    return (right - left) * (bottom - top);
  }

  /**
   * @param small small pointer
   * @param large large pointer
   * @param min small limit
   * @param max large limit
   * @param direction true as to small false as to large
   * @param screen
   * @param isHorizontal true as horizontal false as vertical
   * @return
   */
  private int binarySearch(int small, int large, int min, int max, boolean direction, char[][] screen,
      boolean isHorizontal) {
    for (int k, mid; small != large;) {
      for (k = min, mid = (small + large) >> 1; k < max
          && (isHorizontal ? screen[k][mid] : screen[mid][k]) == '0'; k++);
      if (k < max == direction) large = mid;
      else small = mid + 1;
    }
    return small;
  }
}
