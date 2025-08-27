package lab1;

class zhou_p1{
    public zhou_p1(){
    }
    // method to sort the elements of a String[] having index in
    // [lowerBound, upperBound] using selection sort in ascending order
    // non-integers values are sorted prior to integer values, with Strings
    // sorted first by length and then alphabetically
    public String[] selectionSort(String[] values, int lowerBound, int upperBound){
        for(int i = lowerBound; i <= upperBound; i++){
            int minAt = i;
            for(int j = i; j <= upperBound; j++){
                if(compare(values[minAt], values[j])){
                    minAt = j;
                }
            }
            String save = values[i];
            values[i] = values[minAt];
            values[minAt] = save;
        }
        return values;
    }

    //return true if a > b
    //return false if a <= b
    boolean compare(String a, String b){
        //sort length
        if (a.length() != b.length()){
            return a.length() > b.length();
        }
        //sort by int
        boolean work1 = false;
        boolean work2 = false;
        try{
            Integer.parseInt(a);
            work1 = true;
        }
        catch(Exception e){

        }
        try{
            Integer.parseInt(b);
            work2 = true;
        }
        catch(Exception e){

        }
        if (work1 & work2){
            return Integer.parseInt(a) > Integer.parseInt(b);            
        }
        else if(work1){
            return true;
        }
        else if(work2){
            return false;
        }
        //compareto
        else{
            return a.compareTo(b) > 0;
        }
    }
    // method to return the sum of the integer array elements > testValue with indices
    // in [lowerBound, upperBound] using a for loop to examine the array elements
    public int forLoopTest(int lowerBound, int upperBound, int testValue, String[] values){
        int returnable = 0;
        for(int i = lowerBound; i <= upperBound; i++){
            try{
                if (Integer.parseInt(values[i]) > testValue){
                    returnable = returnable + Integer.parseInt(values[i]);
                }

            }
            catch(Exception e){
                continue;
            }
        }
        return returnable;
    }
    // method to return the number of non-integer array elements <= testValue with
    // indices not in [lowerBound, upperBound] using a while loop to examine the array
    // elements
    public int whileLoopTest(int lowerBound, int upperBound, String testValue, String[] values){
        int returnable = 0;
        int i = lowerBound;
        while(i <= upperBound){
            try{
                Integer.parseInt(values[i]);
            }
            catch(Exception e){
                if (values[i].compareTo(testValue) <= 0) {
                    returnable = returnable + 1;
                }
            }
            i = i + 1;
        }
        return returnable;
    }
    // method to return the sum of the int array elements not in
    // [testValue1, testValue2] and with indices in [lowerBound, upperBound]
    // using a do-while loop to examine the array elements
    public int doWhileLoopTest(int lowerBound, int upperBound, int testValue1, int testValue2, String[] values){
        int returnable = 0;
        do{
            try{
                int currentVal = Integer.parseInt(values[lowerBound]);
                if (!(testValue1 == currentVal || testValue2 == currentVal)){
                    returnable += currentVal;
                }
            }
            catch(Exception e){
                continue;
            }
        }while(lowerBound <= upperBound);
        return returnable;
    }
    // method to return the number of array elements that match the switch
    // cases 0, 1, 2, 3, 4, 5, a, e, i, o or u, the default case, and count of
    // the number of values with length < 3, with indices in [lowerBound, upperBound]
    // return an array of size 12, with indices 0 - 9 corresponding to cases
    // 0 - 5, a, e, i, o or u, index 10 corresponding to the default case, and
    // index 11 corresponding to the count of strings with length less than 3
    // cases are based on the value of the third symbol in the string
    // '0' - count of all values
    // '1' - sum of int values
    // '2' - count of the non-int values
    // '3' - count of all values
    // '4' - count of int values
    // '5' - sum of int values
    // 'a' - count of all values
    // 'e' - count of all values
    // 'i' - count of all values
    // 'o' & 'u' - count of all values
    // default - count of values not covered elsewhere
    // the number of values with length less than 3
    public int[] switchTest(int lowerBound, int upperBound, String[] values){
        return (new int[1]);
    }
}