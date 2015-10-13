/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class GrayCode {
    /*
    The gray code is a binary numeral system where two successive values differ in only one bit.    
    Given a non-negative integer n representing the total number of bits in the code, 
    print the sequence of gray code. A gray code sequence must begin with 0.
    
    For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
    
    00 - 0
    01 - 1
    11 - 3
    10 - 2
    
    Note:
    For a given n, a gray code sequence is not uniquely defined.    
    For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
    For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
    */

    /*
    格雷码 (Gray Code) 的定义请参考 http://en.wikipedia.org/wiki/Gray_code
    
    自然二进制码转换为格雷码:g0=b0,gi=bi⊕bi−1
    保留自然二进制码的最高位作为格雷码的最高位,格雷码次高位为二进制码的高位与次高位异或,
    其余各位与次高位的求法类似。
    例如,将自然二进制码1001,转换为格雷码的过程是:保留最高位;
    然后将第1位的1和第2位的0异或,得到1,作为格雷码的第2位;
    将第2位的0和第3位的0异或,得到0,作为格雷码的第3位;
    将第3位的0和第4位的1异或,得到1,作为格雷码的第4位,最终,格雷码为1101。
    
    格雷码转换为自然二进制码:b0=g0,bi=gi⊕bi−1
    保留格雷码的最高位作为自然二进制码的最高位,次高位为自然二进制高位与格雷码次高位异或,
    其余各位与次高位的求法类似。
    例如,将格雷码1000转换为自然二进制码的过程是:保留最高位1,作为自然二进制码的最高位;
    然后将自然二进制码的第1位1和格雷码的第2位0异或,得到1,作为自然二进制码的第2位;
    将自然二进制码的第2位1和格雷码的第3位0异或,得到1,作为自然二进制码的第3位;
    将自然二进制码的第3位1和格雷码的第4位0异或,得到1,作为自然二进制码的第4位,最终,自然二进制码为1111。
    
    格雷码有数学公式,整数n的格雷码是n⊕(n/2)。
    这题要求生成 n 比特的所有格雷码。
    方法 1,最简单的方法,利用数学公式,对从 0∼ 2^n − 1的所有整数,转化为格雷码。
    方法 2,n 比特的格雷码,可以递归地从 n − 1 比特的格雷码生成。
    */

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList(0, 1, 3, 2), grayCode(2));
        Assert.assertEquals(Lists.newArrayList(0, 1, 3, 2), grayCodeII(2));
    }

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>(1 << n);// 2^n
        for (int i = 0, size = 1 << n; i < size; result.add(getGrayFromBinary(i++)));
        return result;
    }

    private int getGrayFromBinary(int n) {
        return n ^ (n >> 1);
    }

    public List<Integer> grayCodeII(int n) {
        List<Integer> result = new ArrayList<>(1 << n);// 2^n
        result.add(0);
        for (int i = 0; i < n; i++)
            for (int highestBit = 1 << i, j = result.size() - 1; j >= 0; j--) // 要反着遍历,才能对称
                result.add(highestBit | result.get(j));
        return result;
    }
}
