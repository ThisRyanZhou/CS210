import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.StringTokenizer;

class ReadTextFileAndParseTokensExample
{
	public static void main(String[] args)
	{
		if( args.length != 1 )
		{
			System.out.println("format is: ReadTextFileAndParseTokensExample \"input file name\"");
			System.exit(0);
		}
		
		try
		{
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
			
			String inn;
			while( (inn = input.readLine()) != null )
			{
				StringTokenizer st = new StringTokenizer(inn);
				System.out.println(inn + " (" + st.countTokens() + ")");
				while( st.hasMoreTokens() )
				{
					String s = st.nextToken();
					System.out.println("\t" + s + " (" + s.length() + ")");
				}
			}
			input.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}

