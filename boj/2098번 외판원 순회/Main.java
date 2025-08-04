import java.util.*;
import java.io.*;

public class Main {

    static final int INF = 16_000_000;
    static int N;
    static int[][] W;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[1 << N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < (1 << N); i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(1, 0));
    }

    static int dfs(int visited, int current) {
        if (visited == (1 << N) - 1) {
            return W[current][0] == 0 ? INF : W[current][0];
        }

        if (dp[visited][current] != -1) return dp[visited][current];

        int min = INF;

        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0 || W[current][next] == 0) continue;

            int cost = dfs(visited | (1 << next), next) + W[current][next];
            min = Math.min(min, cost);
        }

        return dp[visited][current] = min;
    }
}
