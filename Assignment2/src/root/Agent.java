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
		synchronized(this.table)
		{
			while(!this.table.doneMakingSandwiches())
			{
				System.out.println("The Agent thread is checking if the table is full.");
				// loop while the table is full.
				while(this.table.isFull())
				{
					try {
						System.out.println("Table is full. Blocking the agent thread\n");
						this.table.wait();
						
						if(this.table.doneMakingSandwiches())
						{
							System.out.println("Agent thread is done. Exiting");
							return;
						}
						
						System.out.println("Agent thread is unblocked. Checking if the table is full again.");
					} catch(InterruptedException e)
					{
						return;
					}
				}
				
				
				// if the table is empty all two random ingredients 
				System.out.println("Table is not full. Adding ingredients.");
				Collections.shuffle(this.ingredients); // shuffle the ingredients list to randomize the order 
				table.addIngredients(this.ingredients.get(0));
				table.addIngredients(this.ingredients.get(1));
				try {
					Thread.sleep(1000); // slow things down.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Notify All Threads.\n");
				// alert all threads in wait set
				this.table.notifyAll();
			}
		}
		System.out.println("Agent thread is done. Exiting");

	}	

}
