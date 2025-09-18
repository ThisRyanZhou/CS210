class run_test_p1
{
	public static void main(String[] args)
	{
		String inn = "";
		try
		{
			java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(args[0])));
			inn = input.readLine();
			java.util.StringTokenizer st = new java.util.StringTokenizer(inn, "=");
			st.nextToken();
			int numberOfTestValues = Integer.parseInt(st.nextToken().trim());
			String[] testValuesSorted = new String[numberOfTestValues];
			String[] testValuesToSort = new String[numberOfTestValues];
			String[] testValues = new String[numberOfTestValues];
			for( int i = 0; i < testValues.length; i++ )
			{
				String stringValue = input.readLine().trim();
				testValuesToSort[i] = stringValue;
				testValues[i] = stringValue;
			}
			
			inn = input.readLine();
			for( int i = 0; i < testValues.length; i++ )
			{
				String stringValue = input.readLine().trim();
				testValuesSorted[i] = stringValue;
			}
			
			inn = input.readLine();
			
			// lower bound index of the array for the tests
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int lowerBound = Integer.parseInt(st.nextToken());
			
			// upper bound index of the array for the tests
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int upperBound = Integer.parseInt(st.nextToken());
			
			// lower bound index of the array for the sort
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int lowerBoundForSort = Integer.parseInt(st.nextToken());
			
			// upper bound index of the array for the sort
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int upperBoundForSort = Integer.parseInt(st.nextToken());
			
			// lower bound index for while loop
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int lowerBoundForWhileLoop = Integer.parseInt(st.nextToken());
			
			// upper bound index for while loop
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int upperBoundForWhileLoop = Integer.parseInt(st.nextToken());
			
			// the value to test against for forLoopTest
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int testValue = Integer.parseInt(st.nextToken());
			
			// the value to test against for whileLoopTest
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			String testValueString = st.nextToken();
			
			// min int value for doWhileLoopTest
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int testValue1 = Integer.parseInt(st.nextToken());
			
			// max int value for doWhileLoopTest
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int testValue2 = Integer.parseInt(st.nextToken());
			
			// for loop expected result
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int forLoopTestResult = Integer.parseInt(st.nextToken());
			
			// while loop expected result
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int whileLoopTestResult = Integer.parseInt(st.nextToken());
			
			// do while loop expected result
			inn = input.readLine();
			st = new java.util.StringTokenizer(inn, "= ");
			st.nextToken();
			int doWhileLoopTestResult = Integer.parseInt(st.nextToken());
			
			// switch expected results
			int[] switchTestResult = new int[12];
			for( int i = 0; i < switchTestResult.length; i++ )
			{
				inn = input.readLine();
				st = new java.util.StringTokenizer(inn, "= ");
				st.nextToken();
				switchTestResult[i] = Integer.parseInt(st.nextToken());
			}
			
			input.close();
			
			System.out.println("lowerBound = " + lowerBound);
			System.out.println("upperBound = " + upperBound);
			System.out.println("lowerBoundForSort = " + lowerBoundForSort);
			System.out.println("upperBoundForSort = " + upperBoundForSort);
			System.out.println("lowerBoundForWhileLoop = " + lowerBoundForWhileLoop);
			System.out.println("upperBoundForWhileLoop = " + upperBoundForWhileLoop);
			System.out.println("testValue = " + testValue);
			System.out.println("testValueString = " + testValueString);
			System.out.println("testValue1 = " + testValue1);
			System.out.println("testValue2 = " + testValue2);
			
			zhou_p1 testClass = new zhou_p1();
			testValuesToSort = testClass.selectionSort(testValuesToSort, lowerBoundForSort, upperBoundForSort);
			
			if( false )
			{
				System.out.println("testValuesToSort " + testValuesToSort.length);
				for( int i = 0; i < testValuesToSort.length; i++ )
				{
					System.out.println(testValuesToSort[i]);
				}
			}
			
			// 18% of score
			boolean sortWorked = true;
			try
			{
				for( int i = 0; i < testValuesSorted.length; i++ )
				{
					if( !testValuesSorted[i].equals(testValuesToSort[i]) )
					{
						sortWorked = false;
					}
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				sortWorked = false;
			}
			System.out.println("sortWorked = " + sortWorked);
						
			int forLoopTestResult2 = testClass.forLoopTest(lowerBound, upperBound, testValue, testValues);
			System.out.println("forLoopTestResult = " + forLoopTestResult + " " + forLoopTestResult2);
			
			int whileLoopTestResult2 = testClass.whileLoopTest(lowerBoundForWhileLoop, upperBoundForWhileLoop, testValueString, testValues);
			System.out.println("whileLoopTestResult = " + whileLoopTestResult + " " + whileLoopTestResult2);
			
			int doWhileLoopTestResult2 = testClass.doWhileLoopTest(lowerBound, upperBound, testValue1, testValue2, testValues);
			System.out.println("doWhileLoopTestResult = " + doWhileLoopTestResult + " " + doWhileLoopTestResult2);
						
			int[] switchTestResult2 = testClass.switchTest(lowerBound, upperBound, testValues);
			for( int i = 0; i < switchTestResult.length; i++ )
			{
				System.out.println("switchTestResult[" + i + "] = " + switchTestResult[i] + " " + switchTestResult2[i]);
			}
			
			// 18% of score
			boolean forLooTestWorked = (forLoopTestResult2 == forLoopTestResult);
			// 18% of score
			boolean whileLooTestWorked = (whileLoopTestResult2 == whileLoopTestResult);
			// 18% of score
			boolean doWhileLooTestWorked = (doWhileLoopTestResult2 == doWhileLoopTestResult);
			// 18% of score
			boolean[] switchTestWorked = new boolean[switchTestResult.length]; 
			for( int i = 0; i < switchTestResult.length; i++ )
			{
				switchTestWorked[i] = (switchTestResult2[i] == switchTestResult[i]);
			}
			
			int[] score = new int[switchTestResult.length+4];
			if( !sortWorked )
			{
				score[0] = 1;
			}
			
			if( !forLooTestWorked )
			{
				score[1] = 1;
			}
			
			if( !whileLooTestWorked )
			{
				score[2] = 1;
			}
			
			if( !doWhileLooTestWorked )
			{
				score[3] = 1;
			}
			
			for( int i = 0; i < switchTestResult.length; i++ )
			{
				if( !switchTestWorked[i] )
				{
					score[i+4] = 1;
				}
			}
			
			System.err.print("results" + "\t");
			for(int i = 0; i < score.length; i++ )
			{
				System.err.print(score[i] + "\t");
			}
			System.err.println();
			
		}
		catch(Exception e)
		{
			System.out.println(inn);
			System.out.println(e.toString());
			e.printStackTrace();
			System.exit(0);
		}
		
	}
}
