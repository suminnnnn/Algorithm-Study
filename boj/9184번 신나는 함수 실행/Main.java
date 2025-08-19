import java.util.*;
import java.io.*;

class Main{

    static long[][][] dp;

    private static long w(int i,  int j, int k) {
        if(i<=0 || j<=0 || k<=0) return 1;
        if(i>20 || j>20 || k>20) return w(20, 20, 20);
        if(dp[i][j][k]!=-1) return dp[i][j][k];

        if((i<j) && (j<k))
            dp[i][j][k] = w(i,j,k-1) + w(i, j-1, k-1) - w(i, j-1, k);
        else
            dp[i][j][k] = w(i-1, j,k) + w(i-1, j-1, k) + w(i-1,j,k-1) - w(i-1,j-1,k-1);

        return dp[i][j][k];
    }

    public static void main(String[] args) throws Exception {
        dp = new long[21][21][21];
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                dp[i][j] = new long[21];
                Arrays.fill(dp[i][j], -1);
            }
        }

        while(true){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                if(i==-1 && j==-1 && k==-1)
                    return;

                StringBuffer sb = new StringBuffer();
                sb.append("w(");
                sb.append(i);
                sb.append(", ");
                sb.append(j);
                sb.append(", ");
                sb.append(k);
                sb.append(") = ");
                sb.append(w(i,j,k));
                System.out.println(sb.toString());
            }
        }

    }
}