package lab1;
import java.util.Arrays;

public class mytest {
    public static void main(String[] args){
        String[] test = new String[]{"abc", "1", "0sd", "2x", "12", "kj", "kma", "0ka"};
        zhou_p1 tester = new zhou_p1();
        String[] sorted = tester.selectionSort(test,0,7);
        System.out.println(Arrays.toString(sorted));
    }
}
