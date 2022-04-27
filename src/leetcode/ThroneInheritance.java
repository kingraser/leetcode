package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author Wit
 */
public class ThroneInheritance {
    /*
    A kingdom consists of a king, his children, his grandchildren, and so on. Every once in a while, someone in the family dies or a child is born.
    The kingdom has a well-defined order of inheritance that consists of the king as the first member. Let's define the recursive function Successor(x, curOrder), which given a person x and the inheritance order so far, returns who should be the next person after x in the order of inheritance.
    Successor(x, curOrder):
        if x has no children or all of x's children are in curOrder:
            if x is the king return null
            else return Successor(x's parent, curOrder)
        else return x's oldest child who's not in curOrder
    For example, assume we have a kingdom that consists of the king, his children Alice and Bob (Alice is older than Bob), and finally Alice's son Jack.
    In the beginning, curOrder will be ["king"].
    Calling Successor(king, curOrder) will return Alice, so we append to curOrder to get ["king", "Alice"].
    Calling Successor(Alice, curOrder) will return Jack, so we append to curOrder to get ["king", "Alice", "Jack"].
    Calling Successor(Jack, curOrder) will return Bob, so we append to curOrder to get ["king", "Alice", "Jack", "Bob"].
    Calling Successor(Bob, curOrder) will return null. Thus the order of inheritance will be ["king", "Alice", "Jack", "Bob"].
    Using the above function, we can always obtain a unique order of inheritance.
    Implement the ThroneInheritance class:
    ThroneInheritance(string kingName) Initializes an object of the ThroneInheritance class. The name of the king is given as part of the constructor.
    void birth(string parentName, string childName) Indicates that parentName gave birth to childName.
    void death(string name) Indicates the death of name. The death of the person doesn't affect the Successor function nor the current inheritance order. You can treat it as just marking the person as dead.
    string[] getInheritanceOrder() Returns a list representing the current order of inheritance excluding dead people.

    Example 1:
    Input
    ["ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"]
    [["king"], ["king", "andy"], ["king", "bob"], ["king", "catherine"], ["andy", "matthew"], ["bob", "alex"], ["bob", "asha"], [null], ["bob"], [null]]
    Output
    [null, null, null, null, null, null, null, ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"], null, ["king", "andy", "matthew", "alex", "asha", "catherine"]]
    Explanation
    ThroneInheritance t= new ThroneInheritance("king"); // order: king
    t.birth("king", "andy"); // order: king > andy
    t.birth("king", "bob"); // order: king > andy > bob
    t.birth("king", "catherine"); // order: king > andy > bob > catherine
    t.birth("andy", "matthew"); // order: king > andy > matthew > bob > catherine
    t.birth("bob", "alex"); // order: king > andy > matthew > bob > alex > catherine
    t.birth("bob", "asha"); // order: king > andy > matthew > bob > alex > asha > catherine
    t.getInheritanceOrder(); // return ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
    t.death("bob"); // order: king > andy > matthew > bob > alex > asha > catherine
    t.getInheritanceOrder(); // return ["king", "andy", "matthew", "alex", "asha", "catherine"]

    Constraints:
    1 <= kingName.length, parentName.length, childName.length, name.length <= 15
    kingName, parentName, childName, and name consist of lowercase English letters only.
    All arguments childName and kingName are distinct.
    All name arguments of death will be passed to either the constructor or as childName to birth first.
    For each call to birth(parentName, childName), it is guaranteed that parentName is alive.
    At most 10^5 calls will be made to birth and death.
    At most 10 calls will be made to getInheritanceOrder.
    */

    public static class TestClass {
        @Test
        public void test() {
            TestUtil.testEquals(new ThroneInheritance("king"),
                    new String[]{"birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"},
                    new Object[][]{
                            {null, "king", "andy"},
                            {null, "king", "bob"},
                            {null, "king", "catherine"},
                            {null, "andy", "matthew"},
                            {null, "bob", "alex"},
                            {null, "bob", "asha"},
                            {List.of("king", "andy", "matthew", "bob", "alex", "asha", "catherine")},
                            {null, "bob"},
                            {List.of("king", "andy", "matthew", "alex", "asha", "catherine")}
                    });
        }
    }

    String root;
    HashMap<String, List<String>> familyTree = new HashMap<>();
    HashSet<String> deadList = new HashSet<>();

    public ThroneInheritance(String kingName) {root = kingName;}

    public void birth(String parentName, String childName) {familyTree.computeIfAbsent(parentName, k -> new LinkedList<>()).add(childName);}

    public void death(String name) {deadList.add(name);}

    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(order, root);
        return order;
    }

    void dfs(List<String> order, String root) {
        if (!deadList.contains(root)) order.add(root);
        List<String> list = familyTree.get(root);
        if (list != null) for (String child : list) dfs(order, child);
    }

}
