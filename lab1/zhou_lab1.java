import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
//import java.util.HashMap;

class zhou_lab1{
    public static void main(String[] args) {
        //argument intake
        int lineCount = 0;
        int tokenCount = 0;
        int intCount = 0;
        int intSum = 0;
        int[] returnable = new int[0];
        String[] input = new String[0];
        if (args.length == 0){
            //when you forget to add the text file with data
            System.out.println("you forgor smth");
            System.exit(0);
        }
        else if(args.length > 1){
            //when there is more than one arg which means I have to test the amount of the others there are.
            //something with a hashmap or string[] in order to store all the others
            returnable = new int[args.length - 1];
            input = new String[args.length - 1];
            for (int i = 1; i <args.length; i++){
                returnable[i-1] = 0;
                input[i-1] = args[i];
            }
        }
        //scanner starts
        try{
            File myFile = new File(args[0]);
            Scanner reader = new Scanner(myFile);
            while(reader.hasNextLine()){
                //loop through every line
                lineCount++;
                String lineAt = reader.nextLine();
                String[] items = lineAt.split(" ");
                for (int i = 0; i<items.length; i++){
                    //loop through every token in the line
                    tokenCount++;
                    try{
                        //test for int and then add them
                        Integer.parseInt(items[i]);
                        intCount++;
                        intSum = intSum + Integer.parseInt(items[i]);
                    }
                    catch(Exception e){
                        //dont got anythign to throw here
                    }
                    for(int j = 0; j < input.length; i++){
                        if (items[j].compareTo(input[j]) == 0){
                            returnable[j] = returnable[j] + 1;
                        }
                    }
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Code Broke");
            e.printStackTrace();
        }
        System.out.println("lineCount = " + lineCount);
        System.out.println("tokenCount = " + tokenCount);
        System.out.println("intCount = " + intCount);
        System.out.println("intSum = " + intSum);
        for(int i = 0; i < returnable.length; i++){
            System.out.println("Amount of " + input[i] + " = " + returnable[i]); 
        }
    }
}