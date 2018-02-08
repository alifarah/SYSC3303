package root;

import java.util.*;

public class Table {

	private List<String> ingredients;
	private static final int maxIngredients = 2;
	
	public Table()
	{
		this.ingredients = new ArrayList<>();
	}
	
	public void addIngredients(String ingredient)
	{
		System.out.println("Adding "+ ingredient);
		this.ingredients.add(ingredient);
	}
	
	public void consumeIngredients()
	{
		this.ingredients.clear();
	}
	
	public boolean isFull()
	{
		return this.ingredients.size() == maxIngredients;
	}
	
	public boolean hasAllIngredients(String ingredients)
	{
		return !this.ingredients.contains(ingredients) && this.isFull();
	}
}
