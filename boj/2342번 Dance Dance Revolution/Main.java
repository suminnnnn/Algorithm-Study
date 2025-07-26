import java.io.*;
import java.util.*;

class Main{

    private static int cost(int from, int to){
        if(from==0) return 2;
        if(from==to) return 1;
        if ((from + 2) % 4 == to % 4) return 4;
        return 3;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputStr = br.readLine().split(" ");

        int step = inputStr.length;

        int[] input = new int[step];

        for(int i = 0; i<step; i++){
            input[i] = Integer.parseInt(inputStr[i]);
        }

        int[][][] dp = new int[step][5][5];

        for (int[][] d1 : dp)
            for (int[] d2 : d1)
                Arrays.fill(d2, Integer.MAX_VALUE);

        dp[1][input[0]][0] = 2;
        dp[1][0][input[0]] = 2;

        for (int s = 1; s < step - 1; s++) {
            int next = input[s];
            for (int l = 0; l <= 4; l++) {
                for (int r = 0; r <= 4; r++) {
                    if (dp[s][l][r] == Integer.MAX_VALUE) continue;

                    dp[s + 1][next][r] = Math.min(dp[s + 1][next][r], dp[s][l][r] + cost(l, next));
                    dp[s + 1][l][next] = Math.min(dp[s + 1][l][next], dp[s][l][r] + cost(r, next));
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                answer = Math.min(answer, dp[step - 1][i][j]);
            }
        }

        System.out.println(answer);
    }
}