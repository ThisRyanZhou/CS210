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

    // public static void convertTextToBinary(String inputFilename, String outputFilename){
    //     try{
    //         // Buffered Reader
    //         BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
    //         // Buffered Output Stream
    //         BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outputFilename));

    //         // Byte Buffer
    //         // why are we using this cant we just convert to a byte and print it
    //         ByteBuffer bufferByteThing = ByteBuffer.allocate(1024);

    //         // initalize the line for the while loop
    //         String line;

    //         while((line = reader.readLine()) != null){
    //             if (line.length() == 0 || line.startsWith("#")){
    //                 continue;
    //             }
                
    //             String[] lineBreak = line.split("\t", 2);
    //             String inputType = lineBreak[0];
    //             String inputValues = lineBreak[1];

    //             if(inputType.equals("int")){
    //                 bufferByteThing.clear();
    //                 bufferByteThing.putChar('i');
    //                 bufferByteThing.putInt(Integer.parseInt(inputValues));
    //                 output.write(bufferByteThing.array(), 0, 6);
    //             }
    //             else if(inputType.equals("double")){
    //                 bufferByteThing.clear();
    //                 bufferByteThing.putChar('f');
    //                 bufferByteThing.putDouble(Double.parseDouble(inputValues));
    //                 output.write(bufferByteThing.array(), 0, 10);
    //             }
    //             else if(inputType.equals("float")){
    //                 bufferByteThing.clear();
    //                 bufferByteThing.putChar('f');
    //                 bufferByteThing.putFloat(Float.parseFloat(inputValues));
    //                 output.write(bufferByteThing.array(), 0, 6);
    //             }
    //             else if(inputType.equals("long")){
    //                 bufferByteThing.clear();
    //                 bufferByteThing.putChar('l');
    //                 bufferByteThing.putLong(Long.parseLong(inputValues));
    //                 output.write(bufferByteThing.array(), 0, 10);
    //             }
    //             else if(inputType.equals("short")){
    //                 bufferByteThing.clear();
    //                 bufferByteThing.putChar('s');
    //                 bufferByteThing.putShort(Short.parseShort(inputValues));
    //                 output.write(bufferByteThing.array(), 0, 4);
    //             }
    //             else if(inputType.equals("byte")){
    //                 bufferByteThing.clear();
    //                 bufferByteThing.putChar('b');
    //                 bufferByteThing.put((byte)Integer.parseInt(inputValues));
    //                 output.write(bufferByteThing.array(), 0, 3);
    //             }
    //             else if(inputType.equals("string")){
    //                 bufferByteThing.clear();
    //                 bufferByteThing.putChar('s');
    //                 bufferByteThing.putInt(inputValues.length());
    //                 output.write(bufferByteThing.array(), 0, 6);
    //                 char[] charInString = inputValues.toCharArray();
    //                 for(char a : charInString){
    //                     bufferByteThing.clear();
    //                     bufferByteThing.putChar(a);
    //                     output.write(bufferByteThing.array(), 0,2);
    //                 }
    //             }
    //             else if(inputType.equals("long array")){
    //                 String[] longArray = inputValues.split(",");
    //                 bufferByteThing.clear();
    //                 bufferByteThing.putChar('e');
    //                 bufferByteThing.putInt(longArray.length);
    //                 output.write(bufferByteThing.array(), 0, 6);
    //                 for(String a:longArray){
    //                     bufferByteThing.clear();
    //                     bufferByteThing.putLong(Long.parseLong(a));
    //                     output.write(bufferByteThing.array(), 0, 8);
    //                 }
    //             }
    //             else if(inputType.equals("byte array")){
    //                 String[] byteArray = inputValues.split(",");
    //                 bufferByteThing.clear();
    //                 bufferByteThing.putChar('g');
    //                 bufferByteThing.putInt(byteArray.length);
    //                 output.write(bufferByteThing.array(), 0, 6);

    //                 for(String a:byteArray){
    //                     bufferByteThing.clear();
    //                     bufferByteThing.put((byte)Integer.parseInt(a));
    //                     output.write(bufferByteThing.array(), 0, 1);
    //                 }
    //             }
    //             reader.close();
    //             output.close();
    //         }
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }

        // -------------------------------
    // Convert text file -> binary file
    // -------------------------------
    public static void convertTextToBinary(String inputFilename, String outputFilename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFilename));

        String line;
        ByteBuffer buffer = ByteBuffer.allocate(1024); // reused for writing

        while ((line = reader.readLine()) != null) {
            // Skip comments and empty lines
            if (line.length() == 0 || line.startsWith("#")) continue;

            String[] parts = line.split("\t", 2);
            String type = parts[0].trim();
            String data = parts[1].trim();

            // Each case handles one data type
            if (type.equals("int")) {
                buffer.clear();
                buffer.putChar('i');
                buffer.putInt(Integer.parseInt(data));
                bos.write(buffer.array(), 0, 6); // 2 bytes for char + 4 for int
            }
            else if (type.equals("double")) {
                buffer.clear();
                buffer.putChar('d');
                buffer.putDouble(Double.parseDouble(data));
                bos.write(buffer.array(), 0, 10);
            }
            else if (type.equals("float")) {
                buffer.clear();
                buffer.putChar('f');
                buffer.putFloat(Float.parseFloat(data));
                bos.write(buffer.array(), 0, 6);
            }
            else if (type.equals("long")) {
                buffer.clear();
                buffer.putChar('l');
                buffer.putLong(Long.parseLong(data));
                bos.write(buffer.array(), 0, 10);
            }
            else if (type.equals("short")) {
                buffer.clear();
                buffer.putChar('h');
                buffer.putShort(Short.parseShort(data));
                bos.write(buffer.array(), 0, 4);
            }
            else if (type.equals("byte")) {
                buffer.clear();
                buffer.putChar('b');
                buffer.put((byte) Integer.parseInt(data));
                bos.write(buffer.array(), 0, 3);
            }
            else if (type.equals("string")) {
                char[] chars = data.toCharArray();
                buffer.clear();
                buffer.putChar('s');
                buffer.putInt(chars.length);
                bos.write(buffer.array(), 0, 6);

                for (char c : chars) {
                    buffer.clear();
                    buffer.putChar(c);
                    bos.write(buffer.array(), 0, 2);
                }
            }
            else if (type.equals("long array")) {
                String[] nums = data.split(",");
                buffer.clear();
                buffer.putChar('e');
                buffer.putInt(nums.length);
                bos.write(buffer.array(), 0, 6);

                for (String n : nums) {
                    buffer.clear();
                    buffer.putLong(Long.parseLong(n.trim()));
                    bos.write(buffer.array(), 0, 8);
                }
            }
            else if (type.equals("byte array")) {
                String[] nums = data.split(",");
                buffer.clear();
                buffer.putChar('g');
                buffer.putInt(nums.length);
                bos.write(buffer.array(), 0, 6);

                for (String n : nums) {
                    buffer.clear();
                    buffer.put((byte) Integer.parseInt(n.trim()));
                    bos.write(buffer.array(), 0, 1);
                }
            }
        }

        reader.close();
        bos.close();
    }


    public static void convertBinaryToText(String inputFilename, String outputFilename){
    }
}