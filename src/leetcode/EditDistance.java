/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class EditDistance {

    /*
    Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
    (each operation is counted as 1 step.)
    
    You have the following 3 operations permitted on a word:
    
    a) Insert a character
    b) Delete a character
    c) Replace a character
    */

    /*
    分析
    设状态为 f[i][j],表示 A[0,i] 和 B[0,j] 之间的最小编辑距离。设 A[0,i] 的形式是 str1c,
    B[0,j] 的形式是 str2d,
    1. 如果 c==d,则 f[i][j]=f[i-1][j-1];
    2. 如果 c!=d,
    (a) 如果将 c 替换成 d,则 f[i][j]=f[i-1][j-1]+1;
    (b) 如果在 c 后面添加一个 d,则 f[i][j]=f[i][j-1]+1;
    (c) 如果将 c 删除,则 f[i][j]=f[i-1][j]+1;
     */

    public int minDistance(String word1, String word2) {
        int[][] A = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; A[i][0] = i, i++);
        for (int i = 0; i < word2.length() + 1; A[0][i] = i, i++);
        for (int i = 1; i < word1.length() + 1; i++)
            for (int j = 1; j < word2.length() + 1; j++)
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) A[i][j] = A[i - 1][j - 1];
                else A[i][j] = Math.min(Math.min(A[i - 1][j - 1], A[i][j - 1]), A[i - 1][j]) + 1;
        return A[word1.length()][word2.length()];
    }
}