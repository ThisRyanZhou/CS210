public class helloWorld{
	public static void main(String[] args){
		System.out.println("Hello World");
		for(int i = 0; i<args.length; i++){
			System.out.println("/t args[" + i + "] = " + args[i]);
		}
		for(int i = 0; i<args.length; i++){
            try{
                int intValue = Integer.parseInt(args[i])
			    System.out.println("/t args[" + i + "] as an int = " + intValue);
            }
            catch(Exception e){
                System.out.println("/t args[" + i + "] as an int = " + args[i] + " is not a valid integer.");
            }
	
		}

	}
}
