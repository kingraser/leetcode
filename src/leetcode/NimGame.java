/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class NimGame {

    /*
    You are playing the following Nim Game with your friend: 
    There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. 
    The one who removes the last stone will be the winner. You will take the first turn to remove the stones.    
    Both of you are very clever and have optimal strategies for the game. 
    Write a function to determine whether you can win the game given the number of stones in the heap.    
    For example, if there are 4 stones in the heap, then you will never win the game: 
    no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend. 
    */
    
    /*
    dp题
    初始:显然1,2,3时先手赢,4时后手赢
    5,6,7时,可进入4的后手赢case
    8则只能进入5,6,7的先手赢case.
    如此往复可以推广至无穷
    引理:游戏若能move 1...n个,则n的倍数为后手胜case
    */

    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

}
