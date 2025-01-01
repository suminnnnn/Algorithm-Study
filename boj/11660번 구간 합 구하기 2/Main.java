import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void solution() throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long numArr[][] = new long[N+1][N+1];
        long sumArr[][] = new long[N+1][N+1];
 
        for (int i=1; i<N+1; i++){
            st = new StringTokenizer(bf.readLine());

            for (int j=1; j<N+1; j++){
            
                numArr[i][j] = Integer.parseInt(st.nextToken());
                sumArr[i][j] = sumArr[i-1][j] + sumArr[i][j-1] - sumArr[i-1][j-1] + numArr[i][j];

            }
        }

        for (int i=0; i<M; i++){            
            st = new StringTokenizer(bf.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            long result = sumArr[x2][y2] - sumArr[x1-1][y2] - sumArr[x2][y1-1] + sumArr[x1-1][y1-1];
            
            System.out.println(result);
        }
    }
    
    public static void main(String args[]) throws Exception {
        Main.solution();
    }
}