/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class SurroundedRegions {
    /*
    Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.    
    A region is captured by flipping all 'O's into 'X's in that surrounded region.    
    For example,
    X X X X
    X O O X
    X X O X
    X O X X    
    After running your function, the board should be:
    X X X X
    X X X X
    X X X X
    X O X X    
    */

    //深搜。从上下左右四个边界往里走,凡是能碰到的'O',都是跟边界接壤的,应该保留。

    public void solve(char[][] board) {
        boolean[][] reached = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i += board.length - 1)//从上下边搜
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == 'O') dfs(board, i + (i == 0 ? 1 : -1), j, reached);
        for (int j = 0; j < board[0].length; j += board[0].length - 1)//从左右边搜
            for (int i = 1; i < board.length - 1; i++)
                if (board[i][j] == 'O') dfs(board, i, j + (j == 0 ? 1 : -1), reached);
        for (int i = 1; i < board.length - 1; i++)//change 0 to X
            for (int j = 1; j < board[0].length - 1; j++)
                if (board[i][j] == 'O' && !reached[i][j]) board[i][j] = 'X';
    }

    private int[] dx = new int[] { 1, -1, 0, 0 }, dy = new int[] { 0, 0, 1, -1 };

    private void dfs(char[][] A, int i, int j, boolean[][] reached) {
        if (i < 1 || i > A.length - 2 || j < 1 || j > A[0].length - 2 || reached[i][j] || A[i][j] != 'O') return;
        reached[i][j] = true;
        for (int k = 0; k < 4; dfs(A, i + dx[k], j + dy[k++], reached));
    }

    @Test
    public void test() {
        char[][] expected = new char[][] { "XXXX".toCharArray(), "XXXX".toCharArray(), "XXXX".toCharArray(),
                "XOXX".toCharArray() },
                actual = new char[][] { "XXXX".toCharArray(), "XOOX".toCharArray(), "XXOX".toCharArray(),
                        "XOXX".toCharArray() };
        solve(actual);
        Assert.assertArrayEquals(expected, actual);
    }

}
