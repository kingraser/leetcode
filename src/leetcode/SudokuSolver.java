/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class SudokuSolver {

    static Integer row = 0, col = 0;

    public void solveSudoku(char[][] board) {
        Stack<Integer> history = new Stack<Integer>();
        for (row = 0; row < 9; row++)
            for (col = 0; col < 9; col++)
                if (board[row][col] == '.') put(board, '1', history);
    }

    /**
     * @param board 数独
     * @param start 起始尝试数字
     * @param history 填入的历史记录，两位数字，十位是行数，个位是列数
     */
    private void put(char[][] board, char start, Stack<Integer> history) {
        if (board[row][col] != '.') board[row][col] = '.';
        A: for (char a = start; a < ':'; a++) {//a<='9'
            for (int i = 0; i < 9; i++)
                if (board[row][i] == a || board[i][col] == a
                        || board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == a)
                    continue A;//有重复
            board[row][col] = a;//放置
            history.push(row * 10 + col);//历史记录
            return;
        }
        row = history.peek() / 10;//回溯
        col = history.pop() % 10;
        put(board, ++board[row][col], history);
    }

    @Test
    public void test() {
        char[][] board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } },
                expected = new char[][] { { '5', '3', '4', '6', '7', '8', '9', '1', '2' },
                        { '6', '7', '2', '1', '9', '5', '3', '4', '8' },
                        { '1', '9', '8', '3', '4', '2', '5', '6', '7' },
                        { '8', '5', '9', '7', '6', '1', '4', '2', '3' },
                        { '4', '2', '6', '8', '5', '3', '7', '9', '1' },
                        { '7', '1', '3', '9', '2', '4', '8', '5', '6' },
                        { '9', '6', '1', '5', '3', '7', '2', '8', '4' },
                        { '2', '8', '7', '4', '1', '9', '6', '3', '5' },
                        { '3', '4', '5', '2', '8', '6', '1', '7', '9' } };
        solveSudoku(board);
        Assert.assertTrue(equals(expected, board));
    }

    private boolean equals(char[][] a, char[][] b) {
        try {
            for (int i = 0; i < a.length; i++)
                for (int j = 0; j < a[0].length; j++)
                    if (a[i][j] != b[i][j]) return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
