package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CouponCodeValidator {
    /*
    You are given three arrays of length n that describe the properties of n coupons: code, businessLine, and isActive. The ith coupon has:
        code[i]: a string representing the coupon identifier.
        businessLine[i]: a string denoting the business category of the coupon.
        isActive[i]: a boolean indicating whether the coupon is currently active.
    A coupon is considered valid if all the following conditions hold:
        code[i] is non-empty and consists only of alphanumeric characters (a-z, A-Z, 0-9) and underscores (_).
        businessLine[i] is one of the following four categories: "electronics", "grocery", "pharmacy", "restaurant".
        isActive[i] is true.
    Return an array of the codes of all valid coupons, sorted first by their businessLine in the order: "electronics", "grocery", "pharmacy", "restaurant",
    and then by code in lexicographical (ascending) order within each category.

    Example 1:
    Input: code = ["SAVE20","","PHARMA5","SAVE@20"], businessLine = ["restaurant","grocery","pharmacy","restaurant"], isActive = [true,true,true,true]
    Output: ["PHARMA5","SAVE20"]
    Explanation:
        First coupon is valid.
        Second coupon has empty code (invalid).
        Third coupon is valid.
        Fourth coupon has special character @ (invalid).

    Example 2:
    Input: code = ["GROCERY15","ELECTRONICS_50","DISCOUNT10"], businessLine = ["grocery","electronics","invalid"], isActive = [false,true,true]
    Output: ["ELECTRONICS_50"]
    Explanation:
        First coupon is inactive (invalid).
        Second coupon is valid.
        Third coupon has invalid business line (invalid).

    Constraints:
        n == code.length == businessLine.length == isActive.length
        1 <= n <= 100
        0 <= code[i].length, businessLine[i].length <= 100
        code[i] and businessLine[i] consist of printable ASCII characters.
        isActive[i] is either true or false.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {
                        List.of("1OFw", "0MvB"),
                        new String[]{"1OFw", "0MvB"},
                        new String[]{"electronics", "pharmacy"},
                        new boolean[]{true, true}
                }, {
                        List.of("PHARMA5", "SAVE20"),
                        new String[]{"SAVE20", "", "PHARMA5", "SAVE@20"},
                        new String[]{"restaurant", "grocery", "pharmacy", "restaurant"},
                        new boolean[]{true, true, true, true}
                }, {
                        List.of("ELECTRONICS_50"),
                        new String[]{"GROCERY15", "ELECTRONICS_50", "DISCOUNT10"},
                        new String[]{"grocery", "electronics", "invalid"},
                        new boolean[]{false, true, true}
                },
                });
    }

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<Coupon> result = new ArrayList<>();
        for (int i = 0, length = isActive.length, sort; i < length; i++)
            if (isActive[i] && (sort = getSort(businessLine[i])) > 0 && isValidCode(code[i]))
                result.add(new Coupon(sort, code[i]));
        Collections.sort(result);
        return result.stream().map(coupon -> coupon.code).collect(Collectors.toList());
    }

    private boolean isValidCode(String s) {
        if (s.length() == 0) return false;
        for (byte c : s.getBytes())
            if ((c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && c != '_') return false;
        return true;
    }

    private int getSort(String s) {
        if ("electronics".equals(s)) return 1;
        if ("grocery".equals(s)) return 2;
        if ("pharmacy".equals(s)) return 3;
        if ("restaurant".equals(s)) return 4;
        return -1;
    }

    public static final class Coupon implements Comparable<Coupon> {
        public int businessLine;
        public String code;

        public Coupon(int businessLine, String code) {
            this.businessLine = businessLine;
            this.code = code;
        }

        @Override
        public int compareTo(Coupon o) {
            if (businessLine < o.businessLine) return -1;
            if (businessLine > o.businessLine) return 1;
            return code.compareTo(o.code);
        }
    }
}
