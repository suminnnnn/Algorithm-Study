import java.io.*;
import java.util.*;

class Main {
    static int n,m;
    static int[][] D;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        D = new int[n+1][n+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i==j) D[i][j] = 0;
                else{
                    D[i][j] = 10000001;
                }
            }
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            D[s][e] = 1;
            D[e][s] = 1;
        }

        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(D[i][j]> D[i][k] + D[k][j]){
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
        }

        int Min = Integer.MAX_VALUE;
        int Answer = -1;

        for(int i=1; i<=n; i++){
            int temp = 0;
            for(int j=1; j<=n; j++){
                temp += D[i][j];
            }
            if(temp<Min){
                Min = temp;
                Answer = i;
            }
        }
        System.out.println(Answer);
    }
}