java zhou_lab0a.java

java zhou_lab0b.java 0 1 2 3 4 5

java zhou_lab0b.java 0 1 2 a 4 5

My program loops through the inputs and prints them. If I were to use i <= args.length it would return an error since the index would be out of bounds since the length is 5 which means the last index will be 4 but we will be searching for args[5] which doesn't exist.

There wouldn't be any difference since ++i and i++ both increament the varible by 1, the only difference would be the value that is returned which doesn't matter since we arn't saving that.

With the change, the loop started with 1 and ends at 6. The main difference in this for loop is i++<args.length which causes the argument i that is compared to be args.length to be one less than the i that is used in the for loop.

With the change, the loop started at 1 and ended at 5. The main difference between this code and the last one is the i++ vs the ++i. ++i returns the changed value which i++ returns the previous value. Since ++i returns the changed value, the for loop stops at the right time but it starts at 1 since it was increamented already.

java zhou_lab0c.java 0 1 2 a 4 5 