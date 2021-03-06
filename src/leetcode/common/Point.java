
package leetcode.common;

import java.util.Objects;

public class Point {
  public int x, y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object obj) {
    if (Objects.isNull(obj) || !(obj instanceof Point)) return false;
    Point point = (Point) obj;
    return x == point.x && y == point.y;
  }

  @Override
  public String toString() {
    return String.format("[x:%d,y:%d]", x, y);
  }
}
