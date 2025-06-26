import java.io.*;
import java.util.*;

class Main{

    static int k, n;
    static int[][] D;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        D = new int[15][15];

        for(int i = 0; i<15; i++){
            D[0][i] = i;
            D[i][1] = 1;
        }

        for(int i = 1; i<15; i++){
            for(int j=2; j<15; j++){
                D[i][j] = D[i-1][j] + D[i][j-1];
            }
        }

        for(int t=0; t<T; t++){
            k = scanner.nextInt();
            n = scanner.nextInt();

            System.out.println(D[k][n]);
        }
    }
}