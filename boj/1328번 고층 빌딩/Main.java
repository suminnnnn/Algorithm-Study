import java.io.*;
import java.util.*;

class Main {

    static int N, L, R;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bufferedReader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        R = Integer.parseInt(input[2]);

        int[][][] D = new int[N+1][L+1][R+1];

        D[1][1][1] = 1;

        for(int i = 2; i<=N; i++){
            for(int l = 1; l<=L; l++){
                for(int r=1; r<=R; r++){
                    long value = 0;

                    if(l>1) value+=  D[i-1][l-1][r];
                    if(r>1) value+= D[i-1][l][r-1];
                    value += ((long) D[i-1][l][r]) * (i - 2); ;

                    D[i][l][r] = (int)(value%1000000007);
                }
            }
        }

        System.out.println(D[N][L][R]);
    }
}
