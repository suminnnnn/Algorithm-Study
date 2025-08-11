import java.util.*;
import java.io.*;

class Main{

    static final long NUMBER = 1_000_000_007L;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();

        dp = new long[(int)((N+1))];

        dp[0] = 0%NUMBER;
        dp[1] = 1%NUMBER;

        for(int i = 2; i<=N; i++){
            dp[i] = (dp[i-1]%NUMBER) + (dp[i-2]%NUMBER);
            dp[i] = dp[i]%NUMBER;
        }
        System.out.println(dp[(int)N]);
    }
}