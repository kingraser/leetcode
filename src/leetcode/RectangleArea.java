/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class RectangleArea {

  /*
  Find the total area covered by two rectilinear rectangles in a 2D plane.
  
  Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
  */

  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    return (C - A) * (D - B) + (G - E) * (H - F)
        - Math.max(0, Math.min(C, G) - Math.max(A, E)) * Math.max(0, Math.min(D, H) - Math.max(B, F));
  }
}
