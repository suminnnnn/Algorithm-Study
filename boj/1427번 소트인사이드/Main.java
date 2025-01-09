import java.util.*;
import java.io.*;

public class Main 
{

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        String num = sc.nextLine();

        char[] charArr = num.toCharArray();
        Integer[] numArr = new Integer[charArr.length];

        for(int i=0; i<charArr.length; i++) {
            numArr[i] = charArr[i]-'0';
        }

        Arrays.sort(numArr, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<numArr.length;i++){
            sb.append(numArr[i]);
        }

        System.out.println(sb);

    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
    
}
