package leetcode.util;

import java.util.Arrays;

public class MathUtil {

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

}
