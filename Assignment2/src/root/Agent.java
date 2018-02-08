package root;

import java.util.Collections;
import java.util.List;

public class Agent implements Runnable {

	private List<String> ingredients;
	private Table table;
	
	
	public Agent(List<String> ingredients, Table table)
	{
		this.ingredients = ingredients;
		this.table = table;
	}
	
	@Override
	public void run() {
		while(true)
		{
			synchronized(this.table)
			{
				System.out.println("The Agent thread is checking if the table is full.");
				while(this.table.isFull())
				{
					try {
						System.out.println("Table is full. Blocking the agent thread");
						this.table.wait();
						System.out.println("Agent thread is unblocked. Checking if the table is full again.\n");
					} catch(InterruptedException e)
					{
						return;
					}
				}
				
				System.out.println("Table is not full. Adding ingredients.");
				Collections.shuffle(this.ingredients);
				table.addIngredients(this.ingredients.get(0));
				table.addIngredients(this.ingredients.get(1));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Notify All Threads.\n");
				this.table.notifyAll();
			}
		}
	}	

}
