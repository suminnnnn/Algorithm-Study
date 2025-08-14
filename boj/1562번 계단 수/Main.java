import java.io.*;
import java.util.*;

class Main {

    static int N;
    static long[][][] dp;
    static final long mod = 1000000000;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        dp = new long[N + 1][10][1 << 10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int len = 2; len <= N; len++) {
            for (int digit = 0; digit <= 9; digit++) {
                for (int mask = 0; mask < (1 << 10); mask++) {
                    long cur = dp[len - 1][digit][mask];
                    if (cur == 0) continue;

                    if (digit > 0) {
                        int newMask = mask | (1 << (digit - 1));
                        dp[len][digit - 1][newMask] =
                                (dp[len][digit - 1][newMask] + cur) % mod;
                    }

                    if (digit < 9) {
                        int newMask = mask | (1 << (digit + 1));
                        dp[len][digit + 1][newMask] =
                                (dp[len][digit + 1][newMask] + cur) % mod;
                    }
                }
            }
        }

        int fullMask = (1 << 10) - 1;
        long answer = 0;

        for (int digit = 0; digit <= 9; digit++) {
            answer = (answer + dp[N][digit][fullMask]) % mod;
        }

        System.out.println(answer);
    }
}
