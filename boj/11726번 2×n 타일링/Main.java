import java.util.*;
import java.io.*;

public class Main {
    static long mod = 10007;

    public static void solution() throws Exception {        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long D[] = new long[1001];

        D[1] = 1;
        D[2] = 2;

        for(int i=3; i<=n; i++){
            D[i] = (D[i-1] + D[i-2]) % mod;
        }

        System.out.println(D[n]);
    }

    public static void main(String[] args) throws Exception{
        Main.solution();
    }
}
