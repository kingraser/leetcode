package leetcode.util;

import java.util.Objects;

public class ArrayUtil {

  public static <T> void swap(T[] A, int i, int j) {
    if (i == j) return;
    T temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }

  public static void swap(int[] A, int i, int j) {
    if (i == j) return;
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }

  public static void swap(char[] A, int i, int j) {
    if (i == j) return;
    char temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }

  public static <T> void reverse(T[] array) {
    reverse(array, 0, array.length - 1);
  }

  public static void reverse(int[] array) {
    reverse(array, 0, array.length - 1);
  }

  public static void reverse(char[] array) {
    reverse(array, 0, array.length - 1);
  }

  public static <T> void reverse(T[] array, int left, int right) {
    rangeCheck(array, left, right);
    for (; left < right; swap(array, left++, right--));
  }

  public static void reverse(int[] array, int left, int right) {
    rangeCheck(array, left, right);
    for (; left < right; swap(array, left++, right--));
  }

  public static void reverse(char[] array, int left, int right) {
    rangeCheck(array, left, right);
    for (; left < right; swap(array, left++, right--));
  }

  private static <T> void rangeCheck(T[] A, int start, int end) {
    if (start > end) throw new IllegalArgumentException();
    if (start < 0) throw new ArrayIndexOutOfBoundsException(start);
    if (end > A.length - 1) throw new ArrayIndexOutOfBoundsException(end);
  }

  private static void rangeCheck(int[] A, int start, int end) {
    if (start > end) throw new IllegalArgumentException();
    if (start < 0) throw new ArrayIndexOutOfBoundsException(start);
    if (end > A.length - 1) throw new ArrayIndexOutOfBoundsException(end);
  }

  private static void rangeCheck(char[] A, int start, int end) {
    if (start > end) throw new IllegalArgumentException();
    if (start < 0) throw new ArrayIndexOutOfBoundsException(start);
    if (end > A.length - 1) throw new ArrayIndexOutOfBoundsException(end);
  }

  public static <T> int findFirst(T[] A, int start, int end, T target) {
    rangeCheck(A, start, end);
    for (int i = start; i <= end; i++)
      if (Objects.equals(target, A[i])) return i;
    return -1;
  }

  public static int findFirst(int[] A, int start, int end, int target) {
    rangeCheck(A, start, end);
    for (int i = start; i <= end; i++)
      if (target == A[i]) return i;
    return -1;
  }

  public static int findFirst(char[] A, int start, int end, char target) {
    rangeCheck(A, start, end);
    for (int i = start; i <= end; i++)
      if (target == A[i]) return i;
    return -1;
  }

  public static <T> int findFirstNot(T[] A, int start, int end, T target) {
    rangeCheck(A, start, end);
    for (int i = start; i <= end; i++)
      if (!Objects.equals(target, A[i])) return i;
    return -1;
  }

  public static int findFirstNot(int[] A, int start, int end, int target) {
    rangeCheck(A, start, end);
    for (int i = start; i <= end; i++)
      if (target != A[i]) return i;
    return -1;
  }

  public static int findFirstNot(char[] A, int start, int end, char target) {
    rangeCheck(A, start, end);
    for (int i = start; i <= end; i++)
      if (target != A[i]) return i;
    return -1;
  }

  public static <T> int findFirst(T[] A, T target) {
    return findFirst(A, 0, A.length - 1, target);
  }

  public static int findFirst(int[] A, int target) {
    return findFirst(A, 0, A.length - 1, target);
  }

  public static int findFirst(char[] A, char target) {
    return findFirst(A, 0, A.length - 1, target);
  }

  public static <T> int findFirstNot(T[] A, T target) {
    return findFirstNot(A, 0, A.length - 1, target);
  }

  public static int findFirstNot(int[] A, int target) {
    return findFirstNot(A, 0, A.length - 1, target);

  }

  public static int findFirstNot(char[] A, char target) {
    return findFirstNot(A, 0, A.length - 1, target);
  }

  public static <T> int findLast(T[] A, int start, int end, T target) {
    rangeCheck(A, start, end);
    for (int i = end; i >= start; i--)
      if (Objects.equals(target, A[i])) return i;
    return -1;
  }

  public static int findLast(int[] A, int start, int end, int target) {
    rangeCheck(A, start, end);
    for (int i = end; i >= start; i--)
      if (target == A[i]) return i;
    return -1;
  }

  public static int findLast(char[] A, int start, int end, char target) {
    rangeCheck(A, start, end);
    for (int i = end; i >= start; i--)
      if (target == A[i]) return i;
    return -1;
  }

  public static <T> int findLastNot(T[] A, int start, int end, T target) {
    rangeCheck(A, start, end);
    for (int i = end; i >= start; i--)
      if (!Objects.equals(target, A[i])) return i;
    return -1;
  }

  public static int findLastNot(int[] A, int start, int end, int target) {
    rangeCheck(A, start, end);
    for (int i = end; i >= start; i--)
      if (target != A[i]) return i;
    return -1;
  }

  public static int findLastNot(char[] A, int start, int end, char target) {
    rangeCheck(A, start, end);
    for (int i = end; i >= start; i--)
      if (target != A[i]) return i;
    return -1;
  }

  public static <T> int findLast(T[] A, T target) {
    return findLast(A, 0, A.length - 1, target);
  }

  public static int findLast(int[] A, int target) {
    return findLast(A, 0, A.length - 1, target);
  }

  public static int findLast(char[] A, char target) {
    return findLast(A, 0, A.length - 1, target);
  }

  public static <T> int findLastNot(T[] A, T target) {
    return findLastNot(A, 0, A.length - 1, target);
  }

  public static int findLastNot(int[] A, int target) {
    return findLastNot(A, 0, A.length - 1, target);

  }

  public static int findLastNot(char[] A, char target) {
    return findLastNot(A, 0, A.length - 1, target);
  }

}
