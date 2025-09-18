class EnhancedBankAccount extends BankAccount
{
	private final java.util.ArrayList<Transaction> successfulTransactions;
	private final java.util.ArrayList<Transaction> failedTransactions;
	
	EnhancedBankAccount(String ownerLastName, String ownerFirstName, double checkingBalance, double savingsBalance)
	{
		super(ownerLastName, ownerFirstName, checkingBalance, savingsBalance);
		
		successfulTransactions = new java.util.ArrayList<>();
		failedTransactions = new java.util.ArrayList<>();
		
		java.util.Date date = new java.util.Date();
		successfulTransactions.add(new Transaction(TransactionType.NEW_ACCOUNT, date, 0.0));
		if( checkingBalance > 0.0 )
		{
			successfulTransactions.add(new Transaction(TransactionType.DEPOSIT_TO_CHECKING, date, checkingBalance));
		}
		
		if( savingsBalance > 0.0 )
		{
			successfulTransactions.add(new Transaction(TransactionType.DEPOSIT_TO_SAVINGS, date, savingsBalance));
		}
	}
	
	EnhancedBankAccount(String ownerLastName, String ownerFirstName)
	{
		this(ownerLastName, ownerFirstName, 0.0, 0.0);
	}
	
	public boolean accountTransaction(TransactionType transactionType, double amount)
	{
		boolean returnValue = false;
		java.util.Date date = new java.util.Date(); 
		if( super.accountTransaction(transactionType, amount) )
		{
			successfulTransactions.add(new Transaction(transactionType, date, amount));
			returnValue = true;
		}
		else
		{
			failedTransactions.add(new Transaction(transactionType, date, amount));
		}
		return returnValue;
	}
		
	public java.util.ArrayList<Transaction> getTransactions(TransactionListType transactionListType, TransactionType transactionType)
	{
		if( transactionListType == TransactionListType.ALL_SUCCESSFULL )
		{
			return successfulTransactions;
		}
		
		if( transactionListType == TransactionListType.ALL_FAILED )
		{
			return failedTransactions;
		}
		
		java.util.ArrayList<Transaction> results = new java.util.ArrayList<>();
		
		if( transactionListType == TransactionListType.SOME_SUCCESSFULL )
		{
			for(Transaction a : successfulTransactions){
				if (a.type().equals(transactionType)){
					results.add(a);
				}
			}
		}
		
		if( transactionListType == TransactionListType.SOME_FAILED )
		{
			for(Transaction a : failedTransactions){
				if (a.type().equals(transactionType)){
					results.add(a);
				}
			}
		}
		return results;
	}
	
	public java.util.ArrayList<Transaction> getTransactions(TransactionListType transactionListType, TransactionType transactionType, java.util.Date startDate, java.util.Date endDate)
	{
		java.util.ArrayList<Transaction> results = new java.util.ArrayList<>();
				
		if( transactionListType == TransactionListType.ALL_SUCCESSFULL )
		{
			
		}
		
		if( transactionListType == TransactionListType.SOME_SUCCESSFULL )
		{
		}
		
		if( transactionListType == TransactionListType.ALL_FAILED )
		{
		}
		
		if( transactionListType == TransactionListType.SOME_FAILED )
		{
		}
		return results;
	}	
}
