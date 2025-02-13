import java.util.*;
import java.io.*;

public class Main {
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for (int t=0; t<T; t++){

            int n = Integer.parseInt(br.readLine());

            int[][] A = new int[2][n];
            int[][] D = new int[2][n];

            for(int i=0; i<2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int j=0; j<n; j++){
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = 0;

            D[0][0] = A[0][0];
            D[1][0] = A[1][0];

            if (n > 1) { 
                D[0][1] = A[0][1] + D[1][0];
                D[1][1] = A[1][1] + D[0][0];
            }

            for(int i=2; i<n; i++){
                D[0][i] = A[0][i] + Math.max(D[1][i-1], D[1][i-2]);
                D[1][i] = A[1][i] + Math.max(D[0][i-1], D[0][i-2]);
            }

            System.out.println(Math.max(D[0][n - 1], D[1][n - 1]));
        }

    }

    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
