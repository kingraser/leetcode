/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月8日<p>
//-------------------------------------------------------
public class FirstBadVersion {
    /*
    假定有1...n n个版本，其中有些版本有问题
    若一个版本有问题，则其后继版本均有问题
    现有api:isBadVersion判断一个版本是否有问题
    最小化api调用次数找到最开始有问题的版本
    
    解法
    显然的二分查找
    logN
     */

    public int firstBadVersion(int n) {
        int l = 1, r = n;
        for (int m = l + (r - l) / 2; l < r; m = l + (r - l) / 2)
            if (isBadVersion(m)) r = m;
            else l = m + 1;
        return l;
    }

    private boolean isBadVersion(int version) {
        return true;
    }

}
