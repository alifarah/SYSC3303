package root;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	
	public static void main(String[] args)
	{
		// Create the ingredients
		String bread = "Bread";
		String jam = "Jam";
		String peanutButter = "Peanut Butter";
		List<String> ingredients = new ArrayList<>();
		ingredients.add(bread);
		ingredients.add(jam);
		ingredients.add(peanutButter);
		
		// create threads
		Table table = new Table();
		Thread chef1 = new Thread(new Chef(bread,table));
		Thread chef2 = new Thread(new Chef(jam,table));
		Thread chef3 = new Thread(new Chef(peanutButter,table));
		Thread agent = new Thread(new Agent(ingredients, table));
		
		//start threads
		chef1.start();
		chef2.start();
		chef3.start();
		agent.start();


	}
	
	
}
