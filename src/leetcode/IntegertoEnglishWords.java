/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月2日<p>
//-------------------------------------------------------
public class IntegertoEnglishWords {

    /*
            数字转英文
    */

    private final String[] lessThan20 = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" },
            tens = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" },
            thousands = { "", "Thousand", "Million", "Billion" };

    public String numberToWords(int num) {
        if (num < 20) return lessThan20[num];
        String words = "";
        for (int i = 0; num > 0; i++, num /= 1000)
            if (num % 1000 != 0) words = helper(num % 1000) + thousands[i] + " " + words;
        return words.trim();
    }

    private String helper(int num) {
        if (num == 0) return "";
        else if (num < 20) return lessThan20[num];
        else if (num < 100) return tens[num / 10] + " " + helper(num % 10);
        else return lessThan20[num / 100] + " Hundred " + helper(num % 100);
    }

    @Test
    public void test() {
        Assert.assertEquals("One Hundred Twenty Three", numberToWords(123));
        Assert.assertEquals("Twelve Thousand Three Hundred Forty Five", numberToWords(12345));
        Assert.assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven",
                numberToWords(1234567));
    }

}
