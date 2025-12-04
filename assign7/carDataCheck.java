import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;

class carDataCheck
{
	private static boolean outputCompareDetails = false;
	private static int doublePrecisionDigits = 10;
	public static void main(String[] args)
	{
		if( args.length != 6 )
		{
			System.out.println("format carDataCheck \"input file\" \"check file\" \"min total range\" \"max total range\" \"min remaining range\" \"max remaining range\"");
			System.exit(0);
		}
		
		double[] testResults = new double[17];
		for( int i = 0; i < testResults.length; i++ )
		{
			testResults[i] = 1.0;
		}
		
		ArrayList<ArrayList<String>> checkLists = new ArrayList<>(); 
		for( int i = 0; i < testResults.length+3; i++ )
		{
			checkLists.add(new ArrayList<String>());
		}
		
		// get the command line arguments
		String filename = args[0];
		String checkFilename = args[1];
		double minTotalRange = Double.parseDouble(args[2]);
		double maxTotalRange = Double.parseDouble(args[3]);
		double minRemainingRange = Double.parseDouble(args[4]);
		double maxRemainingRange = Double.parseDouble(args[5]);
		
		System.out.println("testing Car & ManageCarData");
		
		try
		{
			System.out.println("reading " + checkFilename);
			String inn;
			java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(checkFilename)));
			for( int i = 0; i < checkLists.size(); i++ )
			{
				ArrayList<String> currentArrayList = checkLists.get(i);
				input.readLine();
				while( (inn = input.readLine()) != null ) 
				{
					if( inn.length() > 0 )
					{
						currentArrayList.add(inn);
					}
					else
					{
						break;
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		
		System.out.println("checkLists.size() = " + checkLists.size());
				
		// create a ManageCarData object
		ManageCarDataFunctions manageCarData = new zhou_ManageCarData();
		
		// read the car definitions from the input file
		manageCarData.readData(filename);
		
		// get the list of cars that is stored as an arraylist and print it out
		System.out.println("carList");
		ArrayList<CarFunctions> carList = manageCarData.getCarList();
		testResults[0] = compareCarListToStringList(carList, checkLists.get(0), true);
		System.out.println("testResults[0] = " + testResults[0]);
		
		for( CarFunctions c : carList )
		{
			System.out.println(c);
		}
		System.out.println();

		// get the list of cars stored in the PriorityQueue ordered by total range via an iterator and print it out
		System.out.println("carListByTotalRange iterator");
		ArrayList<CarFunctions> carListByTotalRangeByIterator = manageCarData.getCarListByTotalRangeUsingIterator();
		testResults[1] = compareCarListToStringList(carListByTotalRangeByIterator, checkLists.get(1), false);
		System.out.println("testResults[1] = " + testResults[1]);
		
		for( CarFunctions c : carListByTotalRangeByIterator )
		{
			System.out.println(c);
		}
		System.out.println();
		
		// get an iterator for the PriorityQueue ordered by total range and print them out
		System.out.println("carListByTotalRange iterator local");
		Iterator<CarFunctions> itByTotalRange = manageCarData.getCarListByTotalRange().iterator();
		ArrayList<String> tempGetCarListByTotalRange = new ArrayList<>();
		while( itByTotalRange.hasNext() )
		{
			tempGetCarListByTotalRange.add(itByTotalRange.next().toString());
		}
		testResults[2] = compareWithoutOrder(tempGetCarListByTotalRange, checkLists.get(2));
		System.out.println("testResults[2] = " + testResults[2]);
		
		for( String s : tempGetCarListByTotalRange )
		{
			System.out.println(s);
		}
		System.out.println(); 
		
		// poll out the elements of the PriorityQueue ordered by remaining range 
		System.out.println("carListByRemainingRange poll");
		PriorityQueue<CarFunctions> carsByRemaingRange = manageCarData.getCarListByRemainingRange();
		ArrayList<String> tempGetCarListByRemainingRangeQueue = new ArrayList<>();
		while( carsByRemaingRange.size() > 0 )
		{
			tempGetCarListByRemainingRangeQueue.add(carsByRemaingRange.poll().toString());
		}
		testResults[3] = compare(tempGetCarListByRemainingRangeQueue, checkLists.get(3));
		System.out.println("testResults[3] = " + testResults[3]);
		
		for( String s : tempGetCarListByRemainingRangeQueue )
		{
			System.out.println(s);
		}
		System.out.println();
		
		// poll out the elements of the PriorityQueue ordered by total range 
		System.out.println("carListByTotalRange poll");
		PriorityQueue<CarFunctions> carsByTotalRange = manageCarData.getCarListByTotalRange();
		ArrayList<String> tempGetCarListByTotalRangeQueue = new ArrayList<>();
		while( carsByTotalRange.size() > 0 )
		{
			tempGetCarListByTotalRangeQueue.add(carsByTotalRange.poll().toString());
		}
		testResults[4] = compare(tempGetCarListByTotalRangeQueue, checkLists.get(4));
		System.out.println("testResults[4] = " + testResults[4]);
		
		for( String s : tempGetCarListByTotalRangeQueue )
		{
			System.out.println(s);
		}
		System.out.println();
		
		// get the list of cars stored in the PriorityQueue ordered by remaining range via an iterator and print it out
		System.out.println("carListByRemainingRange iterator");
		ArrayList<CarFunctions> carListByRemainingRangeByIterator = manageCarData.getCarListByRemainingRangeUsingIterator();
		testResults[5] = compareCarListToStringList(carListByRemainingRangeByIterator, checkLists.get(5), false);
		System.out.println("testResults[5] = " + testResults[5]);
		
		for( CarFunctions c : carListByRemainingRangeByIterator )
		{
			System.out.println(c);
		}
		System.out.println();
		
		// get an iterator for the PriorityQueue ordered by remaining range and print them out
		System.out.println("carListByRemainingRange iterator local");
		Iterator<CarFunctions> itByRemaininglRange = manageCarData.getCarListByRemainingRange().iterator();
		ArrayList<String> tempGetCarListByRemainingRange = new ArrayList<>();
		while( itByRemaininglRange.hasNext() )
		{
			tempGetCarListByRemainingRange.add(itByRemaininglRange.next().toString());
		}
		testResults[6] = compareWithoutOrder(tempGetCarListByRemainingRange, checkLists.get(6));
		System.out.println("testResults[6] = " + testResults[6]);
		
		for( String s : tempGetCarListByRemainingRange )
		{
			System.out.println(s);
		}
		System.out.println();
		
		// get the list of cars stored in the PriroityQueue ordered by total range having total range [minTotalRange, maxTotalRange]
		System.out.println("carListByTotalRange.poll().getTotalRangeInMiles() in [" + minTotalRange + "," + maxTotalRange + "]");
		ArrayList<String> carListByTotalRangeByPoll = manageCarData.getCarListByTotalRangeViaPoll(minTotalRange, maxTotalRange);
		testResults[7] = compare(carListByTotalRangeByPoll, checkLists.get(7));
		System.out.println("testResults[7] = " + testResults[7]);
		
		for( String s : carListByTotalRangeByPoll )
		{
			System.out.println(s);
		}
		System.out.println();
		
		// get the list of cars stored in the PriroityQueue ordered by remaining range having total range [minRemainingRange, maxRemainingRange]
		System.out.println("carListByRemainingRange.poll().getRemainingRangeInMiles() in [" + minRemainingRange + "," + maxRemainingRange + "]");
		ArrayList<String> carListByRemainingRangeByPoll = manageCarData.getCarListByRemainingRangeViaPoll(minRemainingRange, maxRemainingRange);
		testResults[8] = compare(carListByRemainingRangeByPoll, checkLists.get(8));
		System.out.println("testResults[8] = " + testResults[8]);
		
		for( String s : carListByRemainingRangeByPoll )
		{
			System.out.println(s);
		}
		System.out.println();
		
		// get the list of cars stored in the PriorityQueue ordered by total range via an iterator and print it out
		System.out.println("carListByTotalRange iterator (if empty, you didn't refill carListByTotalRange after polling all of the elements)");
		carListByTotalRangeByIterator = manageCarData.getCarListByTotalRangeUsingIterator();
		testResults[9] = compareCarListToStringList(carListByTotalRangeByIterator, checkLists.get(9), false);
		System.out.println("testResults[9] = " + testResults[9]);
		
		for( CarFunctions c : carListByTotalRangeByIterator )
		{
			System.out.println(c);
		}
		System.out.println();
		
		// get the list of cars stored in the PriorityQueue ordered by remaining range via an iterator and print it out
		System.out.println("carListByRemainingRange iterator (if empty, you didn't refill carListByRemainingRange after polling all of the elements)");
		carListByRemainingRangeByIterator = manageCarData.getCarListByRemainingRangeUsingIterator();
		testResults[10] = compareCarListToStringList(carListByRemainingRangeByIterator, checkLists.get(10), false);
		System.out.println("testResults[10] = " + testResults[10]);
		
		for( CarFunctions c : carListByRemainingRangeByIterator )
		{
			System.out.println(c);
		}
		System.out.println();
		
		ArrayList<String> driveThreeParamExceptions = new ArrayList<>();
		ArrayList<String> driveThreeParam = checkLists.get(11);
		System.out.println("3 parameter drive");
		for( int i = 0; i < driveThreeParam.size(); i++ )
		{
			System.out.println(driveThreeParam.get(i));
		}
		System.out.println();
		
		System.out.println("fuel exceptions for 3 parameter drive");
		try
		{
			for( int i = 0; i < driveThreeParam.size(); i++ )
			{
				java.util.StringTokenizer st = new java.util.StringTokenizer(driveThreeParam.get(i), "\t");
				Integer id = Integer.parseInt(st.nextToken());
				Integer timeInMinutes = Integer.parseInt(st.nextToken());
				Integer speedInMilesPerHours = Integer.parseInt(st.nextToken());
				String result = manageCarData.drive(id, timeInMinutes, speedInMilesPerHours);
				if( result.length() > 0 )
				{
					driveThreeParamExceptions.add(result);
					System.out.println(result);
				}
			}
			System.out.println();
			testResults[11] = compareOrig(driveThreeParamExceptions, checkLists.get(12));
		}
		catch(Exception e)
		{
			System.out.println("exception on testResults[11]");
			testResults[11] = 1.0;
		}
		System.out.println("testResults[11] = " + testResults[11]);
		
		System.out.println("remaining fuel updates post 3 param drive");
		carList = manageCarData.getCarList();
		testResults[12] = compareCarListToStringList(carList, checkLists.get(13), true);
		System.out.println("testResults[12] = " + testResults[12]);
		
		for( CarFunctions c : carList )
		{
			System.out.println(c);
		}
		System.out.println();
		
		ArrayList<String> driveTwoParamExceptions = new ArrayList<>();
		ArrayList<String> driveTwoParam = checkLists.get(14);
		System.out.println("2 parameter drive");
		for( int i = 0; i < driveTwoParam.size(); i++ )
		{
			System.out.println(driveTwoParam.get(i));
		}
		System.out.println();
		
		System.out.println("fuel exceptions for 2 parameter drive");
		try
		{
			for( int i = 0; i < driveTwoParam.size(); i++ )
			{
				java.util.StringTokenizer st = new java.util.StringTokenizer(driveTwoParam.get(i), "\t");
				Integer id = Integer.parseInt(st.nextToken());
				Double distanceInMiles = Double.parseDouble(st.nextToken());
				String result = manageCarData.drive(id, distanceInMiles);
				if( result.length() > 0 )
				{
					driveTwoParamExceptions.add(result);
					System.out.println(result);
				}
			}
			System.out.println();
			testResults[13] = compareOrig(driveTwoParamExceptions, checkLists.get(15));
		}
		catch(Exception e)
		{
			System.out.println("exception on testResults[13]");
			testResults[13] = 1.0;
		}
		System.out.println("testResults[13] = " + testResults[13]);
		
		System.out.println("remaining fuel updates post 2 param drive");
		carList = manageCarData.getCarList();
		testResults[14] = compareCarListToStringList(carList, checkLists.get(16), true);
		System.out.println("testResults[14] = " + testResults[14]);
		
		for( CarFunctions c : carList )
		{
			System.out.println(c);
		}
		System.out.println();

		ArrayList<String> idleExceptions = new ArrayList<>();
		ArrayList<String> idle = checkLists.get(17);
		System.out.println("idle");
		for( int i = 0; i < idle.size(); i++ )
		{
			System.out.println(idle.get(i));
		}
		System.out.println();
		
		System.out.println("fuel exceptions for idle");
		try
		{
			for( int i = 0; i < idle.size(); i++ )
			{
				java.util.StringTokenizer st = new java.util.StringTokenizer(idle.get(i), "\t");
				Integer id = Integer.parseInt(st.nextToken());
				int duration = Integer.parseInt(st.nextToken());
				String result = manageCarData.idle(id, duration);
				if( result.length() > 0 )
				{
					idleExceptions.add(result);
					System.out.println(result);
				}
			}
			System.out.println();
			testResults[15] = compareOrig(idleExceptions, checkLists.get(18));
		}
		catch(Exception e)
		{
			System.out.println("exception on testResults[15]");
			testResults[15] = 1.0;
		}
		System.out.println("testResults[15] = " + testResults[15]);
		
		System.out.println("remaining fuel updates post idle");
		carList = manageCarData.getCarList();
		testResults[16] = compareCarListToStringList(carList, checkLists.get(19), true);
		System.out.println("testResults[16] = " + testResults[16]);
		
		for( CarFunctions c : carList )
		{
			System.out.println(c);
		}
		System.out.println();

				
		System.err.print("testResults =");
		for( int i = 0; i < testResults.length; i++ )
		{
			System.err.print("\t"+testResults[i]);
		}
		System.err.println();
	}
	
	private static double compareCarListToStringList(ArrayList<CarFunctions> carList, ArrayList<String> checkList, boolean orderMatters)
	{
		ArrayList<String> list = new ArrayList<>();
		for( CarFunctions c : carList )
		{
			list.add(c.toString());
		}
		
		double result = 1.0;
		if( orderMatters )
		{
			result = compare(list, checkList);
		}
		else
		{
			result = compareWithoutOrder(list, checkList);
		}
		
		return result;
	}
		
	private static double compare(ArrayList<String> carList, ArrayList<String> checkList)
	{
		double result = 0.0;
		double delta = Math.max(0.05, 0.5/checkList.size());
		ArrayList<String> tempCarList = new ArrayList<>();
		ArrayList<String> tempCheckList = new ArrayList<>();
		
		String formatString = "######0.";
		for( int i = 0; i < doublePrecisionDigits; i++ )
		{
			formatString = formatString + "0";
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat(formatString);
		
		try
		{
		for( int i = 0; i < carList.size(); i++ )
		{
			String temp = carList.get(i);
			java.util.StringTokenizer st = new java.util.StringTokenizer(temp, "\t");
			Integer id = Integer.parseInt(st.nextToken());
			Integer fuelEconomy = Integer.parseInt(st.nextToken());
			Integer fuelCapacity = Integer.parseInt(st.nextToken());
			Double currentFuel = Double.parseDouble(st.nextToken());
			Double totalRange = Double.parseDouble(st.nextToken());
			Double remainingRange = Double.parseDouble(st.nextToken());
			Double burnRate = Double.parseDouble(st.nextToken());
			
			String updatedString = id + "\t" + fuelEconomy + "\t" + fuelCapacity + "\t" 
					+ df.format(currentFuel) + "\t" + df.format(totalRange) + "\t" 
					+ df.format(remainingRange) + "\t" + df.format(burnRate);
			carList.set(i, updatedString);	
		}
		}
		catch(Exception e)
		{
			System.out.println("exception parsing carList");
			return 1.0;
		}

		for( int i = 0; i < checkList.size(); i++ )
		{
			String temp = checkList.get(i);
			java.util.StringTokenizer st = new java.util.StringTokenizer(temp, "\t");
			Integer id = Integer.parseInt(st.nextToken());
			Integer fuelEconomy = Integer.parseInt(st.nextToken());
			Integer fuelCapacity = Integer.parseInt(st.nextToken());
			Double currentFuel = Double.parseDouble(st.nextToken());
			Double totalRange = Double.parseDouble(st.nextToken());
			Double remainingRange = Double.parseDouble(st.nextToken());
			Double burnRate = Double.parseDouble(st.nextToken());
			
			String updatedString = id + "\t" + fuelEconomy + "\t" + fuelCapacity + "\t" 
					+ df.format(currentFuel) + "\t" + df.format(totalRange) + "\t" 
					+ df.format(remainingRange) + "\t" + df.format(burnRate);
			checkList.set(i, updatedString);	
		}
		
		if( carList.size() != checkList.size() )
		{
			result = result + (Math.abs(carList.size()-checkList.size())*delta);
			if( outputCompareDetails )
			{
				System.out.println("delta size " + result);
				System.out.println("delta = " + delta);
				System.out.println("carList.size() = " + carList.size());
				System.out.println("checkList.size() = " + checkList.size());
			}
		}
		
		for( String s : carList )
		{
			if( !checkList.contains(s) )
			{
				result = result + delta;
				if(outputCompareDetails )
				{
					System.out.println("checkList missing " + s);
				}
			}
			else
			{
				tempCarList.add(s);
			}
		}
		
		for( String s : checkList )
		{
			if( !carList.contains(s) )
			{
				result = result + delta;
				if( outputCompareDetails )
				{
					System.out.println("carList missing " + s);
				}
			}
			else
			{
				tempCheckList.add(s);
			}
		}
		
		int minSize = (int) Math.min(tempCarList.size(), tempCheckList.size());
		
		for( int i = 0; i < minSize; i++ )
		{
			if( !tempCarList.get(i).equals(tempCheckList.get(i)) )
			{
				result = result + delta;
				if( outputCompareDetails )
				{
					System.out.println("tempCarList(" + i + ") != tempCheckList( " + i + ")");
					System.out.println("\t" + tempCarList.get(i));
					System.out.println("\t" + tempCheckList.get(i));
				}
			}
		}
		
		return Math.min(1.0, result);
	}

	private static double compareWithoutOrder(ArrayList<String> carList, ArrayList<String> checkList)
	{
		double result = 0.0;
		double delta = Math.max(0.05, 0.5/checkList.size());
		ArrayList<String> tempCarList = new ArrayList<>();
		ArrayList<String> tempCheckList = new ArrayList<>();
		
		String formatString = "######0.";
		for( int i = 0; i < doublePrecisionDigits; i++ )
		{
			formatString = formatString + "0";
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat(formatString);
		
		try
		{
		for( int i = 0; i < carList.size(); i++ )
		{
			String temp = carList.get(i);
			java.util.StringTokenizer st = new java.util.StringTokenizer(temp, "\t");
			Integer id = Integer.parseInt(st.nextToken());
			Integer fuelEconomy = Integer.parseInt(st.nextToken());
			Integer fuelCapacity = Integer.parseInt(st.nextToken());
			Double currentFuel = Double.parseDouble(st.nextToken());
			Double totalRange = Double.parseDouble(st.nextToken());
			Double remainingRange = Double.parseDouble(st.nextToken());
			Double burnRate = Double.parseDouble(st.nextToken());
			
			String updatedString = id + "\t" + fuelEconomy + "\t" + fuelCapacity + "\t" 
					+ df.format(currentFuel) + "\t" + df.format(totalRange) + "\t" 
					+ df.format(remainingRange) + "\t" + df.format(burnRate);
			carList.set(i, updatedString);	
		}
		}
		catch(Exception e)
		{
			System.out.println("exception parsing carList");
			return 1.0;
		}

		for( int i = 0; i < checkList.size(); i++ )
		{
			String temp = checkList.get(i);
			java.util.StringTokenizer st = new java.util.StringTokenizer(temp, "\t");
			Integer id = Integer.parseInt(st.nextToken());
			Integer fuelEconomy = Integer.parseInt(st.nextToken());
			Integer fuelCapacity = Integer.parseInt(st.nextToken());
			Double currentFuel = Double.parseDouble(st.nextToken());
			Double totalRange = Double.parseDouble(st.nextToken());
			Double remainingRange = Double.parseDouble(st.nextToken());
			Double burnRate = Double.parseDouble(st.nextToken());
			
			String updatedString = id + "\t" + fuelEconomy + "\t" + fuelCapacity + "\t" 
					+ df.format(currentFuel) + "\t" + df.format(totalRange) + "\t" 
					+ df.format(remainingRange) + "\t" + df.format(burnRate);
			checkList.set(i, updatedString);	
		}
		
		if( carList.size() != checkList.size() )
		{
			result = result + (Math.abs(carList.size()-checkList.size())*delta);
			if( outputCompareDetails )
			{
				System.out.println("delta size " + result);
				System.out.println("delta = " + delta);
				System.out.println("carList.size() = " + carList.size());
				System.out.println("checkList.size() = " + checkList.size());
			}
		}
		
		int missingCount = 0;
		
		for( String s : carList )
		{
			if( !checkList.contains(s) )
			{
				result = result + delta;
				if(outputCompareDetails )
				{
					System.out.println("checkList missing " + s);
					missingCount = missingCount + 1;
					result = result + delta;
				}
			}
			else
			{
				tempCarList.add(s);
			}
		}
		
		for( String s : checkList )
		{
			if( !carList.contains(s) )
			{
				result = result + delta;
				if( outputCompareDetails )
				{
					System.out.println("carList missing " + s);
					missingCount = missingCount + 1;
					result = result + delta;
				}
			}
			else
			{
				tempCheckList.add(s);
			}
		}
		
		if( outputCompareDetails )
		{
			System.out.println("missingCount = " + missingCount);
		}
		
		return Math.min(1.0, result);
	}

	private static double compareOrig(ArrayList<String> carList, ArrayList<String> checkList)
	{
		double result = 0.0;
		double delta = Math.max(0.05, 0.5/checkList.size());
		ArrayList<String> tempCarList = new ArrayList<>();
		ArrayList<String> tempCheckList = new ArrayList<>();
		
		if( carList.size() != checkList.size() )
		{
			result = result + (Math.abs(carList.size()-checkList.size())*delta);
			if( outputCompareDetails )
			{
				System.out.println("delta size " + result);
				System.out.println("delta = " + delta);
				System.out.println("carList.size() = " + carList.size());
				System.out.println("checkList.size() = " + checkList.size());
			}
		}
		
		for( String s : carList )
		{
			if( !checkList.contains(s) )
			{
				result = result + delta;
				if(outputCompareDetails )
				{
					System.out.println("checkList missing " + s);
				}
			}
			else
			{
				tempCarList.add(s);
			}
		}
		
		for( String s : checkList )
		{
			if( !carList.contains(s) )
			{
				result = result + delta;
				if( outputCompareDetails )
				{
					System.out.println("carList missing " + s);
				}
			}
			else
			{
				tempCheckList.add(s);
			}
		}
		
		int minSize = (int) Math.min(tempCarList.size(), tempCheckList.size());
		
		for( int i = 0; i < minSize; i++ )
		{
			if( !tempCarList.get(i).equals(tempCheckList.get(i)) )
			{
				result = result + delta;
				if( outputCompareDetails )
				{
					System.out.println("tempCarList(" + i + ") != tempCheckList(" + i + ")");
					System.out.println("\t" + tempCarList.get(i));
					System.out.println("\t" + tempCheckList.get(i));
				}
			}
		}
		
		return Math.min(1.0, result);
	}
}
