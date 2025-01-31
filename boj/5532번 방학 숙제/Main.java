import java.util.*;
import java.io.*;

public class Main {
    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int L = sc.nextInt();
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();

        int M, K= 0;
        
        if(B%D>0)
            M = B/D +1;
        else
            M = B/D;

        if(A%C>0)
            K = A/C +1;
        else
            K = A/C;
        
        System.out.println(M>=K?(L-M):(L-K));
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
