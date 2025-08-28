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
        if (args.length == 0){
            System.out.println("you forgor smth");
            System.exit(0);
        }
        else if(args.length > 1){
            //something with a hashmap in orde rto store all the others
        }
        //scanner starts
        try{
            File myFile = new File(args[0]);
            Scanner reader = new Scanner(myFile);
            while(reader.hasNextLine()){
                lineCount++;
                String lineAt = reader.nextLine();
                String[] items = lineAt.split(" ");
                for (int i = 0; i<items.length; i++){
                    tokenCount++;
                    try{
                        Integer.parseInt(items[i]);
                        intCount++;
                        intSum = intSum + Integer.parseInt(items[i]);
                    }
                    catch(Exception e){

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
    }
}