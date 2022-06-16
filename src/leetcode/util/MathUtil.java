package leetcode.util;

import java.util.Arrays;

public class MathUtil {

  public static int lowestCommonMultiple(int x, int y) {
    return x * y / gcd(x, y);
  }

  public static int gcd(int x, int y) {
    return x == 0 ? y : y == 0 ? x : gcd(y % x, x);
  }

  public static int max(int... args) {
    return Arrays.stream(args).max().getAsInt();
  }

  public static int min(int... args) {
    return Arrays.stream(args).min().getAsInt();
  }

  public static long max(long... args) {
    return Arrays.stream(args).max().getAsLong();
  }

  public static long min(long... args) {
    return Arrays.stream(args).min().getAsLong();
  }

  public static int log10(long n) {
    int count = 0;
    while (n > 0) {
      count++;
      n /= 10;
    }
    return count;
  }

  public static long join(int a, int b) {
    return (((long) a) << 32) | (b & 0xFFFF_FFFFL);
  }

  public static int[] split(long l) {
    return new int[] { (int) (l >> 32), (int) l };
  }
}
