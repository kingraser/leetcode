/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class NQueensII {

  public int totalNQueens(int n) {
    int sum = 0;
    List<Integer> queenList = new ArrayList<>();
    for (int i = 0; i != -1;) {//-1表示尝试过1-9，结束 
      queenList.add(i);
      if (queenList.size() == n) {//找到一个解
        sum++;//解记录
        queenList.remove(n - 1);//去掉刚刚加入皇后,开始回溯
        i = back(n, queenList);//如果可以回溯则回溯
      } else i = getNext(0, n, queenList);//找到下一行的皇后列位置
    }
    return sum;
  }

  /**
   * @param start
   *          起始位置
   * @param n
   *          棋盘大小
   * @param queenList
   *          当前部分结果集
   * @return 下一皇后位置
   */
  private int getNext(int start, int n, List<Integer> queenList) {
    for (int i = start; i < n; i++)
      if (isOk(i, queenList)) return i;//当前位置与前面皇后都不矛盾,返回当前位置
    return back(n, queenList);//矛盾，回溯
  }

  /**
   * @param n
   *          棋盘大小
   * @param queenList
   *          当前部分结果集
   * @return 回溯之后需要尝试的列数
   */
  private int back(int n, List<Integer> queenList) {
    int next = -1;
    while (next == -1 && queenList.size() > 0)
      next = getNext(1 + queenList.remove(queenList.size() - 1), n, queenList);
    return next;//返回
  }

  /**
   * @param col
   *          列
   * @param queenList
   *          之前的结果集（行数即结果集size）
   * @return 该列数是否矛盾
   */
  public boolean isOk(int col, List<Integer> queenList) {
    for (int i = 0; i < queenList.size(); i++)
      if (queenList.get(i) == col || Math.abs(i - queenList.size()) == Math.abs(queenList.get(i) - col)) return false;//列相同且在同一斜线上,矛盾
    return true;
  }

  @Test
  public void test() {
    assertEquals(92, totalNQueens(8));
  }

}
