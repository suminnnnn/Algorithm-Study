import java.io.*;
import java.util.*;

class Main {

    static int N,M;
    static int[][] D;

    public static void main(String[] args) throws Exception {
         Scanner scanner = new Scanner(System.in);

         D = new int[30][30];

         for(int i = 1; i<30; i++){
             D[0][i] = 1;
             D[1][i] = i;
             D[i][i] = 1;
         }

         for(int j = 2;j<30; j++){
             for(int i=2; i<j; i++){
                 D[i][j] = D[i][j-1] + D[i-1][j-1];
             }
         }

         int T = scanner.nextInt();

         for(int t = 0; t<T; t++){
            N = scanner.nextInt();
            M = scanner.nextInt();

            System.out.println(D[N][M]);
         }

    }
}