import java.util.*;
import java.io.*;

public class Main {

    public static void solution() throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] A = new int[N+1];
        int[][] D = new int[N+1][N+1];
        
        for(int t=0; t<N; t++){
            int r = sc.nextInt();
            int c = sc.nextInt();

            if(t==0) A[0] = r;

            A[t+1] = c;
        }

        for(int i=1; i<=N; i++){
            D[i][i] = 0;
        }

        for(int len =2; len<=N; len++){
            for(int i=1; i<= N-len+1; i++){
                int j = i + len -1;
                
                D[i][j] = Integer.MAX_VALUE;

                for(int k=i; k<j; k++) {
                    int cost = D[i][k] + D[k+1][j] + (A[i-1] * A[k] * A[j]);
                    D[i][j] = Math.min(D[i][j], cost);
                }
            }
        }

        System.out.println(D[1][N]);
    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
