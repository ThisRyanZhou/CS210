// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
// none of this works if i change the name to have my last name before the bank account
class BankAccount
{
	private final String ownerLastName;
	private final String ownerFirstName;
	private final String accountNumber;
	private double checkingBalance;
	private double savingsBalance;
	private static int bankAccountCount = 0;
	
	BankAccount(String ownerLastName, String ownerFirstName, double checkingBalance, double savingsBalance)
	{
		this.ownerLastName = ownerLastName;
		this.ownerFirstName = ownerFirstName;
		this.accountNumber = makeAccountNumber();
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
		
		bankAccountCount = bankAccountCount+1;
	}
	
	BankAccount(String ownerLastName, String ownerFirstName)
	{
		this(ownerLastName, ownerFirstName, 0.0, 0.0);
	}
		
	public boolean accountTransaction(TransactionType transactionType, double amount)
	{
		if( transactionType == TransactionType.DEPOSIT_TO_SAVINGS )
		{
			savingsBalance = savingsBalance + amount;
			return true;
		}
		
		if( transactionType == TransactionType.WITHDRAW_FROM_SAVINGS )
		{
			boolean result = false;
			if( savingsBalance >= amount )
			{
				savingsBalance = savingsBalance - amount;
				result = true;
			}
			return result;
		}
		
		if (transactionType == TransactionType.DEPOSIT_TO_CHECKING){
			checkingBalance = checkingBalance + amount;
			return true;
		}

		if (transactionType == TransactionType.WITHDRAW_FROM_CHECKING){
			boolean result = false;
			if(checkingBalance >= amount){
				checkingBalance = checkingBalance - amount;
				result = true;
			}
			return result;
		}

		if (transactionType == TransactionType.TRANSFER_TO_CHECKING){
			boolean result = false;
			if(savingsBalance >= amount){
				savingsBalance = savingsBalance - amount;
				checkingBalance = checkingBalance + amount;
				result = true;
			}
			return result;
		}

		if (transactionType == TransactionType.TRANSFER_TO_SAVINGS){
			boolean result = false;
			if(checkingBalance >= amount){
				checkingBalance = checkingBalance - amount;
				savingsBalance = savingsBalance + amount;
				result = true;
			}
			return result;
		}

		return true;
	}
		
	public String toString()
	{
		return(getOwnerLastName() + ", " + getOwnerFirstName() + ", " + getAccountNumber() + "\n" + getCheckingBalance() + "\n" + getSavingsBalance());
		// return("");
	}
	
	public String getOwnerLastName()
	{
		return ownerLastName;
	}
	
	public String getOwnerFirstName()
	{
		return ownerFirstName;
	}
	
	public String getAccountNumber()
	{
		return accountNumber;
	}
	
	public double getCheckingBalance()
	{
		return checkingBalance;
	}
	
	public double getSavingsBalance()
	{
		return savingsBalance;
	}
	
	private String makeAccountNumber()
	{
		String aNumber = "" + bankAccountCount;
		
		while( aNumber.length() < 6 )
		{
			aNumber = "0" + aNumber;
		}
		return aNumber;
	}
	
	public static int getBankAccountCount()
	{
		return bankAccountCount;
	}
}
