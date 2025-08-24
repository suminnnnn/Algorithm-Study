import java.io.*;
import java.util.*;

class Main{

    static int A,B,C;
    static long result;

    private static long pow(long base, long exp){
        if(exp==1)
            return base%C;

        long half = pow(base, exp/2);
        long result = (half*half)%C;

        if(exp%2==1){
            result = (result*base)%C;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");

        A = Integer.parseInt(inputs[0]);
        B = Integer.parseInt(inputs[1]);
        C = Integer.parseInt(inputs[2]);

        result = pow(A, B);

        System.out.println(result);
    }
}