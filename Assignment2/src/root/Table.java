package root;

import java.util.*;

/**
 * @author alifarah
 *
 */
public class Table {

	private List<String> ingredients;
	private static final int maxIngredients = 2;
	private static final int maxSandwiches = 20;
	private int numberOfSandwichesMade;
	
	public Table()
	{
		this.ingredients = new ArrayList<>();
	}
	

	/**
	 * @param ingredient
	 * add an ingredient to the end of the list
	 */
	public void addIngredients(String ingredient)
	{
		System.out.println("Adding "+ ingredient);
		this.ingredients.add(ingredient);
	}
	
	/**
	 * @return whether you are done making sandwiches 
	 */
	public boolean doneMakingSandwiches()
	{
		return this.numberOfSandwichesMade >= maxSandwiches;
	}
	
	/**
	 * removes all ingredients currently on the table 
	 */
	public void consumeIngredients()
	{
		this.ingredients.clear();
		this.numberOfSandwichesMade++;
		System.out.println("Sandwich number " + this.numberOfSandwichesMade + " has been made.");
	}
	
	
	/**
	 * @return whether the table is full or not
	 */
	public boolean isFull()
	{
		return this.ingredients.size() == maxIngredients;
	}
	
	/**
	 * @param ingredients
	 * @return whether if the table has all the ingredients needed to make a sandwich 
	 */
	public boolean hasAllIngredients(String ingredients)
	{
		// table has all the necessary ingredients if it is full and it does not contain the chef's ingredient 
		return !this.ingredients.contains(ingredients) && this.isFull();
	}
}
