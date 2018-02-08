package root;

/**
 * @author alifarah
 *
 */
public class Chef implements Runnable {

	
	private final String ingredient;
	private Table table;
	
	public Chef(String ingredient, Table table)
	{
		this.ingredient = ingredient;
		this.table = table;
	}
	
	@Override
	public void run() {
		synchronized(this.table)
		{
			while(!this.table.doneMakingSandwiches())
			{
		
				System.out.println(this.ingredient +" thread is checking if the table has all the ingredients.");
				// loop while the table doesn't have all the ingredients needed to make a sandwich for the chef
				while(!table.hasAllIngredients(this.ingredient))
				{
					try {
						System.out.println("Table is missing ingredients. Blocking " + this.ingredient + " thread.\n");
						this.table.wait();
						
						if(this.table.doneMakingSandwiches())
						{
							System.out.println(this.ingredient + " thread is done. Exiting");
							return;
						}
						
						System.out.println(this.ingredient +" thread is unblocked. Checking if the table has all the ingredients.");
	
					} catch(InterruptedException e)
					{
						return;
					}
				}
				
				System.out.println("Table has all ingredients, " + this.ingredient + " thread is consuming all the ingredients.");
				// if the table has all the ingredients make sandwich and eat it 
				table.consumeIngredients();
				try {
					Thread.sleep(1000); // slow things down
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Notify All Threads.\n");
				// alert all threads in wait set
				this.table.notifyAll();
			}
		}
		
		System.out.println(this.ingredient + " thread is done. Exiting");
	}	
	
}
