package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class ShoppingOffers {
    /*
    In LeetCode Store, there are n items to sell. Each item has a price. However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.
    You are given an integer array price where price[i] is the price of the ith item, and an integer array needs where needs[i] is the number of pieces of the ith item you want to buy.
    You are also given an array special where special[i] is of size n + 1 where special[i][j] is the number of pieces of the jth item in the ith offer and special[i][n] (i.e., the last integer in the array) is the price of the ith offer.
    Return the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers. You are not allowed to buy more items than you want, even if that would lower the overall price. You could use any of the special offers as many times as you want.

    Example 1:
    Input: price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
    Output: 14
    Explanation: There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
    In special offer 1, you can pay $5 for 3A and 0B
    In special offer 2, you can pay $10 for 1A and 2B.
    You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.

    Example 2:
    Input: price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
    Output: 11
    Explanation: The price of A is $2, and $3 for B, $4 for C.
    You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C.
    You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C.
    You cannot add more items, though only $9 for 2A ,2B and 1C.

    Constraints:
    n == price.length
    n == needs.length
    1 <= n <= 6
    0 <= price[i] <= 10
    0 <= needs[i] <= 10
    1 <= special.length <= 100
    special[i].length == n + 1
    0 <= special[i][j] <= 50
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {14, List.of(2, 5), List.of(List.of(3, 0, 5), List.of(1, 2, 10)), IntStream.of(3, 2).boxed().collect(Collectors.toList())},
                {11, List.of(2, 3, 4), List.of(List.of(1, 1, 0, 4), List.of(2, 2, 1, 9)), IntStream.of(1, 2, 1).boxed().collect(Collectors.toList())},
        });
    }

    int result;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        dfs(special.stream().mapToInt(offer -> getMoney(price, offer, needs) - offer.get(offer.size() - 1)).toArray(), special, needs, 0, result = getMoney(price, needs, needs));
        return result;
    }

    void dfs(int[] moneySavings, List<List<Integer>> special, List<Integer> needs, int index, int current) {
        if (index == special.size()) {
            result = Integer.min(result, current);
            return;
        }
        dfs(moneySavings, special, needs, index + 1, current);
        if (moneySavings[index] <= 0) return;
        int maxOffers = getMaxShoppingOffers(special.get(index), needs);
        if (maxOffers < 1) return;
        for (int i = 0; i < maxOffers; i++) {
            buy(special.get(index), needs, 1);
            dfs(moneySavings, special, needs, index + 1, current -= moneySavings[index]);
        }
        buy(special.get(index), needs, -maxOffers);
    }

    int getMaxShoppingOffers(List<Integer> shoppingOffer, List<Integer> needs) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < needs.size(); i++)
            if (needs.get(i) < shoppingOffer.get(i)) return 0;
            else if (shoppingOffer.get(i) == 0) continue;
            else result = Integer.min(result, needs.get(i) / shoppingOffer.get(i));
        return result;
    }

    void buy(List<Integer> special, List<Integer> needs, int count) {
        for (int i = 0; i < needs.size(); i++) needs.set(i, needs.get(i) - special.get(i) * count);
    }

    int getMoney(List<Integer> price, List<Integer> offer, List<Integer> needs) {
        int res = 0, priceSize = price.size();
        for (int i = 0; i < priceSize; res += price.get(i) * offer.get(i++)) if (needs.get(i) < offer.get(i)) return 0;
        return res;
    }
}
