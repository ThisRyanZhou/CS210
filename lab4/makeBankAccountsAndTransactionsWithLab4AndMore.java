class makeBankAccountsAndTransactionsWithLab4AndMore
{
	public static void main(String[] args)
	{
		if( args.length != 2 )
		{
			System.out.println("format: makeBankAccountsAndTransactionsWithLab4AndMore \"input file\" \"output file\"");
			System.exit(0);
		}
		
		boolean excludeNonTransactionListResultsInEnhancedResults = true;
		
		try
		{
			java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(args[0])));
			java.io.PrintWriter output = new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.FileWriter(args[1])));
			String inn;
			java.util.ArrayList<String> inputLines = new java.util.ArrayList<>();
			while( (inn = input.readLine()) != null )
			{
				if( (inn.trim().length() > 0) && (!inn.trim().startsWith("#")) )
				{
					inputLines.add(inn);
				}
			}
			
			for( int i = 0; i < inputLines.size(); )
			{
				inn = inputLines.get(i);
				
				if( inn.startsWith("new BankAccount") )
				{
					System.out.println(inn);
					output.println(inn);
					java.util.StringTokenizer st = new java.util.StringTokenizer(inn, "\t");
					st.nextToken();
					String lastName = st.nextToken();
					String firstName = st.nextToken();
					double checkingInitialValue = Double.parseDouble(st.nextToken());
					double savingsInitialValue = Double.parseDouble(st.nextToken());
					zhou_BankAccount ba = new zhou_BankAccount(lastName, firstName, checkingInitialValue, savingsInitialValue);
					System.out.println(ba.toString());
					output.println(ba.toString());
					i = i+1;
					while( true )
					{
						if( i >= inputLines.size() )
						{
							System.out.println(ba.toString());
							output.println("end BankAccount");
							output.println(ba.toString());
							break;
						}
						
						inn = inputLines.get(i);
						if( inn.startsWith("new") )
						{
							System.out.println(ba.toString());
							output.println("end BankAccount");
							output.println(ba.toString());
							break;
						}
						
						st = new java.util.StringTokenizer(inn, "\t");
						String type = st.nextToken();
						double amount = Double.parseDouble(st.nextToken());
						
						if( type.equals("withdrawFromChecking") )
						{
							boolean result = ba.accountTransaction(TransactionType.WITHDRAW_FROM_CHECKING, amount);
							System.out.println("withdrawFromChecking " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
							output.println("withdrawFromChecking " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
						}
						
						if( type.equals("withdrawFromSavings") )
						{
							boolean result = ba.accountTransaction(TransactionType.WITHDRAW_FROM_SAVINGS, amount);
							System.out.println("withdrawFromSavings " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
							output.println("withdrawFromSavings " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
						}
						
						if( type.equals("depositToChecking") )
						{
							boolean result = ba.accountTransaction(TransactionType.DEPOSIT_TO_CHECKING, amount);
							System.out.println("depositToChecking " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
							output.println("depositToChecking " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
						}
						
						if( type.equals("depositToSavings") )
						{
							boolean result = ba.accountTransaction(TransactionType.DEPOSIT_TO_SAVINGS, amount);
							System.out.println("depositToSavings " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
							output.println("depositToSavings " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
						}
						
						if( type.equals("transferToChecking") )
						{
							boolean result = ba.accountTransaction(TransactionType.TRANSFER_TO_CHECKING, amount);
							System.out.println("transferToChecking " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
							output.println("transferToChecking " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
						}
						
						if( type.equals("transferToSavings") )
						{
							boolean result = ba.accountTransaction(TransactionType.TRANSFER_TO_SAVINGS, amount);
							System.out.println("transferToSavings " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
							output.println("transferToSavings " + amount + " " + result + " " + ba.getCheckingBalance() + " " + ba.getSavingsBalance());
						}
						
						i = i+1;
					}
				}
				
				if( inn.startsWith("new EnhancedBankAccount") )
				{
					System.out.println(inn);
					output.println(inn);
					java.util.Date[] dates = new java.util.Date[10];
					java.util.StringTokenizer st = new java.util.StringTokenizer(inn, "\t");
					st.nextToken();
					String lastName = st.nextToken();
					String firstName = st.nextToken();
					double checkingInitialValue = Double.parseDouble(st.nextToken());
					double savingsInitialValue = Double.parseDouble(st.nextToken());
					zhou_EnhancedBankAccount eba = new zhou_EnhancedBankAccount(lastName, firstName, checkingInitialValue, savingsInitialValue);
					System.out.println(eba.toString());
					output.println(eba.toString());
					
					i = i+1;
					while( true )
					{
						if( i >= inputLines.size() )
						{
							System.out.println(eba.toString());
							output.println("end EnhancedBankAccount");
							output.println(eba.toString());
							break;
						}
						
						try
						{
							java.lang.Thread.sleep(50);
						}
						catch(Exception e)
						{
						}
						
						inn = inputLines.get(i);
						
						if( inn.startsWith("new") )
						{
							System.out.println(eba.toString());
							output.println("end EnhancedBankAccount");
							output.println(eba.toString());
							break;
						}
						
						st = new java.util.StringTokenizer(inn, "\t");
						String type = st.nextToken();
						double amount = Double.parseDouble(st.nextToken());
						
						// get the date
						if( type.equals("getDate") )
						{
							int index = (int) amount;
							dates[index] = new java.util.Date();
							System.out.println("date " + index + " " + dates[index]);
						}
						
						// all new accounts without dates
						if( type.equals("getNewAccountSuccessfulTransactionsNoDate") )
						{
							System.out.println("getNewAccountSuccessfulTransactionsNoDate");
							output.println("getNewAccountSuccessfulTransactionsNoDate");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, TransactionType.NEW_ACCOUNT);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// all successful with dates
						if( type.equals("getAllSuccessfulTransactions") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getAllSuccessfulTransactions " + dates[index0] + " " + dates[index1]);
							output.println("getAllSuccessfulTransactions " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.ALL_SUCCESSFULL, null, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// all successful without dates
						if( type.equals("getAllSuccessfulTransactionsNoDate") )
						{
							System.out.println("getAllSuccessfulTransactionsNoDate");
							output.println("getAllSuccessfulTransactionsNoDate");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.ALL_SUCCESSFULL, null);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// all failed with dates
						if( type.equals("getAllFailedTransactions") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getAllFailedTransactions " + dates[index0] + " " + dates[index1]);
							output.println("getAllFailedTransactions " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.ALL_FAILED, null, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// all failed without dates
						if( type.equals("getAllFailedTransactionsNoDate") )
						{
							System.out.println("getAllFailedTransactionsNoDate");
							output.println("getAllFailedTransactionsNoDate");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.ALL_FAILED, null);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful without dates
						if( type.equals("getWithdrawFromChecking") )
						{
							System.out.println("getWithdrawFromChecking");
							output.println("getWithdrawFromChecking");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, TransactionType.WITHDRAW_FROM_CHECKING);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful without dates
						if( type.equals("getWithdrawFromSavings") )
						{
							System.out.println("getWithdrawFromSavings");
							output.println("getWithdrawFromSavings");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, TransactionType.WITHDRAW_FROM_SAVINGS);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful without dates
						if( type.equals("getDepositToChecking") )
						{
							System.out.println("getDepositToChecking");
							output.println("getDepositToChecking");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, TransactionType.DEPOSIT_TO_CHECKING);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful without dates
						if( type.equals("getDepositToChecking") )
						{
							System.out.println("getDepositToSavings");
							output.println("getDepositToSavings");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, TransactionType.DEPOSIT_TO_SAVINGS);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful without dates
						if( type.equals("getTransferToChecking") )
						{
							System.out.println("getTransferToChecking");
							output.println("getTransferToChecking");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, TransactionType.TRANSFER_TO_CHECKING);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful without dates
						if( type.equals("getTransferToSavings") )
						{
							System.out.println("getTransferToSavings");
							output.println("getTransferToSavings");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, TransactionType.TRANSFER_TO_SAVINGS);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful with dates
						if( type.equals("getWithdrawFromCheckingWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getWithdrawFromCheckingWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getWithdrawFromCheckingWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, 
									TransactionType.WITHDRAW_FROM_CHECKING, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful with dates
						if( type.equals("getWithdrawFromSavingsWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getWithdrawFromSavingsWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getWithdrawFromSavingsWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, 
									TransactionType.WITHDRAW_FROM_SAVINGS, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful with dates
						if( type.equals("getDepositToCheckingWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getDepositToCheckingWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getDepositToCheckingWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, 
									TransactionType.DEPOSIT_TO_CHECKING, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful with dates
						if( type.equals("getDepositToSavingsWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getDepositToSavingsWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getDepositToSavingsWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, 
									TransactionType.DEPOSIT_TO_SAVINGS, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful with dates
						if( type.equals("getTransferToCheckingWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getTransferToCheckingWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getTransferToCheckingWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, 
									TransactionType.TRANSFER_TO_CHECKING, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some successful with dates
						if( type.equals("getTransferToSavingsWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getTransferToSavingsWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getTransferToSavingsWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_SUCCESSFULL, 
									TransactionType.TRANSFER_TO_SAVINGS, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some failed with dates
						if( type.equals("getFailedWithdrawFromCheckingWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getFailedWithdrawFromCheckingWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getFailedWithdrawFromCheckingWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, 
									TransactionType.WITHDRAW_FROM_CHECKING, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some Failed with dates
						if( type.equals("getFailedWithdrawFromSavingsWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getFailedWithdrawFromSavingsWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getFailedWithdrawFromSavingsWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, 
									TransactionType.WITHDRAW_FROM_SAVINGS, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some Failed with dates
						if( type.equals("getFailedDepositToCheckingWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getFailedDepositToCheckingWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getFailedDepositToCheckingWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, 
									TransactionType.DEPOSIT_TO_CHECKING, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some Failed with dates
						if( type.equals("getFailedDepositToSavingsWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getFailedDepositToSavingsWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getFailedDepositToSavingsWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, 
									TransactionType.DEPOSIT_TO_SAVINGS, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some Failed with dates
						if( type.equals("getFailedTransferToCheckingWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getFailedTransferToCheckingWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getFailedTransferToCheckingWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, 
									TransactionType.TRANSFER_TO_CHECKING, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some Failed with dates
						if( type.equals("getFailedTransferToSavingsWithDate") )
						{
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getFailedTransferToSavingsWithDate " + dates[index0] + " " + dates[index1]);
							output.println("getFailedTransferToSavingsWithDate " + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, 
									TransactionType.TRANSFER_TO_SAVINGS, dates[index0], dates[index1]);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some failed without dates
						if( type.equals("getFailedWithdrawFromChecking") )
						{
							System.out.println("getFailedWithdrawFromChecking");
							output.println("getFailedWithdrawFromChecking");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, TransactionType.WITHDRAW_FROM_CHECKING);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some failed without dates
						if( type.equals("getFailedWithdrawFromSavings") )
						{
							System.out.println("getFailedWithdrawFromSavings");
							output.println("getFailedWithdrawFromSavings");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, TransactionType.WITHDRAW_FROM_SAVINGS);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some failed without dates
						if( type.equals("getFailedDepositToChecking") )
						{
							System.out.println("getFailedDepositToChecking");
							output.println("getFailedDepositToChecking");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, TransactionType.DEPOSIT_TO_CHECKING);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some failed without dates
						if( type.equals("getFailedDepositToSavings") )
						{
							System.out.println("getFailedDepositToSavings");
							output.println("getFailedDepositToSavings");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, TransactionType.DEPOSIT_TO_SAVINGS);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some failed without dates
						if( type.equals("getFailedTransferToChecking") )
						{
							System.out.println("getFailedTransferToChecking");
							output.println("getFailedTransferToChecking");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, TransactionType.TRANSFER_TO_CHECKING);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						// some failed without dates
						if( type.equals("getFailedTransferToSavings") )
						{
							System.out.println("getFailedTransferToSavings");
							output.println("getFailedTransferToSavings");
							java.util.ArrayList<Transaction> transactions = eba.getTransactions(
									TransactionListType.SOME_FAILED, TransactionType.TRANSFER_TO_SAVINGS);
							if( transactions != null )
							{
								for( Transaction e : transactions )
								{
									System.out.println("\t" + e);
									output.println("\t" + e.toStringLessDate());
								}
							}
							else
							{
								System.out.println("\t" + "transaction is null");
							}
						}
						
						if( type.equals("withdrawFromChecking") )
						{
							boolean result = eba.accountTransaction(TransactionType.WITHDRAW_FROM_CHECKING, amount);
							System.out.println("withdrawFromChecking " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							if( !excludeNonTransactionListResultsInEnhancedResults )
							{
								output.println("withdrawFromChecking " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							}
						}
						
						if( type.equals("withdrawFromSavings") )
						{
							boolean result = eba.accountTransaction(TransactionType.WITHDRAW_FROM_SAVINGS, amount);
							System.out.println("withdrawFromSavings " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							if( !excludeNonTransactionListResultsInEnhancedResults )
							{
								output.println("withdrawFromSavings " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							}
						}
						
						if( type.equals("depositToChecking") )
						{
							boolean result = eba.accountTransaction(TransactionType.DEPOSIT_TO_CHECKING, amount);
							System.out.println("depositToChecking " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							if( !excludeNonTransactionListResultsInEnhancedResults )
							{
								output.println("depositToChecking " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							}
						}
						
						if( type.equals("depositToSavings") )
						{
							boolean result = eba.accountTransaction(TransactionType.DEPOSIT_TO_SAVINGS, amount);
							System.out.println("depositToSavings " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							if( !excludeNonTransactionListResultsInEnhancedResults )
							{
								output.println("depositToSavings " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							}
						}
						
						if( type.equals("transferToChecking") )
						{
							boolean result = eba.accountTransaction(TransactionType.TRANSFER_TO_CHECKING, amount);
							System.out.println("transferToChecking " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							if( !excludeNonTransactionListResultsInEnhancedResults )
							{
								output.println("transferToChecking " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							}
						}
						
						if( type.equals("transferToSavings") )
						{
							boolean result = eba.accountTransaction(TransactionType.TRANSFER_TO_SAVINGS, amount);
							System.out.println("transferToSavings " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							if( !excludeNonTransactionListResultsInEnhancedResults )
							{
								output.println("transferToSavings " + amount + " " + result + " " + eba.getCheckingBalance() + " " + eba.getSavingsBalance());
							}
						}
						
						i = i+1;
					}
				}
			}
			input.close();
			output.close();
		}
		catch(java.lang.IndexOutOfBoundsException e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
			System.exit(0);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
			System.exit(0);
		}
	}
}
