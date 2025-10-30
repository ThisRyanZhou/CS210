class MiniBankAccountSemaphore implements MiniBankAccountFunctions
{
	private final java.util.concurrent.Semaphore semaphore;
	private static final int delayTime = 1;
	private final String ownerLastName;
	private final String ownerFirstName;
	private double checkingBalance;
	private double savingsBalance;
	
	MiniBankAccountSemaphore(String ownerLastName, String ownerFirstName, java.util.concurrent.Semaphore semaphore)
	{
		this.semaphore = semaphore;
		this.ownerLastName = ownerLastName;
		this.ownerFirstName = ownerFirstName;
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
	}
	
	public void withdrawFromChecking(double amount)
	{
		try{
			semaphore.acquire();
			System.out.println("start withdrawChecking " + amount + "\t" + ownerFirstName);
			double x = checkingBalance;
			pause(delayTime);
			checkingBalance = x - amount;
			System.out.println("end withdrawChecking " + amount + "\t" + ownerFirstName);
		}
		catch(Exception e){

		}
		finally{
			semaphore.release();
		}
	}
	
	public void withdrawFromSavings(double amount)
	{
		try{
			semaphore.acquire();
			System.out.println("start withdrawSavings " + amount + "\t" + ownerFirstName);
			double x = savingsBalance;
			pause(delayTime);
			savingsBalance = x - amount;
			System.out.println("end withdrawSavings " + amount + "\t" + ownerFirstName);
		}
		catch(Exception e){

		}
		finally{
			semaphore.release();
		}
	}
	
	public void depositToSavings(double amount)
	{
		try{
			semaphore.acquire();
			System.out.println("start depositSavings " + amount + "\t" + ownerFirstName);
			double x = savingsBalance;
			pause(delayTime);
			savingsBalance = x + amount;
			System.out.println("end depositSavings " + amount + "\t" + ownerFirstName);
		}
		catch(Exception e){

		}
		finally{
			semaphore.release();
		}
	}
	
	public void depositToChecking(double amount)
	{
		try{
			semaphore.acquire();
			System.out.println("start depositChecking " + amount + "\t" + ownerFirstName);
			double x = checkingBalance;
			pause(delayTime);
			checkingBalance = x + amount;
			System.out.println("end depositChecking " + amount + "\t" + ownerFirstName);
		}
		catch(Exception e){

		}
		finally{
			semaphore.release();
		}
	}
	
	public void transferFromSavingsToChecking(double amount)
	{
		try{
			semaphore.acquire();
			System.out.println("start transferToChecking " + amount + "\t" + ownerFirstName);
			double x = checkingBalance;
			double y = savingsBalance;
			if( amount > y )
			{
				amount = 0;
			}
			pause(delayTime);
			checkingBalance = x + amount;
			savingsBalance = y - amount;
			System.out.println("end transferToChecking " + amount + "\t" + ownerFirstName);
		}
		catch(Exception e){

		}
		finally{
			semaphore.release();
		}
	}
	
	public void transferFromCheckingToSavings(double amount)
	{
		try{
			semaphore.acquire();
			System.out.println("start transferToSavings " + amount + "\t" + ownerFirstName);
			double x = checkingBalance;
			double y = savingsBalance;
			if( amount > x )
			{
				amount = 0;
			}
			pause(delayTime);
			checkingBalance = x - amount;
			savingsBalance = y + amount;
			System.out.println("end transferToSavings " + amount + "\t" + ownerFirstName);
		}
		catch(Exception e){

		}
		finally{
			semaphore.release();
		}
	}
		
	public String toString()
	{
		String result = "";
		try{
			semaphore.acquire();
			result = ownerLastName + ", " + ownerFirstName + "\n";
			result = result + checkingBalance + "\n";
			result = result + savingsBalance;
			semaphore.release();
			return result;
		}
		catch(Exception e){
			return result;
		}
	}
	
	public String getOwnerLastName()
	{
		return ownerLastName;
	}
	
	public String getOwnerFirstName()
	{
		return ownerFirstName;
	}
		
	private void pause(int delay)
	{
		try
		{
			java.lang.Thread.sleep(delay);
		}
		catch(Exception e)
		{
		}
	}
}
