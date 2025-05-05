import java.io.*;
import java.util.*;

class Main {

    static int n,m;
    static int[][] D;
    static final int INF = 10000001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());

        D = new int[n+1][n+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i==j) D[i][j] = 0;
                else{
                    D[i][j] = INF;
                }

            }
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(D[s][e] > w) D[s][e] = w;
        }

        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(D[i][j] > D[i][k] + D[k][j]){
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(D[i][j] == INF) System.out.print("0 ");
                else System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }
    }
}