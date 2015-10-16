/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

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

    //广搜。从上下左右四个边界往里走,凡是能碰到的'O',都是跟边界接壤的,应该保留。

    public void solve(char[][] board) {
        if (board.length < 2) return;
        boolean[][] reached = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i += board.length - 1)//从上下边搜
            for (int j = 0; j < board[i].length; j++)
                if (board[i][j] == 'O') bfs(board, i, j, reached);
        for (int j = 0; j < board[0].length; j += board[0].length - 1)//从左右边搜
            for (int i = 1; i < board.length - 1; i++)
                if (board[i][j] == 'O') bfs(board, i, j, reached);
        for (int i = 1; i < board.length - 1; i++)//change 0 to X
            for (int j = 1; j < board[i].length - 1; j++)
                if (board[i][j] == 'O' && !reached[i][j]) board[i][j] = 'X';
    }

    private void bfs(char[][] board, int i, int j, boolean[][] reached) {
        if (check(board, i, j, reached)) reached[i][j] = true;
        if (check(board, i + 1, j, reached)) bfs(board, i + 1, j, reached);
        if (check(board, i - 1, j, reached)) bfs(board, i - 1, j, reached);
        if (check(board, i, j + 1, reached)) bfs(board, i, j + 1, reached);
        if (check(board, i, j - 1, reached)) bfs(board, i, j - 1, reached);
    }

    private boolean check(char[][] board, int i, int j, boolean[][] reached) {
        if (i > 0 && i < board.length - 1 && j > 0 && j < board[0].length - 1 && board[i][j] == 'O' && !reached[i][j])
            return true;
        return false;
    }
}
