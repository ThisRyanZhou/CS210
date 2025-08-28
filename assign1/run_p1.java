class run_p1
{
	public static void main(String[] args)
	{
		// if we don't get one argument, print a message and exit
		if( args.length != 1 )
		{
			System.out.println("format is: testGarr \"input file\"");
			System.exit(0);
		}
		
		boolean outputSortedStrings = true;
		
		// allocate two empty String arrays to hold the test values
		// give then an initial size of 0, and then resize them
		// in the try/catch block
		String[] valuesToSort = new String[0];
		String[] valuesToTest = new String[0];
		
		try
		{
			java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(args[0])));
			
			// ArrayList to temporarily hold the test values 
			java.util.ArrayList<String> values = new java.util.ArrayList<>();
			
			String inn;
			
			// read the file and add the values to the ArrayList
			while( (inn = input.readLine()) != null )
			{
				values.add(inn);
			}
			input.close();
			
			// initialize the two String[]
			valuesToSort = new String[values.size()];
			valuesToTest = new String[values.size()];
			
			// copy the test values into the two in arrays
			for( int i = 0; i < values.size(); i++ )
			{
				valuesToSort[i] = values.get(i);
				valuesToTest[i] = values.get(i);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// instantiate the class that has the methods that we are going to test
		// put your class here
		zhou_p1 testClass = new zhou_p1();
		
		// lower bound index of the array for the tests
		int lowerBound = 0;
		
		// upper bound index of the array for the tests
		int upperBound = valuesToSort.length-1;
		
		// the value to test against for forLoopTest
		int testValue = 22;
		
		// lower bound index of the array for the tests
		int lowerBoundForWhileLoopTest = valuesToSort.length;
		
		// upper bound index of the array for the tests
		int upperBoundForWhileLoopTest = -1;
		
		// the value to test against for whileLoopTest
		String testValueString = "o0o";
		
		// min int value for doWhileLoopTest
		int testValue1 = 20;
		
		// max int value for doWhileLoopTest
		int testValue2 = 30;
		
		String[] sortedValues = testClass.selectionSort(valuesToSort, lowerBound, upperBound);
		
		if( outputSortedStrings )
		{
			System.out.println("sorted values");
			for( int i = 0; i < sortedValues.length; i++ )
			{
				System.out.println(sortedValues[i]);
			}
		}
		
		// run the for loop test
		int forLoopTestResult = testClass.forLoopTest(lowerBound, upperBound, testValue, valuesToTest);
		System.out.println("forLoopTestResult = " + forLoopTestResult);
		
		// run the while loop test
		int whileLoopTestResult = testClass.whileLoopTest(lowerBoundForWhileLoopTest, upperBoundForWhileLoopTest, testValueString, valuesToTest);
		System.out.println("whileLoopTestResult = " + whileLoopTestResult);
		
		// run the do-while loop test
		int doWhileLoopTestResult = testClass.doWhileLoopTest(lowerBound, upperBound, testValue1, testValue2, valuesToTest);
		System.out.println("doWhileLoopTestResult = " + doWhileLoopTestResult);
		
		// run the switch test
		int[] switchTestResult = testClass.switchTest(lowerBound, upperBound, valuesToTest);
		for( int i = 0; i < switchTestResult.length; i++ )
		{
			System.out.println("switchTestResult[" + i + "] = " + switchTestResult[i]);
		}
		
		// output the indices, test values, and results
		System.out.println("lowerBound = " + lowerBound);
		System.out.println("upperBound = " + upperBound);
		System.out.println("testValue = " + testValue);
		System.out.println("testValue1 = " + testValue1);
		System.out.println("testValue2 = " + testValue2);
		
	}
}
