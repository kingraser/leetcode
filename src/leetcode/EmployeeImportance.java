package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class EmployeeImportance {

  /*
  You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.  
  For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. 
  They have importance value 15, 10 and 5, respectively. 
  Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. 
  Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.  
  Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.
  
  Example 1:  
  Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
  Output: 11
  Explanation:
  Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
  
  Note:  
    One employee has at most one direct leader and may have several subordinates.
    The maximum number of employees won't exceed 2000. 
  */

    @Test
    public void test() {
        assertEquals(11, getImportance(Arrays.asList(new Employee(1, 5, Arrays.asList(2, 3)),
                new Employee(2, 3, new ArrayList<>()), new Employee(3, 3, new ArrayList<>())), 1));
    }

    public int getImportance(List<Employee> employees, int id) {
        return getImportance(employees.stream().collect(Collectors.toMap(e -> e.id, e -> e)), id);
    }

    private int getImportance(Map<Integer, Employee> map, int rootId) {
        Employee root = map.get(rootId);
        return root.importance + root.subordinates.stream().mapToInt(e -> getImportance(map, e)).sum();
    }

    class Employee {
        public int id, importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    }
}
