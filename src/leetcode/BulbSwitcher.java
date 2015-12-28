/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2015年12月28日;
//-------------------------------------------------------
public class BulbSwitcher {
    /*
    There are n bulbs that are initially off. You first turn on all the bulbs. 
    Then, you turn off every second bulb. 
    On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). 
    For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
    
    Example:
    
    Given n = 3. 
    
    At first, the three bulbs are [off, off, off].
    After first round, the three bulbs are [on, on, on].
    After second round, the three bulbs are [on, off, on].
    After third round, the three bulbs are [on, off, off]. 
    
    So you should return 1, because there is only one bulb is on.
    */

    //我们知道，每当灯泡会改变状态，也就是 toggle 时，是因为它出现在了某个数的整数倍上。
    //对于第1个灯泡：1*1，会改变1次状态，即 off -> on
    //对于第2个灯泡：1*2，2*1，会改变2次状态，即 off -> on -> off
    //对于第3个灯泡：1*3，3*1，会改变2次状态，即 off -> on -> off
    //对于第4个灯泡：1*4，2*2，4*1，会改变3次状态，即 off -> on -> off -> on
    //会发现，每当我找到一个数的整数倍，总会找到对称的一个整数倍，例如 1*2，就肯定会有一个 2*1。
    //唯一的例外出现在平方数上，例如 4 = 2*2，只有一次整数倍。
    //每次作为偶数次整数倍，最终的灯泡都会还原为 off；只有作为奇数次整数倍，最终的灯泡都会 on。
    //也就是说，最终亮的灯泡数目为小于n的平方数的个数。

    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

}
