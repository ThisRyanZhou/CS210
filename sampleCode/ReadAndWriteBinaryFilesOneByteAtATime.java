class ReadAndWriteBinaryFilesOneByteAtATime
{
	// sample program using FileInputStream & FileOutputStream to read & write binary files one byte at a time
	public static void main(String[] args)
	{
		if( args.length < 1 )
		{
			System.out.println("format: ReadAndWriteBinaryFilesOneByteAtATime \"input file\" \"<output file>\"");
			System.exit(0);
		}
		
		try
		{
			java.io.FileInputStream input = new java.io.FileInputStream(args[0]);
			java.io.FileOutputStream output = null;
			boolean copyFile = false;
			
			// check if we are going to write to a file
			if( args.length > 1 )
			{
				output = new java.io.FileOutputStream(args[1]);
				copyFile = true;
			}
			
			// get the current time in ms
			long t0 = System.currentTimeMillis();
			int currentByte;
			int byteCount = 0;
			while( (currentByte = input.read()) >= 0 )
			{
				byteCount = byteCount+1;
				
				// using the if versus not doing a test did not have much of an 
				// effect the time to copy the file 
				if( copyFile )
				{
					output.write(currentByte);
				}
			}
			System.out.println("byteCount = " + byteCount);
			
			input.close();
			if( args.length > 1 )
			{
				output.close();
			}
			
			// get the current time in ms
			long t1 = System.currentTimeMillis();
			
			// output the time in seconds that it took to read and optionally write the file
			System.out.println("total time " + ((t1-t0)/1000.0) + " seconds");
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}
