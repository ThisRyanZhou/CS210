class checkResults
{
	public static void main(String[] args)
	{
		if( args.length != 2 )
		{
			System.out.println("format: checkResults \"expected results file\" \"file to check results\"");
			System.exit(0);
		}
		
		java.util.HashMap<String, java.util.ArrayList<String>> expectedResults = new java.util.HashMap<>();
		java.util.HashMap<String, java.util.ArrayList<String>> checkResults = new java.util.HashMap<>();
		java.util.ArrayList<String> bankAccountsExpected = new java.util.ArrayList<>();
		java.util.ArrayList<String> enhancedBankAccountsExpected = new java.util.ArrayList<>();
		java.util.ArrayList<String> bankAccountsCheck = new java.util.ArrayList<>();
		java.util.ArrayList<String> enhancedBankAccountsCheck = new java.util.ArrayList<>();
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("###.00");
		
		int index = args[1].indexOf("_transactions");
		String name = args[1].substring(0, index);
		
		int scoreCount = 0;
		double[] scoreResults = {0.0, 0.0, 0.0, 0.0};
		int[] deltaLineCount = {0, 0, 31, 31};
		
		boolean outputTransaction = true;
		
		try
		{
			java.io.BufferedReader expectedResultsInput = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(args[0])));
			java.io.BufferedReader checkResultsInput = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(args[1])));
			
			java.util.ArrayList<String> lines = new java.util.ArrayList<>();
			String currentAccount = "";
			String inn;
			while( (inn = expectedResultsInput.readLine()) != null )
			{
				if( inn.trim().startsWith("getAllSuccessfulTransactions ") || inn.trim().startsWith("getAllFailedTransactions ") || (inn.trim().indexOf("WithDate ") > -1) )
				{
					int ind = inn.indexOf(" ");
					inn = inn.substring(0, ind);
				}
				skipRestOfLine:
				if( (inn.trim().length() > 0) && (!inn.trim().startsWith("#")) )
				{
					if( inn.startsWith("new BankAccount") )
					{
						bankAccountsExpected.add(inn);
						if( currentAccount.length() > 0 )
						{
							expectedResults.put(currentAccount, lines);
						}
						lines = new java.util.ArrayList<>();
						currentAccount = inn;
						break skipRestOfLine;
					}
					
					if( inn.startsWith("new EnhancedBankAccount") )
					{
						enhancedBankAccountsExpected.add(inn);
						if( currentAccount.length() > 0 )
						{
							expectedResults.put(currentAccount, lines);
						}
						lines = new java.util.ArrayList<>();
						currentAccount = inn;
						break skipRestOfLine;
					}
					lines.add(inn);
				}
			}
			if( currentAccount.length() > 0 )
			{
				expectedResults.put(currentAccount, lines);
			}
			expectedResultsInput.close();
			
			currentAccount = "";
			lines = new java.util.ArrayList<>();
			while( (inn = checkResultsInput.readLine()) != null )
			{
				if( inn.trim().startsWith("getAllSuccessfulTransactions ") || inn.trim().startsWith("getAllFailedTransactions ") || (inn.trim().indexOf("WithDate ") > -1) )
				{
					int ind = inn.indexOf(" ");
					inn = inn.substring(0, ind);
				}
				skipRestOfLine2:
				if( (inn.trim().length() > 0) && (!inn.trim().startsWith("#")) )
				{
					if( inn.startsWith("new BankAccount") )
					{
						bankAccountsCheck.add(inn);
						if( currentAccount.length() > 0 )
						{
							checkResults.put(currentAccount, lines);
						}
						lines = new java.util.ArrayList<>();
						currentAccount = inn;
						break skipRestOfLine2;
					}
					
					if( inn.startsWith("new EnhancedBankAccount") )
					{
						enhancedBankAccountsCheck.add(inn);
						if( currentAccount.length() > 0 )
						{
							checkResults.put(currentAccount, lines);
						}
						lines = new java.util.ArrayList<>();
						currentAccount = inn;
						break skipRestOfLine2;
					}
					lines.add(inn);
				}
			}
			if( currentAccount.length() > 0 )
			{
				checkResults.put(currentAccount, lines);
			}
			checkResultsInput.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		int deltaIndex = 50;
		if( true )
		{
			System.out.println("expected BankAccount versus check BankAccount");
			for( int i = 0; i < bankAccountsExpected.size(); i++ )
			{
				String currentAccount = bankAccountsExpected.get(i);
				System.out.println("\t" + currentAccount);
				java.util.ArrayList<String> expectedList =  expectedResults.get(currentAccount);
				java.util.ArrayList<String> checkList =  checkResults.get(currentAccount);
				
				int numberLinesInExpected = expectedList.size();
				int numberLinesInCheck = checkList.size();
				
				boolean[] foundInCheck = new boolean[numberLinesInExpected];
				boolean[] foundInExpected = new boolean[numberLinesInCheck];
				int expectedFoundCount = 0;
				int checkFoundCount = 0;
				
				for( int j = 0; j < expectedList.size(); j++ )
				{
					doneWithCurrentListElement:
					for( int k = Math.max(0, j-deltaIndex); k < Math.min(j+deltaIndex, checkList.size()); k++ )
					{
						if( (!foundInExpected[k]) && expectedList.get(j).equals(checkList.get(k)) )
						{
							foundInCheck[j] = true;
							foundInExpected[k] = true;
							expectedFoundCount = expectedFoundCount+1;
							checkFoundCount = checkFoundCount+1;
							break doneWithCurrentListElement;
						}
					}
				}
				
				expectedFoundCount = expectedFoundCount-deltaLineCount[scoreCount];
				checkFoundCount = checkFoundCount-deltaLineCount[scoreCount];
				
				numberLinesInExpected = numberLinesInExpected-deltaLineCount[scoreCount];
				numberLinesInCheck = numberLinesInCheck-deltaLineCount[scoreCount];
				
				System.out.println("\t\texpectedFoundCount = " + expectedFoundCount);
				System.out.println("\t\tcheckFoundCount = " + checkFoundCount);
				System.out.println("\t\tnumberLinesInExpected = " + numberLinesInExpected);
				System.out.println("\t\tnumberLinesInCheck = " + numberLinesInCheck);
				double score = (((double) expectedFoundCount)/((double) numberLinesInExpected))*25.0;
				System.out.println("\t\tscore = " + score);
				
				scoreResults[scoreCount] = score;
				scoreCount = scoreCount+1;
			}
			
			if( outputTransaction )
			{
				System.out.println("expected BankAccount");
				for( int i = 0; i < bankAccountsExpected.size(); i++ )
				{
					String currentAccount = bankAccountsExpected.get(i);
					System.out.println("\t" + currentAccount);
					java.util.ArrayList<String> expectedList =  expectedResults.get(currentAccount);
					for( int j = 0; j < expectedList.size(); j++ )
					{
						System.out.println("\t\t" + expectedList.get(j));
					}
				}
				
				System.out.println("check BankAccount");
				for( int i = 0; i < bankAccountsCheck.size(); i++ )
				{
					String currentAccount = bankAccountsCheck.get(i);
					System.out.println("\t" + currentAccount);
					java.util.ArrayList<String> checkList =  checkResults.get(currentAccount);
					for( int j = 0; j < checkList.size(); j++ )
					{
						System.out.println("\t\t" + checkList.get(j));
					}
				}
			}
		}
		
		if( true )
		{
			System.out.println("expected EnhancedBankAccount versus check EnhancedBankAccount");
			for( int i = 0; i < enhancedBankAccountsExpected.size(); i++ )
			{
				String currentAccount = enhancedBankAccountsExpected.get(i);
				System.out.println("\t" + currentAccount);
				java.util.ArrayList<String> expectedList =  expectedResults.get(currentAccount);
				java.util.ArrayList<String> checkList =  checkResults.get(currentAccount);
				
				int numberLinesInExpected = expectedList.size();
				int numberLinesInCheck = checkList.size();
				
				boolean[] foundInCheck = new boolean[numberLinesInExpected];
				boolean[] foundInExpected = new boolean[numberLinesInCheck];
				int expectedFoundCount = 0;
				int checkFoundCount = 0;
				
				for( int j = 0; j < expectedList.size(); j++ )
				{
					doneWithCurrentListElement:
					for( int k = Math.max(0, j-deltaIndex); k < Math.min(j+deltaIndex, checkList.size()); k++ )
					{
						if( (!foundInExpected[k]) && expectedList.get(j).equals(checkList.get(k)) )
						{
							foundInCheck[j] = true;
							foundInExpected[k] = true;
							expectedFoundCount = expectedFoundCount+1;
							checkFoundCount = checkFoundCount+1;
							break doneWithCurrentListElement;
						}
					}
				}
				
				expectedFoundCount = expectedFoundCount-deltaLineCount[scoreCount];
				checkFoundCount = checkFoundCount-deltaLineCount[scoreCount];
				
				numberLinesInExpected = numberLinesInExpected-deltaLineCount[scoreCount];
				numberLinesInCheck = numberLinesInCheck-deltaLineCount[scoreCount];
				
				System.out.println("\t\texpectedFoundCount = " + expectedFoundCount);
				System.out.println("\t\tcheckFoundCount = " + checkFoundCount);
				System.out.println("\t\tnumberLinesInExpected = " + numberLinesInExpected);
				System.out.println("\t\tnumberLinesInCheck = " + numberLinesInCheck);
				double score = (((double) expectedFoundCount)/((double) numberLinesInExpected))*25.0;
				System.out.println("\t\tscore = " + score);
				
				scoreResults[scoreCount] = score;
				scoreCount = scoreCount+1;
			}
			
			if( outputTransaction )
			{
				System.out.println("expected EnhancedBankAccount");
				for( int i = 0; i < enhancedBankAccountsExpected.size(); i++ )
				{
					String currentAccount = enhancedBankAccountsExpected.get(i);
					System.out.println("\t" + currentAccount);
					java.util.ArrayList<String> expectedList =  expectedResults.get(currentAccount);
					for( int j = 0; j < expectedList.size(); j++ )
					{
						System.out.println("\t\t" + expectedList.get(j));
					}
				}
	
				System.out.println("check EnhancedBankAccount");
				for( int i = 0; i < enhancedBankAccountsCheck.size(); i++ )
				{
					String currentAccount = enhancedBankAccountsCheck.get(i);
					System.out.println("\t" + currentAccount);
					java.util.ArrayList<String> checkList =  checkResults.get(currentAccount);
					for( int j = 0; j < checkList.size(); j++ )
					{
						System.out.println("\t\t" + checkList.get(j));
					}
				}
			}
		}
		System.err.print(name);
		for(int i = 0; i < scoreResults.length; i++ )
		{
			System.err.print("\t" + df.format(scoreResults[i]));
		}
		System.err.println();
	}
}
