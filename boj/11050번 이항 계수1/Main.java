import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        int[][] dp = new int[N+1][N+1];

        for(int i = 0; i<=N; i++){
            dp[i][0] = 1;
            dp[i][1] = i;
            dp[i][i] = 1;
        }

        for(int i = 2; i<=N; i++){
            for(int j=1; j<i; j++){
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }

        System.out.println(dp[N][K]);
    }
}