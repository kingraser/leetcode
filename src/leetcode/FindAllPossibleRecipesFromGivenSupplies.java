package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author Wit
 */
public class FindAllPossibleRecipesFromGivenSupplies {
	/*
	You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
	You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
	Return a list of all the recipes that you can create. You may return the answer in any order.
	Note that two recipes may contain each other in their ingredients.

	Example 1:
	Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
	Output: ["bread"]
	Explanation:
	We can create "bread" since we have the ingredients "yeast" and "flour".

	Example 2:
	Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
	Output: ["bread","sandwich"]
	Explanation:
	We can create "bread" since we have the ingredients "yeast" and "flour".
	We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".

	Example 3:
	Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
	Output: ["bread","sandwich","burger"]
	Explanation:
	We can create "bread" since we have the ingredients "yeast" and "flour".
	We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
	We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".

	Constraints:
	n == recipes.length == ingredients.length
	1 <= n <= 100
	1 <= ingredients[i].length, supplies.length <= 100
	1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
	recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
	All the values of recipes and supplies combined are unique.
	Each `ingredients[i]` does not contain any duplicate values.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{List.of("bread"), new String[]{"bread"}, TestUtil.transferToString("[[\"yeast\",\"flour\"]]"), new String[]{"yeast", "flour", "corn"}},
				{List.of("bread", "sandwich"), new String[]{"bread", "sandwich"}, TestUtil.transferToString("[[\"yeast\",\"flour\"],[\"bread\",\"meat\"]]"), new String[]{"yeast", "flour", "meat"}},
				{List.of("bread", "sandwich", "burger"), new String[]{"bread", "sandwich", "burger"}, TestUtil.transferToString("[[\"yeast\",\"flour\"],[\"bread\",\"meat\"],[\"sandwich\",\"meat\",\"bread\"]]"), new String[]{"yeast", "flour", "meat"}},
		});
	}

	public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
		Map<String, Set<String>> ingredientToRecipes = new HashMap<>();
		Map<String, Integer> inDegree = new HashMap<>();
		for (int i = 0; i < recipes.length; i++) {
			for (String ing : ingredients.get(i))
				ingredientToRecipes.computeIfAbsent(ing, s -> new HashSet<>()).add(recipes[i]);
			inDegree.put(recipes[i], ingredients.get(i).size());
		}
		// Topological Sort.
		List<String> ans = new ArrayList<>(recipes.length);
		Queue<String> available = new ArrayDeque<>(supplies.length + recipes.length);
		for (String supply : supplies) available.add(supply);
		while (!available.isEmpty()) {
			Set<String> recipeSet = ingredientToRecipes.remove(available.poll());
			if (recipeSet == null) continue;
			for (String recipe : recipeSet) {
				if (inDegree.merge(recipe, -1, Integer::sum) > 0) continue;
				available.offer(recipe);
				ans.add(recipe);
			}
		}
		return ans;
	}
}
