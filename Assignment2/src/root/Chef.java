package root;

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
		while(true)
		{
			synchronized(this.table)
			{
				System.out.println(this.ingredient +" thread is checking if the table has all the ingredients.");
				while(!table.hasAllIngredients(this.ingredient))
				{
					try {
						System.out.println("Table is missing ingredients. Blocking " + this.ingredient + " thread.\n");
						this.table.wait();
						System.out.println(this.ingredient +" thread is unblocked. Checking if the table has all the ingredients.");

					} catch(InterruptedException e)
					{
						return;
					}
				}
				
				System.out.println("Table has all ingredients, " + this.ingredient + " thread is consuming all the ingredients.");
				table.consumeIngredients();
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
