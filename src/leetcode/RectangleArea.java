package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RectangleArea {

  /*
  Find the total area covered by two rectilinear rectangles in a 2D plane.  
  Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
  */

  @Test
  public void test() {
    assertEquals(1, computeArea(0, 0, 1, 1, 0, 0, 1, 1));
  }

  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    return (C - A) * (D - B) + (G - E) * (H - F)
        - Math.max(0, Math.min(C, G) - Math.max(A, E)) * Math.max(0, Math.min(D, H) - Math.max(B, F));
  }
}
