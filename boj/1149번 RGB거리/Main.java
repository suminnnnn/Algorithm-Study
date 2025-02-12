import java.io.*;
import java.util.*;

public class Main {
    
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int A[][] = new int[N][3];
        int D[][] = new int[N][3];

        int lastIdx = -1;
        int min = 1001;

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                
                if(i==0){
                    D[0][j] = A[i][j];
                }
            }
        }

        for(int i=1; i<N; i++) {
            for(int j=0; j<3; j++) {

                if (j==0)
                    D[i][0] = Math.min(D[i-1][1], D[i-1][2]) + A[i][j];
                else if (j==1)
                    D[i][1] = Math.min(D[i-1][0], D[i-1][2]) + A[i][j];
                else
                    D[i][2] = Math.min(D[i-1][0], D[i-1][1]) + A[i][j];
            }
            
        }

        System.out.println(Math.min(Math.min(D[N-1][0], D[N-1][1]), D[N-1][2]));

    }
    
    public static void main(String[] args) throws Exception {
        Main.solution();
    }
}
