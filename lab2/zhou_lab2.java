import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.*;

class zhou_lab2
{
	public static void main(String[] args)
	{
		// put some code here to check for three commandline arguments
		
		// put some code here to check that the first commandline argument starts with "b" or "t"

		//ignore when args is under 3 since none of the code should be ran when only given 2 inputs
		if (args.length < 3){
			System.out.println("Somethings missin");
			System.exit(0);
		}

		//3 cases
		//case 1 it starts with b and you run binary to text
		//case 2 it starts with t and you run text to binary
		//case 3 its not b or t and you throw an error and exit
		if( args[0].startsWith("b") )
		{
			convertBinaryToText(args[1], args[2]);
		}
		else if (args[0].startsWith("t"))
		{
			convertTextToBinary(args[1], args[2]);
		}
		else{
			System.out.println("Not a valid first input");
			System.exit(0);
		}
	}
	
	private static void convertBinaryToText(String inputFilename, String outputFilename)
	{
		System.out.println("convertBinaryToText");
		try
		{
			//when reading an binary imput file you can use a FileInputStream
			FileInputStream input1 = new FileInputStream(inputFilename);
			//when writing to a text output file you use PrintWriter which is initalized with BufferedWriter which is initalized with a FileWriter
			PrintWriter writer1 = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));

			//the loop goes through every value inside of the input file
			int currentByte;
			//set the int as the next value and make sure it exists by checking to make sure its greater than or equal to 0
			while((currentByte = input1.read())>= 0){
				//turn the character to a string so it can be added to the txt file
				String value = Character.toString((char)currentByte);
				writer1.print(value);
			}

			//close
			input1.close();
			writer1.close();

		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
	
	private static void convertTextToBinary(String inputFilename, String outputFilename)
	{
		System.out.println("convertTextToBinary");
		try
		{
			//when reading a text file you use a FileReader but we learned that a BufferedReader which is a Buffered file reader is faster than a file reader since its buffered is something else we can use to optimize the runtime
			BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
			//when writing to a binary file you will use a DataOutputStream which is initalized using a fileoutputstream
			DataOutputStream writer = new DataOutputStream(new FileOutputStream(outputFilename));
			//my loop works by reading it the first time and then reading it before the code ends which allows the next cycle to be checked
			//I did this before doing binarytotext so i didnt know how the other while loop worked and this also works so oh well
			String line = reader.readLine();
			while(line != null){
				//you turn the string into a byte array and just write it onto the file
				byte[] lines = line.getBytes();
				writer.write(lines);
				//uhhh you spelled seperator wrong and it fully broke so i used that fix temporarily 
				//char newlineChar = '\n';
				//writer.writeByte(newlineChar);
				writer.write(System.lineSeparator().getBytes());
				line = reader.readLine();
			}
			//close
			reader.close();
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}

