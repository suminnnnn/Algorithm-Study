import java.io.*;
import java.util.*;

class Main{

    static int n;
    static int[][] D;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        D = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<n; j++){
                D[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(D[i][k]==1 && D[k][j]==1){
                        D[i][j] = 1;
                    }
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(D[i][j]+" ");
            }
            System.out.println();
        }
    }
}