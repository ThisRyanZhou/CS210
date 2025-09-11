package lab3;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


class zhou_lab3{
    public static void main(String[] args) {
        // requires at least 2 inputs namely the file name and the output file name
        if(args.length < 2){
            System.out.println("You forgor smth");
            System.exit(0);
        }

        // check to see if they want a file size and char nums
        boolean yes = false;
        if(args.length == 3){
            String wantInfo = args[2].toLowerCase();
            if (wantInfo.startsWith("t") || wantInfo.startsWith("y")){
                yes = true;
            }
        }

        // things that we need to return
        // backwords returnable, line count, word count, char count, unique words, word count
        TreeSet<String> uniqueWords = new TreeSet<>();
        HashMap<String, Integer> numWords = new HashMap<>();
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try{
            // the input and output he defined
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));

            String atLine;
            // loops through all lines
            while((atLine = input.readLine()) != null){
                lineCount = lineCount + 1;

                ArrayDeque<String> trashcan = new ArrayDeque<>();

                // use a scanner in order to read each word and increment the variables he wants us to print
                Scanner givenInputs = new Scanner(atLine);
                while(givenInputs.hasNext()){
                    String curWord = givenInputs.next();
                    trashcan.addLast(curWord);
                    wordCount = wordCount + 1;
                    charCount = charCount + curWord.length();
                    uniqueWords.add(curWord);
                    if(numWords.containsKey(curWord)){
                        numWords.put(curWord, numWords.get(curWord) + 1);
                    }
                    else{
                        numWords.put(curWord, 1);
                    }
                }
                givenInputs.close();
                
                // // wanted to try StringBuilder like he mentioned in class
                // // works and is slightly faster
                // StringBuilder outputLine = new StringBuilder();
                // while(!trashcan.isEmpty()){
                //     String lineOutput = trashcan.removeLast();
                //     StringBuilder reversedLineOutput = new StringBuilder(lineOutput).reverse();
                //     outputLine.append(reversedLineOutput);
                //     if(!trashcan.isEmpty()){
                //         outputLine.append(" ");
                //     }
                // }
                // output.println(outputLine.toString());

                // reverses the individual strings
                String returnable = "";
                while(!trashcan.isEmpty()){
                    String lineOutput = trashcan.removeLast();
                    String reversedString = "";
                    for (int i = lineOutput.length() - 1; i >= 0; i--) {
                        reversedString += lineOutput.charAt(i);
                    }
                    returnable = returnable + reversedString;
                    if(!trashcan.isEmpty()){
                        returnable = returnable + " ";
                    }
                }
                output.println(returnable);

            }

            input.close();
            output.close();

        }
        catch(Exception e){

        }

        // printing everything that is needed
        DecimalFormat format = new DecimalFormat("###,###,###,###,###");
        System.out.println("lines = " + format.format(lineCount));
        System.out.println("words = " + format.format(wordCount));
        System.out.println("character in words = " + format.format(charCount));
        System.out.println("unique words = " + format.format(uniqueWords.size()));

        if(yes){
            int size = uniqueWords.size();
            for(int i = 0; i < size; i++){
                String valueAt = uniqueWords.getFirst();
                uniqueWords.removeFirst();  
                System.out.println(valueAt + " = " + format.format(numWords.get(valueAt)));
            }
        }
    }
}