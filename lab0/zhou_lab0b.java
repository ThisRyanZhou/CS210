public class zhou_lab0b{
	public static void main(String[] args){
		//System.out.println("Hello World");
		for(int i = 0; ++i<args.length;){
			System.out.println("/t args[" + i + "] = " + args[i]);
		}
		for(int i = 0; i<args.length; i++){
			int intValue = Integer.parseInt(args[i]);
			System.out.println("/t args[" + i + "] as an int = " + intValue);
		}

	}
}
