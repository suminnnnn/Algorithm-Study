import java.io.*;
import java.util.*;

class Main {


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] A = new int[N];
        int[] dp = new int[N];

        for(int i=0; i<N; i++){
            A[i] = sc.nextInt();
            dp[i] = 1;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<i; j++){
                if(A[i]>A[j]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int answer = 0;

        for(int i=0;i<N; i++){
            if(dp[i]>answer){
                answer = dp[i];
            }
        }

        System.out.println(answer);

    }
}