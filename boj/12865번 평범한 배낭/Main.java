import java.io.*;
import java.util.*;

class Main {

    static int N,K;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K+1];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            for(int w = K; w>=weight; w--){
                dp[w] = Math.max(dp[w], dp[w-weight] + value);
            }
        }

        System.out.println(dp[K]);
    }
}