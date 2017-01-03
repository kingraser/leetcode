package leetcode;

import java.util.TreeMap;

public class MatchstickstoSquare {

  public boolean makesquare(int[] nums) {
    int sum = 0;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int i : nums) {
      sum += i;
      map.compute(i, (k, v) -> v == null ? 1 : v + 1);
    }
    if (sum % 4 != 0 || map.lastKey() > sum / 4) return false;
    for (int i = 4, len = sum / 4; i-- > 0;)
      if (!makeLength(map, len)) return false;
    return true;
  }

  private boolean makeLength(TreeMap<Integer, Integer> map, int len) {
    Integer count = map.get(len);
    if (count != null && count > 1) map.put(len, count - 1);
    return false;
  }

}
