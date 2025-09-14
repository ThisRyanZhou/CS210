import java.io.*;
import java.nio.ByteBuffer;

class zhou_p2{
    public static void main(String[] args){
        // not 3 args
        if (args.length < 3){
            System.out.println("Something missin");
            System.exit(0);
        }
        
        try{
            // check if its t2b or b2t or neither and throw error
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void convertTextToBinary(String inputFilename, String outputFilename) throws IOException{
        // Buffered Reader
        BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
        // Buffered Output Stream
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outputFilename));
        // Byte Buffer
        // why are we using this cant we just convert to a byte and print it
        ByteBuffer bufferByteThing = ByteBuffer.allocate(1024);

        // initalize the line for the while loop
        String line;

        while((line = reader.readLine()) != null){
            if (line.length() == 0 || line.startsWith("#")){
                continue;
            }
            
            String[] lineBreak = line.split("\t", 2);
            String inputType = lineBreak[0];
            String inputValues = lineBreak[1];

            if(inputType.equals("int")){
                bufferByteThing.clear();
                bufferByteThing.putChar('i');
                bufferByteThing.putInt(Integer.parseInt(inputValues));
                output.write(bufferByteThing.array(), 0, 6);
            }
            else if(inputType.equals("double")){
                bufferByteThing.clear();
                bufferByteThing.putChar('f');
                bufferByteThing.putDouble(Double.parseDouble(inputValues));
                output.write(bufferByteThing.array(), 0, 10);
            }
            else if(inputType.equals("float")){
                bufferByteThing.clear();
                bufferByteThing.putChar('f');
                bufferByteThing.putFloat(Float.parseFloat(inputValues));
                output.write(bufferByteThing.array(), 0, 6);
            }
            else if(inputType.equals("long")){
                bufferByteThing.clear();
                bufferByteThing.putChar('l');
                bufferByteThing.putLong(Long.parseLong(inputValues));
                output.write(bufferByteThing.array(), 0, 10);
            }
            else if(inputType.equals("short")){
                bufferByteThing.clear();
                bufferByteThing.putChar('s');
                bufferByteThing.putShort(Short.parseShort(inputValues));
                output.write(bufferByteThing.array(), 0, 4);
            }
            else if(inputType.equals("byte")){
                bufferByteThing.clear();
                bufferByteThing.putChar('b');
                bufferByteThing.put((byte)Integer.parseInt(inputValues));
                output.write(bufferByteThing.array(), 0, 3);
            }
            else if(inputType.equals("string")){
                bufferByteThing.clear();
                bufferByteThing.putChar('s');
                bufferByteThing.putInt(inputValues.length());
                output.write(bufferByteThing.array(), 0, 6);
                char[] charInString = inputValues.toCharArray();
                for(char a : charInString){
                    bufferByteThing.clear();
                    bufferByteThing.putChar(a);
                    output.write(bufferByteThing.array(), 0,2);
                }
            }
            else if(inputType.equals("long array")){
                String[] longArray = inputValues.split(",");
                bufferByteThing.clear();
                bufferByteThing.putChar('e');
                bufferByteThing.putInt(longArray.length);
                output.write(bufferByteThing.array(), 0, 6);
                for(String a:longArray){
                    bufferByteThing.clear();
                    bufferByteThing.putLong(Long.parseLong(a));
                    output.write(bufferByteThing.array(), 0, 8);
                }
            }
            else if(inputType.equals("byte array")){
                String[] byteArray = inputValues.split(",");
                bufferByteThing.clear();
                bufferByteThing.putChar('g');
                bufferByteThing.putInt(byteArray.length);
                output.write(bufferByteThing.array(), 0, 6);
                for(String a:byteArray){
                    bufferByteThing.clear();
                    bufferByteThing.put((byte)Integer.parseInt(a));
                    output.write(bufferByteThing.array(), 0, 1);
                }
            }
        }

        reader.close();
        output.close();
    }

    public static void convertBinaryToText(String inputFilename, String outputFilename) throws IOException{
        DataInputStream input = new DataInputStream(new FileInputStream(inputFilename));
        PrintWriter output = new PrintWriter(new FileWriter(outputFilename));
        //why does datainputstream not have a .hasnext command
        try{
            // for(int i = 0; i < 1; i++){
            //     i--;
            // }
            while(true){
                char inputType = input.readChar();
                if (inputType == 'i'){
                    int printAble = input.readInt();
                    output.println("int\t" + printAble);
                }
                else if (inputType == 'd'){
                    double printAble = input.readDouble();
                    output.println("double\t" + printAble);
                }
                else if (inputType == 'f'){
                    float printAble = input.readFloat();
                    output.println("float\t" + printAble);
                    
                }
                else if (inputType == 'l'){
                    long printAble = input.readLong();
                    output.println("long\t" + printAble);
                    
                }
                else if (inputType == 'h'){
                    short printAble = input.readShort();
                    output.println("short\t" + printAble);
                }
                else if (inputType == 'b'){
                    byte printAble = input.readByte();
                    output.println("byte\t" + printAble);
                }
                else if (inputType == 's'){
                    int max = input.readInt();
                    String printAble = "";
                    for(int i = 0; i < max; i++){
                        printAble = printAble + input.readChar();
                    }                    
                    output.println("string\t" + printAble);
                }
                else if (inputType == 'e'){
                    int max = input.readInt();
                    String printAble = "";
                    for(int i = 0; i < max; i++){
                        printAble = printAble + input.readLong();
                        if(!(i == max - 1)){
                            printAble = printAble + ",";
                        }
                    }
                    output.println("long array\t" + printAble);
                    
                }
                else if (inputType == 'g'){
                    int max = input.readInt();
                    String printAble = "";
                    for(int i = 0; i < max; i++){
                        printAble = printAble + input.readByte();
                        if(!(i == max - 1)){
                            printAble = printAble + ",";
                        }
                    }
                    output.println("byte array\t" + printAble);
                }
            }
        }
        catch(Exception e){

        }

        input.close();
        output.close();
    }
}