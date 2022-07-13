package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

import static leetcode.util.TestUtil.transferToString;

/**
 * @author Wit
 */
public class DisplayTableOfFoodOrdersInARestaurant {
    /*
    Given the array orders, which represents the orders that customers have done in a restaurant. More specifically orders[i]=[customerNamei,tableNumberi,foodItemi] where customerNamei is the name of the customer, tableNumberi is the table customer sit at, and foodItemi is the item customer orders.
    Return the restaurant's “display table”. The “display table” is a table whose row entries denote how many of each food item each table ordered. The first column is the table number and the remaining columns correspond to each food item in alphabetical order. The first row should be a header whose first column is “Table”, followed by the names of the food items. Note that the customer names are not part of the table. Additionally, the rows should be sorted in numerically increasing order.

    Example 1:
    Input: orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
    Output: [["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
    Explanation:
    The displaying table looks like:
    Table,Beef Burrito,Ceviche,Fried Chicken,Water
    3    ,0           ,2      ,1            ,0
    5    ,0           ,1      ,0            ,1
    10   ,1           ,0      ,0            ,0
    For the table 3: David orders "Ceviche" and "Fried Chicken", and Rous orders "Ceviche".
    For the table 5: Carla orders "Water" and "Ceviche".
    For the table 10: Corina orders "Beef Burrito".

    Example 2:
    Input: orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
    Output: [["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
    Explanation:
    For the table 1: Adam and Brianna order "Canadian Waffles".
    For the table 12: James, Ratesh and Amadeus order "Fried Chicken".

    Example 3:
    Input: orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
    Output: [["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]

    Constraints:
    1 <= orders.length <= 5 * 10^4
    orders[i].length == 3
    1 <= customerNamei.length, foodItemi.length <= 20
    customerNamei and foodItemi consist of lowercase and uppercase English letters and the space character.
    tableNumberi is a valid integer between 1 and 500.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {
                        transferToString("[[\"Table\",\"Beef Burrito\",\"Ceviche\",\"Fried Chicken\",\"Water\"],[\"3\",\"0\",\"2\",\"1\",\"0\"],[\"5\",\"0\",\"1\",\"0\",\"1\"],[\"10\",\"1\",\"0\",\"0\",\"0\"]]"),
                        transferToString("[[\"David\",\"3\",\"Ceviche\"],[\"Corina\",\"10\",\"Beef Burrito\"],[\"David\",\"3\",\"Fried Chicken\"],[\"Carla\",\"5\",\"Water\"],[\"Carla\",\"5\",\"Ceviche\"],[\"Rous\",\"3\",\"Ceviche\"]]")
                },
                {
                        transferToString("[[\"Table\",\"Canadian Waffles\",\"Fried Chicken\"],[\"1\",\"2\",\"0\"],[\"12\",\"0\",\"3\"]]"),
                        transferToString("[[\"James\",\"12\",\"Fried Chicken\"],[\"Ratesh\",\"12\",\"Fried Chicken\"],[\"Amadeus\",\"12\",\"Fried Chicken\"],[\"Adam\",\"1\",\"Canadian Waffles\"],[\"Brianna\",\"1\",\"Canadian Waffles\"]]")
                },
                {
                        transferToString("[[\"Table\",\"Bean Burrito\",\"Beef Burrito\",\"Soda\"],[\"2\",\"1\",\"1\",\"1\"]]"),
                        transferToString("[[\"Laura\",\"2\",\"Bean Burrito\"],[\"Jhon\",\"2\",\"Beef Burrito\"],[\"Melissa\",\"2\",\"Soda\"]]")
                }
        });
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> result = new ArrayList<>();
        TreeSet<String> foods = new TreeSet<>();
        TreeMap<Integer, Map<String, Integer>> tableMap = new TreeMap<>();
        orders.forEach(order -> {
            foods.add(order.get(2));
            tableMap.computeIfAbsent(Integer.parseInt(order.get(1)), k -> new HashMap<>()).merge(order.get(2), 1, Integer::sum);
        });
        result.add(new ArrayList<>(foods.size() + 1) {{
            add("Table");
            addAll(foods);
        }});
        tableMap.forEach((key, value) -> {
            List<String> list = new ArrayList<>(foods.size() + 1) {{add(key.toString());}};
            foods.forEach(food -> list.add(value.getOrDefault(food, 0).toString()));
            result.add(list);
        });
        return result;
    }


}
